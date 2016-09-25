app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('rootController', function($scope, $location, cookieUtilService, loginService) {
    $scope.headingTitle = "User List";

    $scope.isLoggedIn = function(){
        return cookieUtilService.isCookieValid();
    }

    $scope.logout = function(){
        cookieUtilService.invalidateCookies();
        $location.path('/home');
    }
});

app.controller('glucoseController', function($scope, $http, $location, glucoseService, cookieUtilService) {
    $scope.headingTitle = "My glucose level";

    $scope.isLoggedIn = cookieUtilService.isCookieValid();
    if (!cookieUtilService.isCookieValid()){
        $location.path('/login');
    }else{
        cookieUtilService.extendCookie();
    }

    $scope.successReadAllCallback = function(data){
        $scope.entries = data;
    }

    $scope.errorReadAllCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.successReadSingleCallback = function(data){
        glucoseService.currentGlucose = data;
        $scope.glucoseMeasurement = data;
        $location.path("/updateGlucose");
    }

    $scope.errorReadSingleCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.updateGlucose = function(id){
        glucoseService.getSingleGlucoseMeasurement(id, $scope.successReadSingleCallback, $scope.errorReadSingleCallback);
    }

    $scope.successReadSingleForDeletionCallback = function(data){
        glucoseService.currentGlucose = data;
        glucoseService.deleteGlucoseMeasurement(data.id, $scope.successDeletionCallback, $scope.errorDeletionCallback);
    }

    $scope.successDeletionCallback = function(){
        glucoseService.getMyGlucoseLevel(cookieUtilService.getUserId(), $scope.successReadAllCallback, $scope.errorReadAllCallback);
    }

    $scope.errorDeletionCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.deleteGlucose = function(id){
        glucoseService.getSingleGlucoseMeasurement(id, $scope.successReadSingleForDeletionCallback, $scope.errorReadSingleCallback);
        //glucoseService.deleteGlucoseMeasurement(id, $scope.successDeletionCallback, $scope.errorDeletionCallback);
    }

    glucoseService.getMyGlucoseLevel(cookieUtilService.getUserId(), $scope.successReadAllCallback, $scope.errorReadAllCallback);
});

app.controller('createGlucoseController', function($scope, $location, glucoseService, cookieUtilService) {
    $scope.headingTitle = "create new glucose measurement";
    $scope.myDate = new Date();
    $scope.errorMessage='';

    $scope.isLoggedIn = cookieUtilService.isCookieValid();

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

    $scope.user = {id : cookieUtilService.getUserId()};
    $scope.glucoseMeasurement = { measureDate : new Date(), glucoseValue: 5.0, user : $scope.user};

    var hour = new Date().getHours();
    var minute = Math.floor(new Date().getMinutes() / 5);
    $scope.selectedHour = $scope.hours[hour];
    $scope.selectedMinute = $scope.minutes[minute];

    if (!cookieUtilService.isCookieValid()){
        $location.path('/login');
    }else{
        cookieUtilService.extendCookie();
    }

    $scope.successCreationCallback = function(){
        $location.path("/glucose");
    }

    $scope.errorCreationCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.save = function(){
        $scope.errorMessage = '';
        var theDate = $scope.myDate;
        theDate.setHours($scope.selectedHour.value);
        theDate.setMinutes($scope.selectedMinute.value)
        $scope.glucoseMeasurement.measureDate = theDate;

        if (isNaN($scope.glucoseMeasurement.glucoseValue)){
            $scope.errorMessage = 'The entered value is not valid!';

            if ($scope.glucoseMeasurement.glucoseValue.indexOf('') >= 0){
                $scope.errorMessage = 'This input is not allowed!';
            }
        }else{
            if ($scope.glucoseMeasurement.glucoseValue < -1){
                $scope.errorMessage = 'The value must not be negative!';
            }

            if ($scope.glucoseMeasurement.glucoseValue < 3 || $scope.glucoseMeasurement.glucoseValue > 30 ){
                $scope.errorMessage = 'The value is too low or too high!';
            }
        }

        if ($scope.errorMessage === ''){
            glucoseService.createGlucoseLevel($scope.glucoseMeasurement, $scope.successCreationCallback, $scope.errorCreationCallback);
        }
    }

    $scope.cancel = function(){
        $location.path("/glucose");
    }

});

