var app = angular.module("authApp", []);
 
app.controller("AuthController", [ '$scope', '$http', function($scope, $http) {
	alert('Hello');
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
     
    $scope.sendPost = function() {
        $http({
            url : 'http://localhost:8080/MiDARe/auth',
            method : "POST",
            data : {
                'pseudo' : $scope.pseudo,
                'password' : $scope.password
            }
        }).then(function(response) {
            console.log(response.data);
            $scope.message = response.data;
        }, function(response) {
            //fail case
            console.log(response);
            $scope.message = response;
        });
 
    };
} ]);