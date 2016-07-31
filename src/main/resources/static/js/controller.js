app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('glucoseController', function($scope) {
    $scope.headingTitle = "My glucose level";

    $scope.entries = [{id: 0, value: '6.5'},{id: 1,value: '7.2'},{id: 2,value: '8.1'}];


});
