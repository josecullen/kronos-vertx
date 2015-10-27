var kronosDirectives = angular.module('kronosDirectives', ['knControllers']);

kronosDirectives.directive('knAcontecimiento', function(){
	return {
		restrict: 'E',
		controller: 'knAcontecimientoCtrl',
		templateUrl : "/angular/views/kn-acontecimiento.html",
		scope:{acontecimiento: '='}
	};
});
