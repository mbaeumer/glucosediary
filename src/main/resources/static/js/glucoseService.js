var services = angular.module('services');
services.factory('glucoseService', function($http, hostAddressService){
    return {
        createGlucoseLevel : function(glucose, successCallback, errorCallback){
            $http.post(hostAddressService.hostAddress + 'myglucose', glucose).then(function(response){
                if (response.status == 200){
                    if (response.data.length === 0){
                        errorCallback('Login failed: Wrong username or password!');
                    }else{
                        successCallback(response.data);
                    }
                }else{
                    errorCallback('An unknown error occured');
                }
            });
        },
        getMyGlucoseLevel : function(userId, successCallback, errorCallback){
            $http.get(hostAddressService.hostAddress + 'myglucose/user/' + userId).then(function(response){
                if (response.status == 200){
                    if (response.data.length === 0){
                        errorCallback(response);
                    }else{
                        successCallback(response.data);
                    }
                }else{
                        errorCallback('An unknownerror occured');
                }
            });
        }
    }
})