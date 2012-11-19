var componentsModule = angular.module('components', []);

//componentsModule.directive('myModal', function () {
//    return {
//        restrict:'E',
//		replace:true,
//		template: '<span> MY MODAL HERE {{owner}} for {{repo}} </span>',
//		scope: { owner:'@owner',
//				 repo: '@repo'},
////		templateUrl: 'compo/modal.html',
////		link : function (scope, iElement, iAttrs) {
////			console.info ('scope : '+ scope.value);
////			console.info ('element : ' +element.value);
////			console.info ('attribs : ' + attrs.value);
////			console.info ('OWNER : ' + owner);
////			console.info ('REPO : ' + repo);
////			console.log ('---- Test Scope : ' + iAttrs.owner + ' and repo : ' + iAttrs.repo );
////		}
//				 
//				 link: function (scope, iElement, iAttrs) {
////					 alert('toto : ' + attrs );
////					 console.info('consoleInfo : ' + iAttrs );
//					 console.info('------');
//					 console.log(iAttrs);
//					 console.log(iAttrs.$get('owner'));
//					 console.log(iAttrs.repo);
//			            // Title element
////			        var title = angular.element(element.children()[0]),
////			            // Opened / closed state
////			            opened = true;
////			 
////			        // Clicking on title should open/close the zippy
////			        title.bind('click', toggle);
////			 
////			        // Toggle the closed/opened state
////			        function toggle() {
////			          opened = !opened;
////			          element.removeClass(opened ? 'closed' : 'opened');
////			          element.addClass(opened ? 'opened' : 'closed');
////			        }
////			 
////			        // initialize the zippy
////			        toggle();
//			      }
//    };
//});


componentsModule.directive('myModal', function($interpolate) {
	return {
		restrict : 'E',
		replace:true,
		 scope:{
		 owner:'@',
		 repo: '@'
		 },
		templateUrl : 'views/modal.html',
		link : function(scope, iElement, iAttrs) {
//			console.info('------');
//			console.log(iAttrs);
//			console.log(iAttrs.owner);
//			console.log(iAttrs.repo);
////			var v1 = $interpolate(iAttrs.owner);
////			var v2 = $interpolate(iAttrs.repo);
////			console.info('+++++++++++');
////			console.log(v1);
////			console.log(v2);
			console.info('**************');
			console.log(scope);

			iAttrs.$observe('owner', function(value) {
			    console.log('owner has changed value to ' + value);
			  });
			
			iAttrs.$observe('repo', function(value) {
			    console.log('repo has changed value to ' + value);
			  });
		}
	};
});


