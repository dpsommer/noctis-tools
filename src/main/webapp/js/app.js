angular.module('app', ['nav-tabs', 'requests', 'login', 'farm', 'map'])
  
  .factory('sessionToken', ['$cookieStore', '$interval', '$http', 
    function($cookieStore, $interval, $http) {
      var sessionToken = $cookieStore.get('sessionToken'), 
          username = $cookieStore.get('username');
      
      function fetchToken() {
        if (username !== null) {
          $http.post('/user/session/token', username).success(function(data) {
            $cookieStore.put('sessionToken', data.data);
            sessionToken = data.data;
          });
        }
      }
      
      $interval(fetchToken, 600000);
      
      return sessionToken;
  }])

  .controller('HeaderCtrl', ['$rootScope', 'sessionToken',
    function($rootScope, sessionToken) {
      $rootScope.sessionToken = sessionToken;
  }])
  
  .controller('GoldCtrl', ['$scope', '$http', '$timeout', 
    function($scope, $http, $timeout) {
      $scope.submitted = false;
    
      $http.get('/guild').success(function(data) {
        $scope.guild = data;
      });
    
      $scope.setFunds = function() {
        $http.post('/guild/funds', $scope.guild).success(function() {
          $scope.submitted = true;
          $timeout(function() {
            $scope.submitted = false;
          }, 3000);
        });
      }
  }])
  
  .controller('UserCtrl', ['$window', '$scope', '$cookieStore', 
    function($window, $scope, $cookieStore) {
      $scope.username = $cookieStore.get('username');
    
      $scope.logout = function() {
        $cookieStore.put('username', null);
        $cookieStore.put('sessionToken', null);
        $window.location.reload();
      };
  }]);