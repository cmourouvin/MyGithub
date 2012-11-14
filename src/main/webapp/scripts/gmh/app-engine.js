// Controleur principal

angular.module('app', ['api']).
  config(function($routeProvider) {
    $routeProvider.
      when('/', {controller:IndexCtrl, templateUrl:'views/default.html'}).
      when('/stats', {controller:StatsCtrl, templateUrl:'views/stats.html'}).
      when('/search/:key', {controller:SearchCtrl, templateUrl:'views/search.html'}).
      otherwise({redirectTo:'/'});
});

function IndexCtrl($scope, $location, GmhApi) {
	console.info('Resource : index');
}

function StatsCtrl($scope, $location, GmhApi) {
	console.info('Resource : Stats');
}

function SearchCtrl($scope,$routeParams, GmhApi) {
		$scope.repos = GmhApi.search({key:$routeParams.key});
}


function ActionFormCtrl($scope,$routeParams,$location, GmhApi) {

	$scope.submit = function () {
		$location.path('/search/'+this.yourSearch);
	};
	
//	$scope.save = function() {
//	    Project.save($scope.project, function(project) {
//	      $location.path('/edit/' + project._id.$oid);
//	    });
//	  }
	
}