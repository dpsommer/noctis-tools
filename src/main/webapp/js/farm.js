angular.module('farm', [])

  .controller('FarmCtrl', function($scope, $http) {
    $scope.timers = [{}];

    // Fetch any active timers
    $http.get('/farm/timer').success(function(data) {
      $scope.timerNotifications = data;
    });
  
    // Fetch the plantable types for groupby sorting
    $http.get('/farm/types').success(function(data) {
      $scope.plantables = data;
    });
    
    /*
     * Uncheck the options when the plantable is switched
     * in order to avoid leaving the values set to true for 
     * plantables without options (ie. seeds with no optimal climate)
     */
    $scope.uncheck = function(timer) {
      timer.optimalClimate = false;
      timer.watered = false;
    };
  
    $scope.addTimers = function() {
      $scope.json = angular.toJson($scope.timers);
      $http.post('/farm/timer', $scope.json).success(function() {
        console.log('Timers successfully added to database.');
        // Fetch the timers we just added
        $http.get('/farm/timer').success(function(data) {
          $scope.timerNotifications = data;
        });
        $scope.timers = [{}];
      });
    };

    $scope.removeTimer = function(timer) {
      /*
       * Remove these so we don't get Jackson errors
       * when reading the object into Java via @RequestBody
       */
      delete timer.finished;
      delete timer.timeLeft;
      $http.post('/farm/timer/remove', timer).success(function() {
        console.log('Timer successfully removed from database.');
        var index = $scope.timerNotifications.indexOf(timer);
        $scope.timerNotifications.splice(index, 1);
      });
    }
  })

  .directive('remainingTime', ['$interval',
    function($interval) {
      return function(scope, element, attrs) {
        var plantTime, growthTime, stopTime; // So that we can cancel the time updates

        function updateTime() {
          // Calculate the time left in ms, then convert
          var timeLeft = scope.tn.plantTime + scope.tn.growthTime - Date.now();
          scope.tn.timeLeft = timeLeft;
          var seconds  = Math.floor((timeLeft / 1000) % 60);
          var minutes  = Math.floor((timeLeft / (1000*60)) % 60);
          var hours    = Math.floor((timeLeft / (1000*60*60)) % 24);
          var days     = Math.floor((timeLeft / (1000*60*60*24)));
        
          var time = "";
          if (timeLeft < 0) {
            time = "Ready now!";
            scope.tn.finished = true;
          } else {
            time = days + "d " + hours + "h " + minutes + "m " + seconds + "s";
            scope.tn.finished = false;
          }
          
          element.text(time);
        }
      
        scope.$watch(attrs.remainingTime, function() {
          updateTime();
        });
        
        stopTime = $interval(updateTime, 1000); // The important part
 
        /* 
         * Listen on DOM destroy (removal) event, and cancel the next UI update
         * to prevent updating time after the DOM element was removed.
         */ 
        element.on('$destroy', function() {
          $interval.cancel(stopTime);
        });
      }
    }]);