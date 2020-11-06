//var app = angular.module('mainApp', []);

var app = angular.module('mainApp', ['ngRoute']);
app.config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/', {
              templateUrl: 'home.html',
              controller: 'IndexController'
            })
            .when('/login', {
              templateUrl: 'login.html',
              controller: 'LoginController'
            })
            .when('/user/register', {templateUrl: 'user-register.html'})
            .when('/user/list', {templateUrl: 'user-list.html'})
            .when('/content/topics', {
              templateUrl: 'content-topics.html',
              controller: 'ContentTopicsController as ctrlTopics'
            })
            .when('/content/topics/phrase/register', {
              templateUrl: 'content-register.html',
              controller: 'ContentPhraseController as ctrlContent'
            })
            .when('/content/topics/phrase/list', {
              templateUrl: 'content-list.html',
              controller: 'ContentPhraseController as ctrlContent'
            })
            .when('/content/topics/word/register', {
              templateUrl: 'content-register.html',
              controller: 'ContentWordController as ctrlContent'
            })
            .when('/content/topics/word/list', {
              templateUrl: 'content-list.html',
              controller: 'ContentWordController as ctrlContent'
            })
            .when('/settings', {templateUrl: 'settings.html'})
            .otherwise({redirectTo: '/'});
    }
]);

app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
  //$locationProvider.html5Mode(true).hashPrefix('!');
}]);