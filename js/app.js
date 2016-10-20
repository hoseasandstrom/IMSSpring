let app = angular.module('IMSSpring', ['ngRoute']);

//controllers
require('./controllers/loginController.js')(app);


//services
require('./services/loginService.js')(app);


app.config(['$routeProvider', function($routeProvider){
  $routeProvider
  .when('/login', {
      controller: 'loginController',
      templateUrl: 'templates/logIn.html',
    })
  .when('/registration', {
      controller: 'loginController',
      templateUrl: 'templates/registration.html',
    })
