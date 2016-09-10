var services = angular.module('services');
services.factory('glucoseService', function($http, $cookies, hostAddressService){
    return {
        currentGlucose: undefined,
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
                        errorCallback('An unknown error occurred');
                }
            });
        },
        getSingleGlucoseMeasurement : function(glucoseId, successCallback, errorCallback){
            $http.get(hostAddressService.hostAddress + 'myglucose/get/' + glucoseId).then(function(response){
                 if (response.status == 200){
                     if (response.data.length === 0){
                         errorCallback(response);
                     }else{
                         successCallback(response.data);
                     }
                 }else{
                         errorCallback('An unknown error occurred');
                 }
             });
        },
        updateGlucoseMeasurement : function(glucose, successCallback, errorCallback){
            $http.put(hostAddressService.hostAddress + 'myglucose', glucose).then(function(response){
                 if (response.status == 200){
                     if (response.data.length === 0){
                         errorCallback(response);
                     }else{
                         successCallback(response.data);
                     }
                 }else{
                         errorCallback('An unknown error occurred');
                 }
             });
        },
        deleteGlucoseMeasurement : function(glucoseId, successCallback, errorCallback){
            $http.delete(hostAddressService.hostAddress + 'myglucose' + "/" + glucoseId).then(function(response){
                 if (response.status == 200){
                     successCallback();
                 }else{
                     errorCallback('An unknown error occurred');
                 }
             });
        }
    }
})