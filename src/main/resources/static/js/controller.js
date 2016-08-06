app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('glucoseController', function($scope, $http, glucoseService) {
    $scope.headingTitle = "My glucose level";

    $scope.successReadCallback = function(data){
        $scope.entries = data;
    }

    $scope.errorReadCallback = function(message){
        $scope.errorMessage = message;
    }

    glucoseService.getMyGlucoseLevel(1, $scope.successReadCallback, $scope.errorReadCallback);
});

app.controller('createGlucoseController', function($scope, $location, glucoseService) {
    $scope.headingTitle = "create new glucose measurement";
    $scope.myDate = new Date();
    $scope.errorMessage='';

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

    $scope.user = {userId : 1};
    $scope.glucoseMeasurement = { measureDate : new Date(), glucoseValue: 5.0, user : $scope.user};
    $scope.glucoseMeasurement.user.id = 1;

    var hour = new Date().getHours();
    var minute = Math.floor(new Date().getMinutes() / 5);
    $scope.selectedHour = $scope.hours[hour];
    $scope.selectedMinute = $scope.minutes[minute];

    $scope.successCreationCallback = function(){
        $location.path("/glucose");
    }

    $scope.errorCreationCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.save = function(){
        var theDate = $scope.myDate;
        theDate.setHours($scope.selectedHour.value);
        theDate.setMinutes($scope.selectedMinute.value)
        $scope.glucoseMeasurement.measureDate = theDate;
        glucoseService.createGlucoseLevel($scope.glucoseMeasurement, $scope.successCreationCallback, $scope.errorCreationCallback);
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
