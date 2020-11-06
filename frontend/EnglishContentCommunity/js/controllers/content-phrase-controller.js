app.controller('ContentPhraseController', ['$rootScope', function($rootScope){
    $rootScope.session = 'active';
    console.log("Phrase Session = "+$rootScope.session);

    var self = this;
    self.contentType = "phrase";
    self.title = "Content Phrase";
    self.askCaption = "Phrase Ask:";
    self.answerCaption = "Phrase Answer:";
    self.hrefAddContent = "/content/topics/phrase/register";
    self.hrefListContent = "/content/topics/phrase/list";
    self.hrefRegisterCancel = "/content/topics";
    self.defaltImage = "question.png";
    self.contents = [
        {
            ask: "What did you do yesterday?",
            answer: "O que você fez ontem?",
            description: "Simple Question"
        },
        {
            ask: "What's the date today?",
            answer: "Qual é a data de hoje?",
            description: "Simple Question"
        },
        {
            ask: "How are you?",
            answer: "Como você está?",
            description: "Simple Question"
        }
    ];
    
    self.submitContent = function(content){
        console.log(content);
    }

}]);