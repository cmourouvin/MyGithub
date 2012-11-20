/**
 * Controleur principal :
 * - contient le route provider qui controle les demandes de ressource
 * - plusieurs controleurs gérants des zones des pages html
 */

angular.module('app', ['api','components','ui']).
  config(function($routeProvider) {
    $routeProvider.
      when('/stats/:owner/repo/:repo', {controller:StatsCtrl, templateUrl:'views/stats.html'}).
      when('/search/:key', {controller:SearchCtrl, templateUrl:'views/search.html'}).
      when('/about', {templateUrl:'views/about.html'}).
      when('/404',{templateUrl:'views/error.html'}).
      otherwise({redirectTo:'/search/'});
});


function StatsCtrl($scope, $location, $routeParams, GmhApiCommiters) {
	
	// Définition de la fonction de redirection pour chaque demande de statistique
	// sur une ligne de recherche
	$scope.getDetails = function (repo) {
		$location.path('/stats/' + repo.owner + '/repo/' + repo.name);
	};
	
	if  ( $routeParams.owner != null ) {
		$scope.collabs = GmhApiCommiters.getCommiters({
			oname : $routeParams.owner,
			pname : $routeParams.repo
		});
	}
	
}


function SearchCtrl($scope, $routeParams, GmhApiSearch) {
	
	// Appel à la ressource de recher
	if ( $routeParams.key ) {
		$scope.repos = GmhApiSearch.search({
			key : $routeParams.key
		});
	}

}

function AboutCtrl($scope, $routeParams) {
}

function ActionFormCtrl($scope, $routeParams, $location) {

	// Action de redirection lors du submit du formulaire de recherche
	$scope.submit = function() {
		$location.path('/search/' + this.yourSearch);
	};

}

