var componentsModule = angular.module('components', []);

componentsModule.directive('myModal', function($interpolate,$rootScope) {
	return {
		restrict : 'E',
		replace:true,
		 scope:{
		 owner:'@',
		 repo: '@'
		 },
		templateUrl : 'views/modal.html',
		compile : function() {
		      return {
		        pre : function(scope, iElement, iAttrs) {
		        }, //this is called before the directive element is attached to the DOM
		        post : function(scope, iElement, iAttrs) { 
		        } //this is called after the directive element is attached to the DOM (same as link)
		      };
		    },
		link : function(scope, iElement, iAttrs) {
			console.info('**************');
			console.log($rootScope);

			iAttrs.$observe('owner', function(value) {
			    console.log('owner has changed value to ' + value);
			  });
			
			iAttrs.$observe('repo', function(value) {
			    console.log('repo has changed value to ' + value);
			  });
			
		}
	};
});


