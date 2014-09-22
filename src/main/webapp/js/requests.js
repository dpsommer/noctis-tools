angular.module('requests', [])

  .controller('RequestCtrl', function($scope, $http) {
    $scope.newRequest = { 
        items: [{}]
    };

    $scope.addRequest = function() {
      $http.post('/guild/requests', $scope.newRequest).success(function() {
        getRequests();
      });
    };
    
    $scope.contribute = function(request, item) {
      delete request.dateParsed;
      var amount = prompt('How many will you contribute?', ''),
          item = request.items[request.items.indexOf(item)];
      item.amount = (+amount > item.amount ? 0 : item.amount - amount);
      request.items[request.items.indexOf(item)] = item;
      $http.post('/guild/requests/update', request).success(function() {
        console.info("Successfully updated request");
      });
    };
    
    $scope.deleteRequest = function(request) {
      $scope.requests.splice($scope.requests.indexOf(request), 1);
      $http.post('/guild/requests/delete', request).success(function() {
        console.info("Successfully deleted request");
      });
    };

    function getRequests() {
      $http.get('/guild/requests').success(function(data) {
        $scope.requests = data;
        for (i in $scope.requests) {
          var date = $scope.requests[i].datePosted;
          $scope.requests[i].dateParsed = (new Date(date)).toString('dddd, MMMM d, yyyy');
        }
      });
    }
    
    getRequests();
  })

  .directive('request', function() {
    return {
      restrict: 'E',
      transclude: true,
      scope: {
        contribute : '&',
        request    : '='
      },
      template:
        '<div class="request">' +
          '<h3 class="request-title">{{request.title}}</h2>' +
          '<div>' +
            '<div ng-repeat="item in request.items">' +
              '<input class="btn-primary btn-inline" type="button" ng-click="contribute({request:request, item:item})" value="Contribute!"/>' +
              '<div  class="request-items">' +
                '<p style="float:left">{{item.name}}</p>' +
                '<p style="float:right"> x {{item.amount}} </p>' +
                '<br/>' +
              '</div>' +
            '</div>' +
          '</div>' + 
          '<p class="request-attribution">' + 
            'posted by {{request.requester}} on {{request.dateParsed}}' + 
          '</p>' +
        '</div>',
      replace: true
    };
  });