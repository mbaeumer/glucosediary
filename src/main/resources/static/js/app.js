var app = angular.module('app', ['ngRoute','ngResource', 'ngMaterial']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/glucose',{
            templateUrl: '/views/glucosemeasurements.html',
            controller: 'glucoseController'
        })
        .when('/createGlucose',{
                    templateUrl: '/views/createGlucose.html',
                    controller: 'createGlucoseController'
        })
        .when('/userTypes',{
                    templateUrl: '/views/usertypes.html',
                    controller: 'userTypeController'
         })
        .otherwise(
            { redirectTo: '/'}
        );
});
