app.controller('ContentTopicsController', ['$rootScope', function($rootScope){
    $rootScope.session = 'active';
    console.log("Topics Session = "+$rootScope.session);

    var self = this;

    self.topics = [
        {
            name: "Content Phrases", 
            description: "Phrases to Learn",
            href: '/content/topics/phrase/list'
        },
        {
            name: "Content Words", 
            description: "Words to Learn",
            href: '/content/topics/word/list'
        }
    ];

}]);