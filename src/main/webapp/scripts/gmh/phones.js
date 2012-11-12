function PhoneListCtrl($scope) {
  $scope.phones = [
    {"name": "Nexus S",
     "snippet": "Fast just got faster with Nexus S."},
    {"name": "Motorola XOOM™ with Wi-Fi",
     "snippet": "The Next, Next Generation tablet."},
    {"name": "MOTOROLA XOOM™",
     "snippet": "The Next, Next Generation tablet."}
  ];
}

function SearchCtrl($scope, $http) {
	  $http.get('search/spring').success(function(data) {
	    $scope.repos = data.repositories;
	  });
	 
//	  $scope.orderProp = 'name';
	}