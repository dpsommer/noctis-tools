angular.module('map', ['leaflet-directive'])
  .controller('MapCtrl', function($scope, $http, leafletData) {
    angular.extend($scope, {
      center: {
        lat: 0.5,
        lng: 0.5,
        zoom: 2
      },
      defaults: {
        drawControl: true,
        noWrap: true,
        tileLayer: 'https://s3.amazonaws.com/noctis-tools-assets/img/map/tiles/{z}/{x}/{y}.jpg',
        tileLayerOptions: {
          tms: true,
          noWrap: true,
          continuousWorld: false
        },
        minZoom: 1,
        maxZoom: 6
      },
      // These are arbitrary ;)
      maxbounds: {
        northEast: {
          lat: 72.71190310803662,
          lng: 140.828125
        },
        southWest: {
          lat: -80.93088543216642,
          lng: -124.45312499999999
        }
      },
      /*
       * Currently unused, but left in to avoid warnings
       */
      events: {
      }
    });
    
    // Toggle save panel visibility
    $scope.savePanelVisible = false;
    $scope.showPanel = function() {
      $scope.savePanelVisible = true;
    };
    $scope.hidePanel = function() {
      $scope.savePanelVisible = false;
    };
    
    // Set a boolean check so the map is only initialized once
    var mapInit = false;
    
    // Get a list of the saved maps
    $http.get('/map/names').success(function(data) {
      $scope.mapNames = data;
    });
    
    /*
     * Leaflet.draw tools
     * Define the shape options here so they can be reused when 
     * loading from the database
     */
    var drawnItems = new L.FeatureGroup(),
        circleOptions = {
          color: '#ff0', // yellow
          fill: true,
          fillColor: '#ff0'
        },
        rectOptions = {
          color: '#0f0', // green
          fill: true,
          fillColor: '#0f0'
        },
        polygonOptions = {
          color: '#f00', // red
          fill: true,
          fillColor: '#f00'
        };
    var drawControl = new L.Control.Draw({
      draw: {
        polyline: {
          shapeOptions: {
            color: '#00f' // blue
          }
        },
        polygon: {
          shapeOptions: polygonOptions
        },
        rectangle: {
          shapeOptions: rectOptions
        },
        circle: {
          shapeOptions: circleOptions
        },
        // Since we're using custom Markers, remove the option from our .draw tools
        marker: false
      },
      edit: {
        featureGroup: drawnItems
      }
    });
    
    /*
     * Map marker icons
     */
    var poiIcon = L.Icon.extend ({
      options: {
        iconSize: [32, 32],
        popupAnchor: [0, -20]
      }
    });
    
    var plantIcon = new poiIcon ({
      iconUrl: 'https://s3.amazonaws.com/noctis-tools-assets/img/map/icons/plant-marker.png',
      iconType: 'plant' // Used when loading markers from the database
    });
    
    function placeMarker(e, icon) {
      leafletData.getMap().then(function(map) {
        var text = prompt("What should the marker say?", "");
        // Return if the prompt is cancelled
        if (text === null) { return; }
        // Get the offset to properly position the marker
        var offset = jQuery('#aa-map').offset();
        var relX = e.pageX - offset.left - 4;
        var relY = e.pageY - offset.top;
        var point = map.containerPointToLatLng([relX, relY]);
        var marker = new L.marker(point, {
          icon: icon,
          draggable: true
        });
        marker.bindPopup(text, { closeButton: false });
        drawnItems.addLayer(marker);
      });
    }
    
    jQuery('.drag').draggable({
      helper: 'clone',
      containment: 'map',
      start: function(e, ui) {
        jQuery('#map-markers').fadeTo('fast', 0.6, function() {});
      },
      stop: function(e, ui) {
        jQuery('#map-markers').fadeTo('fast', 1.0, function() {});
        placeMarker(e, eval(ui.helper.attr('type') + 'Icon'));          
      }
    });
    
    $scope.$watch(function() {
        /* 
         * Hack to refresh the map when the tab is selected
         * Grab the tab through jQuery and check if it is the active tab
         */
        return (jQuery('#Map-tab').hasClass('active'));
      }, function(value) {
        if ((value && !mapInit) === true) {
          mapInit = true;
          leafletData.getMap().then(function(map) {
            map.invalidateSize();
            // Instantiate Leaflet.draw tools
            map.addLayer(drawnItems);
            map.addControl(drawControl);
            
            // Add Leaflet.draw listeners
            map.on('draw:created', function (e) {
              var type = e.layerType,
                  layer = e.layer;

              drawnItems.addLayer(layer);
            });
          });
        }
    });
    
    $scope.saveMap = function() {
      /*
       * We want to save the layers pushed to drawnItems,
       * so we convert them to GeoJSON and stringify them.
       */
      $scope.json = { name: $scope.name, layers: [] };
      var layers = drawnItems._layers;
      for(i in layers) {
        var geoJson = layers[i].toGeoJSON();
        if (layers[i] instanceof L.Marker) {
          /* 
           * Grab our arbitrary iconType option from the Marker icon and 
           * the Marker's Popup content to store in the GeoJSON object 
           * so we can use them when pulling from the database.
           */
          geoJson.properties.iconType = layers[i].options.icon.options.iconType;
          geoJson.properties.popupContent = layers[i].getPopup().getContent();
        } else if (layers[i] instanceof L.Circle) {
          /*
           * Since circles don't exist in the GeoJSON spec, store
           * the circle radius and type in properties to init the circle on load.
           */
          geoJson.properties.radius = layers[i].getRadius();
          geoJson.properties.type = 'circle'; // Not strictly necessary
        } else if (layers[i] instanceof L.Rectangle) {
          geoJson.properties.type = 'rectangle';
        } else if (layers[i] instanceof L.Polygon) {
          geoJson.properties.type = 'polygon';
        } // We can ignore polyline, since it's the default GeoJSON.geometryToLayer() output
        $scope.json.layers.push(JSON.stringify(geoJson));
      }
      $http.post('/map', $scope.json).success(function() {
        console.log('Map saved successfully.');
        $http.get('/map/names').success(function(data) {
          $scope.mapNames = data;
        });
      });
    };
    
    $scope.loadMap = function() {
      drawnItems.clearLayers(); // Clear the current map

      $http.post('/map/load', $scope.name).success(function(data) {
        var layers = data.layers;
        for(i in layers) {
          var geoJson = JSON.parse(layers[i]), 
              layer;
          
          // Cast layer depending on the type value passed in when the layer was saved
          if (geoJson.properties.type && geoJson.properties.type === 'rectangle') {
            var coords = L.GeoJSON.coordsToLatLngs(geoJson.geometry.coordinates, 1);
            layer = L.rectangle(coords, rectOptions);
          } else if (geoJson.properties.type && geoJson.properties.type === 'polygon') {
            var coords = L.GeoJSON.coordsToLatLngs(geoJson.geometry.coordinates, 1);
            layer = L.polygon(coords, polygonOptions);
          } else {
            /*
             * The ILayer output from geometryToLayer() is preferable to factory  
             * geoJson objects due to leaflet.draw not handling GeoJSON layers properly
             * 
             * This case covers Point type layers (Circles, Markers) and Polylines
             */
            layer = L.GeoJSON.geometryToLayer(geoJson, function (feature, latlng) {
              /*
               * Check if the feature being loaded was saved as a circle;
               * if so, return it as such.
               */
              if (feature.properties && feature.properties.type) {
                return L.circle(latlng, feature.properties.radius, circleOptions);
              }
              var icon = new L.Icon.Default;
              /*
               * For each of our markers, use our saved arbitrary properties to 
               * recapture the saved Marker state
               */
              if (feature.properties && feature.properties.iconType) {
                icon = eval(geoJson.properties.iconType + 'Icon');
              }
              // Return the marker, binding the saved popup (if it exists)
              return L.marker(latlng, {
                icon: icon,
                draggable: true
              }).bindPopup(feature.properties.popupContent, { closeButton: false });
            });
          }
          
          drawnItems.addLayer(layer);
        }
      });
    };
  })
  
  .directive('clickOut',
    function($document) {
      return {
        restrict : 'A',
        link : function(scope, elem, attr, ctrl) {
          elem.bind('click', function(e) {
            e.stopPropagation();
          });
          $document.bind('click', function() {
            scope.$apply(attr.clickOut);
          })
        }
      }
    });