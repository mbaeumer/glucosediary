app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('glucoseController', function($scope, $http) {
    $scope.headingTitle = "My glucose level";
    $http.get('http://localhost:9090/myglucose').
                success(function(data) {
                    $scope.entries = data;
                    var test = undefined;
                });
});

app.controller('createGlucoseController', function($scope) {
    $scope.headingTitle = "create new glucose measurement";
    //$scope.entries = [{id: 0, value: '6.5'},{id: 1,value: '7.2'},{id: 2,value: '8.1'}];
    $scope.myDate = new Date();
    $scope.minDate = new Date(
                   $scope.myDate.getFullYear(),
                   $scope.myDate.getMonth() - 2,
                   $scope.myDate.getDate());
    $scope.maxDate = new Date(
                   $scope.myDate.getFullYear(),
                   $scope.myDate.getMonth() + 2,
                   $scope.myDate.getDate());

});


app.controller('userTypeController', function($scope, $http) {
    $scope.headingTitle = "User types";
    $http.get('http://localhost:9090/usertypes').
            success(function(data) {
                $scope.entries = data;
                var test = undefined;
            });
});
