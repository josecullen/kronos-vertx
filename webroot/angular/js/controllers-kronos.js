
var kronosControllers = angular.module('knControllers', ['mapDirectives', 'mapServices']);

kronosControllers.controller("knAcontecimientoCtrl", function($scope) {
	$scope.addOverlay = function(acontecimiento){
		console.log("kronos addOverlay");
		//var overlayInfo = {src: src, coordenadas: coordenadas};
		$scope.$emit('bcAddOverlay', acontecimiento);
	};
	$scope.flyTo = function(coordenadas){
		$scope.$emit('bcFlyTo', coordenadas);
	}
	$scope.showImagenes = function(acontecimiento){
		$scope.$emit('showImagenes', acontecimiento);
	}
	
	$scope.setAcontecimiento = function(acontecimiento){
		$scope.$emit('setAcontecimiento', acontecimiento);
	}
	
	
});