app.controller('updateGlucoseController', function($scope, $location, glucoseService, cookieUtilService) {
    $scope.headingTitle = "Update glucose measurement";
    $scope.myDate = new Date();
    $scope.errorMessage='';

    $scope.isLoggedIn = cookieUtilService.isCookieValid();

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

    $scope.user = {id : cookieUtilService.getUserId()};
    if ($scope.glucoseMeasurement === undefined){
        $scope.glucoseMeasurement = glucoseService.currentGlucose;
        $scope.glucoseMeasurement.measureDate = new Date($scope.glucoseMeasurement.measureDate);
        $scope.myDate = new Date($scope.glucoseMeasurement.measureDate);
    }

    var hour = $scope.glucoseMeasurement.measureDate.getHours();
    var minute = Math.floor($scope.glucoseMeasurement.measureDate.getMinutes() / 5);
    $scope.selectedHour = $scope.hours[hour];
    $scope.selectedMinute = $scope.minutes[minute];

    if (!cookieUtilService.isCookieValid()){
        $location.path('/login');
    }else{
        cookieUtilService.extendCookie();
    }

    $scope.successUpdateCallback = function(){
        $location.path("/glucose");
    }

    $scope.errorUpdateCallback = function(message){
        $scope.errorMessage = message;
    }

    $scope.save = function(){
        $scope.errorMessage = '';
        var theDate = $scope.myDate;
        theDate.setHours($scope.selectedHour.value);
        theDate.setMinutes($scope.selectedMinute.value)
        $scope.glucoseMeasurement.measureDate = theDate;

        if (isNaN($scope.glucoseMeasurement.glucoseValue)){
            $scope.errorMessage = 'The entered value is not valid!';

            if ($scope.glucoseMeasurement.glucoseValue.indexOf('') >= 0){
                $scope.errorMessage = 'This input is not allowed!';
            }
        }else{
            if ($scope.glucoseMeasurement.glucoseValue < -1){
                $scope.errorMessage = 'The value must not be negative!';
            }

            if ($scope.glucoseMeasurement.glucoseValue < 3 || $scope.glucoseMeasurement.glucoseValue > 30 ){
                $scope.errorMessage = 'The value is too low or too high!';
            }
        }

        if ($scope.errorMessage === ''){
            glucoseService.updateGlucoseMeasurement($scope.glucoseMeasurement, $scope.successUpdateCallback, $scope.errorUpdateCallback);
        }
    }

    $scope.cancel = function(){
        $location.path("/glucose");
    }

});

app.controller('userTypeController', function($scope, $http, hostAddressService) {
    $scope.headingTitle = "User types";
    $http.get(hostAddressService.hostAddress + 'usertypes').
            success(function(data) {
                $scope.entries = data;
            });
});

app.controller('homeController', function($scope, cookieUtilService) {
    $scope.headingTitle = "Welcome to GlucometriQ!";
    $scope.isLoggedIn = cookieUtilService.isCookieValid();
    $scope.username = cookieUtilService.getUserName();
});

app.controller('loginController', function($scope, $location, $cookies, $cookieStore, loginService, cookieUtilService) {
    $scope.headingTitle = "Login";
    $scope.username = '';
    $scope.password = '';

    $scope.login = function(){
        $scope.credentials = {};
        $scope.credentials.username = $scope.username;
        $scope.credentials.password = $scope.password;
        loginService.login($scope.credentials, $scope.loginSuccessCallback, $scope.loginErrorCallback);
    }

    $scope.loginSuccessCallback = function(data){
        cookieUtilService.initCookies(data);
        $location.path("/glucose");
    }

    $scope.loginErrorCallback = function(data){
        $scope.errorMessage = data;
    }
});

app.controller('glucoseTrendController', function($scope, cookieUtilService, $location, glucoseService) {
    $scope.headingTitle = "Your glucose trend";
    $scope.isLoggedIn = cookieUtilService.isCookieValid();
    $scope.username = cookieUtilService.getUserName();

    $scope.successReadAllCallback = function(data){
        $scope.entries = data;
        var dates = [];
        var glucoseMeasurements = [];
        var arrayLength = $scope.entries.length;
        for (var i = 0; i < arrayLength; i++) {
            dates.push(new Date($scope.entries[i].measureDate).toString("yyyy-MM-dd HH:mm"));
            glucoseMeasurements.push($scope.entries[i].glucoseValue);
            //$scope.entries[i];
        }
        $scope.labels = dates; //['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
        $scope.series = ['Series A', 'Series B'];

        $scope.data = [ glucoseMeasurements
          //[6.5, 5.9, 8.0, 8.1, 5.6, 5.5, 9.0]
        ];
    }

    $scope.errorReadAllCallback = function(message){
        $scope.errorMessage = message;
    }

    glucoseService.getMyGlucoseLevel(cookieUtilService.getUserId(), $scope.successReadAllCallback, $scope.errorReadAllCallback);


});
