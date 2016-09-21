angular.module('services',[]);
var app = angular.module('app', ['services','ngRoute','ngResource', 'ngMaterial', 'ngCookies', 'chart.js']);
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
        .when('/updateGlucose',{
            templateUrl: '/views/createGlucose.html',
            controller: 'updateGlucoseController'
        })
        .when('/showTrend',{
                    templateUrl: '/views/trend.html',
                    controller: 'glucoseTrendController'
         })
         .when('/userTypes',{
             templateUrl: '/views/usertypes.html',
             controller: 'userTypeController'
         })
         .when('/home',{
             templateUrl: '/views/home.html',
             controller: 'homeController'
        })
        .when('/login',{
              templateUrl: '/views/login.html',
              controller: 'loginController'
        })
        .otherwise(
            { redirectTo: '/home'}
        );
});
