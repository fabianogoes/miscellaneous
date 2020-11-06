app.controller('IndexController', ['$rootScope', function ($rootScope) {
    $rootScope.session = 'active';       

    console.log("Index = "+$rootScope.session); 
}]);