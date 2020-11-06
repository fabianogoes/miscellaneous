// mainApp é uma variavel global no arquivo mainApp.js
mainApp.factory('CategoryService', [ '$http', function($http) {

	/**
	 * APP_NAME = é uma variavel global inicializada em mainApp.js
	 */
	var rootUrlCategory = APP_NAME + '/api/category';
	
	var _findAll = function( launchType ){
		return $http.get( rootUrlCategory );
	}	
	
	var _createCategory = function( category ){
		return $http.post( rootUrlCategory, category )
	}
	
	return {
		findAll  : _findAll,
		createCategory : _createCategory 
	};	

}]);