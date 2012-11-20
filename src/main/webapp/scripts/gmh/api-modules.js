/**
 * Ressources Angulars qui utilisent les services REST publiés par JAX-RS
 */

var apiModule = angular.module('api', [ 'ngResource' ]);

apiModule.factory('GmhApiSearch', function($resource) {

	// Récupération de la ressource : search
	return $resource('api/myapi/search/:key', {}, {
		'search' : {method : 'GET'}
	});

});

apiModule.factory('GmhApiCommiters', function($resource) {

	// Récupération de la ressource : search
	return $resource('api/myapi/owner/:oname/project/:pname', {}, {
		'getCommiters' : {method : 'GET'}
	});

});

