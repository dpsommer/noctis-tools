angular.module('login', ['ngCookies'])

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
    })
    
  .controller('LoginCtrl', function($window, $scope, $http, $cookieStore) {
    $scope.user = {};
    $scope.visible = false;
    $scope.login = true;
    $scope.error = '';
    
    $scope.submitUserInformation = function() {
      if ($scope.user.username == null || $scope.user.password == null
          || $scope.user.username === '' || $scope.user.password === '') {
        $scope.error = 'Invalid username or password';
        return;
      } else if (!$scope.user.username.match(/\S+@\S+/)) {
        $scope.error = 'Not a valid email address';
        return;
      } else if (!$scope.login && $scope.user.password != $scope.confirm) {
        $scope.error = 'Passwords do not match!';
        return;
      }
      
      if ($scope.login) {
        $http.post('/user/login/validate', $scope.user).success(function(data) {
          $cookieStore.put('username', $scope.user.username);
          $http.post('/user/session/token', $scope.user.username).success(function(data) {
            $cookieStore.put('sessionToken', data.data);
            $window.location.reload();
          });
        }).error(function(data) {
          $scope.error = data.message;
        });
      } else {
        $http.post('/user/register', $scope.user)
          .success(function(data) {
            $window.location.reload();
          }).error(function(data) {
            $scope.error = data.message;
          });
      }
    };
    
    $scope.showPanel = function() {
      $scope.visible = true;
    };

    $scope.hidePanel = function() {
      $scope.visible = false;
    };
    
    $scope.switchView = function() {
      $scope.login = !$scope.login;
    };
  });