// mainApp é uma variavel global no arquivo mainApp.js
mainApp.factory('LaunchService', [ '$http', function($http) {

	/**
	 * APP_NAME = é uma variavel global inicializada em mainApp.js
	 */
	var rootUrlLaunch = APP_NAME + '/api/launch';
	
	var _postLaunch = function( launch ){
		return $http.post( rootUrlLaunch, launch );
	}	
	
	var _findAll = function(){
		return $http.get( rootUrlLaunch );
	}
	
	var _findByType = function( launchType ){
		return $http.get( rootUrlLaunch + '/type/' + launchType  );
	}
	
	var _deleteLaunch = function( id ){
		return $http.get( rootUrlLaunch + "/delete/" + id );
	}
	
	var _findLaunch = function( id ){
		return $http.get( rootUrlLaunch + "/" + id );
	}
	
	var _payLaunch = function( id ){
		return $http.get( rootUrlLaunch + "/pay/" + id );
	}
	
	var _findLates = function(){
		return $http.get( rootUrlLaunch + "/lates" );
	}
	
	var _getLatePerc = function(){
		return $http.get( rootUrlLaunch + "/lates/perc" );
	}
	
	var _getDonePerc = function(){
		return $http.get( rootUrlLaunch + "/done/perc" );
	}
	
	var _getWaitingPerc = function(){
		return $http.get( rootUrlLaunch + "/waiting/perc" );
	}
	
	return {
		postLaunch     : _postLaunch,
		findAll        : _findAll,
		deleteLaunch   : _deleteLaunch,
		findLaunch     : _findLaunch,
		payLaunch      : _payLaunch,
		findByType     : _findByType,
		findLates	   : _findLates,
		getLatePerc    : _getLatePerc,
		getDonePerc    : _getDonePerc,
		getWaitingPerc : _getWaitingPerc
	};	

}]);