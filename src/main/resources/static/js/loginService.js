var services = angular.module('services');
services.factory('loginService', function($http, hostAddressService){
    return {
        login : function(credentials, successCallback, errorCallback){
                    $http.post(hostAddressService.hostAddress + '/users/login', credentials).then(function(response){
                        if (response.status == 200){
                            if (response.data.length === 0){
                                errorCallback(response.data);
                            }else{
                                successCallback(response.data);
                            }
                        }else{
                            errorCallback('An unknown error occured - error details(' + response.status + ')');
                        }
                    });
                }
    }
})