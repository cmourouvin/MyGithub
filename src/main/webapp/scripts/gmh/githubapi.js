angular.module('gmhServices', [ 'ngResource' ]).factory('gmhApi',
		function($resource, $scope) {
			return $resource('search/:key', {}, {
				query : {
					method : 'GET',
					params : {
						key : 'spring'
					},
					isArray : true
				}
			});
		});

function SearchCtrl($scope, gmhApi) {
	$scope.search = gmhApi.query();
}