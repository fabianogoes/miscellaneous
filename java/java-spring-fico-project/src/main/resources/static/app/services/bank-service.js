// mainApp é uma variavel global no arquivo mainApp.js
mainApp.factory('BankService', [ '$http', function($http) {

	/**
	 * APP_NAME = é uma variavel global inicializada em mainApp.js
	 */
	var rootUrlBank = APP_NAME + '/api/bank';
	
	var _findAll = function( launchType ){
		return $http.get( rootUrlBank );
	}	
	
	var _createBank = function( bank ){
		return $http.post( rootUrlBank, bank )
	}
	
	return {
		findAll  : _findAll,
		createBank : _createBank 
	};	

}]);