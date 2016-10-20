(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports = function(app) {
    app.controller('loginController', ['$scope', '$http', '$location', 'loginService', function($scope, $http, $location, loginService) {

        $scope.firstname = '';
        $scope.lastname = '';
        $scope.email = '';
        $scope.username = '';
        $scope.password = '';
        $scope.errorMessage = '';
        $scope.error = true;

        $scope.logout = function() {
            Session.clear();
            success(function(response) {
                $location.path('/login');
            }, function(response) {
                $scope.errorMessage = response.data.message;
            });
        };

        $scope.register = function() {
            loginService.registerUser($scope.firstname, $scope.lastname, $scope.email, $scope.username, $scope.password)
                .success(function(response) {
                    $location.path('/');
                }, function(response) {
                    $scope.errorMessage = response.data.message;
                });
        };

        $scope.login = function() {
            loginService.loginUser($scope.username, $scope.password)
                .then(function(response) {
                    console.log('successful')
                    $location.path('/')

                }, function(response) {
                    console.log('unsuccessful');
                    $scope.errorMessage = response.data.message;
                });

        };


    }]);
}

},{}],2:[function(require,module,exports){
let app = angular.module('IMSSpring', ['ngRoute']);

//controllers
require('./controllers/loginController.js')(app);


//services
require('./services/loginService.js')(app);


app.config(['$routeProvider', function($routeProvider){
  $routeProvider
  .when('/login', {
      controller: 'loginController',
      templateUrl: 'templates/login.html',
    })
  .when('/registration', {
      controller: 'loginController',
      templateUrl: 'templates/registration.html',
    })
}])

},{"./controllers/loginController.js":1,"./services/loginService.js":3}],3:[function(require,module,exports){
module.exports = function(app) {
    app.factory('loginService', function($http) {

        let firstname = "";
        let lastname = "";
        let email = "";
        let username = "";
        let password = "";

        let usersArray = [];
        var currentUser;

        logout = function() {
            $http.post('/logout').then(function(data) {
                console.log('logout: ', data);
            })
        }

        return {
            getUser: function() {
                $http({
                    method: 'GET',
                    url: '/users',
                }).then(function(response) {
                    console.log('getting the user', response.data);
                    let userList = response.data
                    angular.copy(userList, usersArray)
                })
                return usersArray;
            },

            registerUser: function(firstname, lastname, email, username, password) {
                return $http({
                    method: 'POST',
                    url: '/register',
                    data: {
                        firstname: firstname,
                        lastname: lastname,
                        email: email,
                        username: username,
                        password: password,
                    }
                });
            },

            loginUser: function(username, password) {
                return $http({
                    method: 'POST',
                    url: '/login',
                    data: {
                        username: username,
                        password: password,
                    }
                }).then(function(response) {
                    if (response.config.data.username === username) {
                        currentUser = response.config.data.username;
                    }
                    return currentUser
                })
            },

            getUsername: function() {
                return currentUser
            },
        }
    })
}

},{}]},{},[2])