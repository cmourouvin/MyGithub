// Controleur principal

angular.module('app', ['api','components']).
  config(function($routeProvider) {
    $routeProvider.
      when('/', {controller:IndexCtrl, templateUrl:'views/default.html'}).
      when('/stats', {controller:StatsCtrl, templateUrl:'views/stats.html'}).
      when('/search/:key', {controller:SearchCtrl, templateUrl:'views/search.html'}).
      when('/about', {controller:AboutCtrl, templateUrl:'views/about.html'}).
      otherwise({redirectTo:'/search/'});
});

function IndexCtrl($scope, $location) {
	console.info('Resource : index');
}

function StatsCtrl($scope, $location, GmhApiCommiters) {
	console.info('Resource : Stats');
	
	$scope.getDetails = function () {
		alert ('Details pour ');
	};
	
	console.info ($scope);

//	$scope.commiters = GmhApiCommiters.getCommiters({
//		oname : $routeParams.oname,
//		pname : $routeParams.pname
//	});
}

function SearchCtrl($scope, $routeParams, GmhApiSearch) {
	$scope.repos = GmhApiSearch.search({
		key : $routeParams.key
	});
}

function AboutCtrl($scope, $routeParams) {
	console.info('Resource : About');
}

function ActionFormCtrl($scope, $routeParams, $location) {

	$scope.submit = function() {
		$location.path('/search/' + this.yourSearch);
	};
	
//	$scope.save = function() {
//	    Project.save($scope.project, function(project) {
//	      $location.path('/edit/' + project._id.$oid);
//	    });
//	  }
	
	
	function SomeCtrl($scope) {
		   $scope.blub = "Test Papa !!";
		}
	
	
	function ModalCtrl($scope) {
		   this.setModel = function(data) {
		      $scope.$apply( function() {
		         $scope.data = data;
		      });
		   }
		   $scope.setModel = this.setModel;     
		}
}