// mainApp é uma variavel global no arquivo mainApp.js
mainApp.controller('BankController', ['BankService', function(BankService){
	
	var self = this;
	self.init = function(){
	};
	
	self.listBanks = [];
	self.bank = {
			id: null, 
			code: '',
			name: '' 
	};
	
	self.populateBanks = function(){
		BankService.findAll( ).then(function(response){
			self.listBanks = response.data;
		});
	}
	
	self.createBank = function(){
		console.log( 'createBank()...' );
		console.log( self.bank )
		var bankResponse = BankService.createBank( self.bank ).success(function(bankSaved){
			self.bank = bankSaved;
			console.log( self.bank );
			sweetAlert("OK", "Banco salvo com sucesso!", "success");
		}).error(function(data, status) {
			console.log( status );
			console.log( data.message );
			sweetAlert("ERRO", data.message, "error");
        });
	}
	
	self.init();
	
}]);