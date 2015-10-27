var mapDirectives = angular.module('mapDirectives', []);


mapDirectives.directive('jcMap', function(){
	return {
		restrict: 'E',
		templateUrl : "/angular/views/jc-map.html",
		scope:{id: '='}
	};
});

mapDirectives.directive('jcCloneMap', function(){
	return {
		restrict: 'E',
		templateUrl: "/angular/views/jc-clone-map.html"		
	};
});