'use strict';

/* Controllers */


var app = angular.module('swan.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


app.controller('MyCtrl1', ['$scope', 'UserFactory', function ($scope, UserFactory) {
    $scope.bla = 'bla from controller';
    UserFactory.get({}, function (userFactory) {
        $scope.firstname = userFactory.firstName;
    })
}]);



angular.module('F1FeederApp.controllers', []).
controller('driversController', function($scope, ergastAPIservice) {
  $scope.nameFilter = null;
  $scope.driversList = [];

  ergastAPIservice.getDrivers().success(function (response) {
      //Dig into the responde to get the relevant data
      $scope.driversList = response.MRData.StandingsTable.StandingsLists[0].DriverStandings;
  });
});


