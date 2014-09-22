angular.module('nav-tabs', ['ngAnimate'])
 
  .directive('tabs', function() {
    return {
      restrict: 'E',
      transclude: true,
      scope: {},
      controller: function($scope, $element, $timeout) {
        var panes = $scope.panes = [];
 
        $scope.select = function(pane) {
          angular.forEach(panes, function(pane) {
            pane.selected = false;
          });
          pane.selected = true;
        };
 
        this.addPane = function(pane) {
          if (panes.length === 0) { $scope.select(pane); }
          panes.push(pane);
        };
      },
      template:
        '<div class="tabbable">' +
          '<ul class="nav nav-tabs">' +
            '<li id="{{pane.name}}-tab" ng-repeat="pane in panes" ng-class="{active:pane.selected}">'+
              '<a ng-click="select(pane)">' +
                '<img ng-src="/img/{{pane.name}}.png" class="nav-tab" alt="{{pane.name}}" title="{{pane.name}}"/>' +
              '</a>' +
            '</li>' +
          '</ul>' +
          '<div class="tab-content" ng-transclude></div>' +
        '</div>',
      replace: true
    };
  })
 
  .directive('pane', function() {
    return {
      require: '^tabs',
      restrict: 'E',
      transclude: true,
      scope: { name: '@' },
      link: function(scope, element, attrs, tabsCtrl) {
        tabsCtrl.addPane(scope);
      },
      template:
        '<div class="tab-pane" ng-class="{active: selected, inactive: !selected}" ng-transclude>' +
        '</div>',
      replace: true
    };
  });