angular.module('api', [ 'ngResource' ]).factory('GmhApi', function($resource) {

	// Récupération de la ressource : search
	return $resource('api/myapi/search/:key', {}, {
		'search' : {method : 'GET'}
	});

//	gmhApiSearch.prototype.search = function(cb) {
//		return Project.update({
//			id : this._id.$oid
//		}, angular.extend({}, this, {
//			_id : undefined
//		}), cb);
//	};

//	return gmhApiSearch;
});