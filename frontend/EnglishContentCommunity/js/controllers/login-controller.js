app.controller('LoginController', ['$rootScope', function($rootScope){
    $rootScope.session = null;

    console.log("Login = "+$rootScope.session);
}]);