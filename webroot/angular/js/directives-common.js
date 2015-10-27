var commonDirectives = angular.module('commonDirectives',['componentsModule']);

commonDirectives.directive('jcInput', function(){
	return {
		templateUrl : "/angular/views/jc-input.html",	
		scope: {
			name: '=',
			title: '=',
			value: '=',
			type: '='
		}
	};
});

commonDirectives.directive('jcCoordinates', function(){
	return {
		templateUrl : "/angular/views/jc-coordinates.html",	
		
		scope: {
			name: '=',
			title: '=',
			value: '='
		}
	};
});
commonDirectives.directive('jcFileImage', function(){
	return {
		templateUrl : "/angular/views/jc-file-image.html",	
		transclude: true,
		controllerAs:'appCtrl',
		scope: {
			data: '=',
			type: '='
		}
	};
});




