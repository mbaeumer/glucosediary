var app = angular.module('app', ['ngRoute','ngResource']);
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
        .otherwise(
            { redirectTo: '/'}
        );
});
