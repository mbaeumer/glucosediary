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

app.controller('createGlucoseController', function($scope, $location) {
    $scope.headingTitle = "create new glucose measurement";
    $scope.myDate = new Date();

    $scope.minDate = new Date(
                   $scope.myDate.getFullYear(),
                   $scope.myDate.getMonth() - 2,
                   $scope.myDate.getDate());
    $scope.maxDate = new Date(
                   $scope.myDate.getFullYear(),
                   $scope.myDate.getMonth() + 2,
                   $scope.myDate.getDate());

    $scope.hours = [{id: 0, value: '00'},{id: 1,value: '01'},{id: 2,value: '02'},
                    {id: 3, value: '03'},{id: 4, value: '04'},{id: 5, value: '05'},{id: 6, value: '06'},
                    {id: 7, value: '07'},{id: 8, value: '08'},{id: 9, value: '09'},{id: 10, value: '10'},
                    {id: 11, value: '11'},{id: 12, value: '12'},{id: 13, value: '13'},{id: 14, value: '14'},
                    {id: 15, value: '15'},{id: 16, value: '16'},{id: 17, value: '17'},{id: 18, value: '18'},
                    {id: 19, value: '19'},{id: 20, value: '20'},{id: 21, value: '21'},{id: 22, value: '22'},
                    {id: 23, value: '23'}];

    $scope.minutes = [{value: '00'},{value: '05'},{value: '10'},
                    {value: '15'},{value: '20'},{value: '25'},{value: '30'},
                    {value: '35'},{value: '40'},{value: '45'},{value: '50'},
                    {value: '55'}];

    var hour = new Date().getHours();
    var minute = Math.floor(new Date().getMinutes() / 5);

    $scope.selectedHour = $scope.hours[hour];
    $scope.selectedMinute = $scope.minutes[minute];

    $scope.save = function(){
    }

    $scope.cancel = function(){
        $location.path("/glucose");
    }

});


app.controller('userTypeController', function($scope, $http) {
    $scope.headingTitle = "User types";
    $http.get('http://localhost:9090/usertypes').
            success(function(data) {
                $scope.entries = data;
                var test = undefined;
            });
});
