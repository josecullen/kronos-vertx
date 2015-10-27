
var mapControllers = angular.module('mapControllers', ['mapDirectives', 'mapServices']);

mapControllers.controller("mapController", function($scope, MapInstance) {
	console.log("mapController");
	
	$scope.$on('zoom', function(e, newZoom) {
		MapInstance.doZoom(newZoom);
	});
	
	$scope.$on('flyTo', function(e, coordenadas) {
		MapInstance.flyTo(coordenadas);
	});
	
	$scope.$on('addOverlay', function(e, overlayInfo) {
		console.log('mapController addOverlay');
		var overlayAdded = MapInstance.addOverlay(overlayInfo);
		$scope.$emit('overlayAdded', overlayAdded);
	});
	
	$scope.$on('removeOverlay', function(e, overlayAdded){
		console.log('mapController removeOverlay');
		//var overlayRemoved = 
		MapInstance.removeOverlay(overlayAdded);
		//$scope.$emit('overlayRemoved', overlayRemoved);
	});

});

mapControllers.controller("cloneMapCtrl", function($scope, clonedMap) {
	
});