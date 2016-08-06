var services = angular.module('services');
services.factory('glucoseService', function($http){
    return {
        createGlucoseLevel : function(glucose, successCallback, errorCallback){
            $http.post('http://localhost:9090/myglucose', glucose).then(function(response){
                if (response.status == 200){
                    if (response.data.length === 0){
                        errorCallback(response);
                    }else{
                        successCallback();
                    }
                }else{
                    errorCallback('An unknown error occured');
                }


            });
        }
    }
})