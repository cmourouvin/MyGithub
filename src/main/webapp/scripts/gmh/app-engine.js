// Controleur principal

angular.module('app', ['api','components','ui']).
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
	
	$scope.getDetails = function (p,d) {
//		console.info($location);
//		console.info($scope);
//		console.info('owner :');
//		console.info(p);
//		console.info(this.prop);
//		console.info('repo :');
//		console.info(d);
//		console.info(this.depo);
//		this.details = GmhApiCommiters.getCommiters({
//			oname : 'cmourouvin',
//			pname : 'springPoke'
//		});
//		console.info ($scope.details);
//		console.info (this.details);
//		console.info (this.details);

	};
	
	console.info ('-- OUT OF function');
//	console.info ($scope);
	$scope.details = GmhApiCommiters.getCommiters({
		oname : 'cmourouvin',
		pname : 'springPoke'
	});
	
//	$scope.details = "Details !!!!!!!!!!!!!!!!!!! ALO";
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