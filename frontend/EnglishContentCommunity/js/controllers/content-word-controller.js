app.controller('ContentWordController', ['$rootScope', function($rootScope){
    $rootScope.session = 'active';
    console.log("Word Session = "+$rootScope.session);

    var self = this;
    self.contentType = "word";
    self.title = "Content Word";
    self.askCaption = "Word Ask:";
    self.answerCaption = "Word Answer:";
    self.hrefAddContent = "/content/topics/word/register";
    self.hrefListContent = "/content/topics/word/list";
    self.hrefRegisterCancel = "/content/topics";
    self.defaltImage = "words.png";
        self.contents = [
        {
            ask: "Ask",
            answer: "Perguntar",
            description: "Words"
        },
        {
            ask: "Answer",
            answer: "Responda",
            description: "Words"
        },
        {
            ask: "Mistake",
            answer: "Erro",
            description: "Words"
        }
    ];

    self.submitContent = function(content){
        console.log(content);
    }

}]);