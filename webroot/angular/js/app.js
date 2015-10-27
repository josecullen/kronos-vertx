'use strict';

var app = angular.module('app', [ 'componentsModule','ngAnimate', 'commonDirectives',
		'mapControllers', 'mapServices', 'kronosDirectives', 'dbServices', 'jc-carousel' ]);

app.controller('appCtrl', function($scope, $window, lineaPrueba, $interval) {
	$scope.linea = lineaPrueba;
	$scope.acontecimientos = lineaPrueba.acontecimientos;
	$scope.mapWindow;
	$scope.mapScope;
	$scope.acontecimiento;
	$scope.acontecimientoOverlay;
	
	
	$scope.showImagenes = function(acontecimiento){
		$scope.acontecimiento = acontecimiento;
		$scope.mapScope.acontecimiento = acontecimiento;
		$scope.mapScope.toggleCarousel();
	}
	$scope.$on('showImagenes', function(e, acontecimiento) {
		$scope.showImagenes(acontecimiento);
	});
	
	$scope.prevSlide = function(){
		$scope.mapScope.prevSlide();
	}
	$scope.nextSlide = function(){
		$scope.mapScope.nextSlide();
	}	

	$scope.$on('bcAddOverlay', function(e, acontecimiento) {
		console.log('appCtrl bcAddOverlay');
		$scope.acontecimientoOverlay = acontecimiento;		
		if($scope.acontecimientoOverlay.overlay){
			$scope.mapScope.removeOverlay($scope.acontecimientoOverlay.overlay);
			$scope.$broadcast('removeOverlay', $scope.acontecimientoOverlay.overlay);
			$scope.acontecimientoOverlay.overlay = false;
		}else{
			var overlayInfo = {src: $scope.acontecimientoOverlay.icono, coordenadas: $scope.acontecimientoOverlay.coordenadas};
			$scope.$broadcast('addOverlay', overlayInfo);
			$scope.mapScope.addOverlay(overlayInfo);
		}		
	});
	
	$scope.$on('overlayAdded', function(e, overlayAdded){
		console.log('overlayAdded');
		$scope.acontecimientoOverlay.overlay = overlayAdded;
	});

	$scope.$on('bcFlyTo', function(e, coordenadas) {
		console.log('appCtrl bcAddOverlay');
		$scope.$broadcast('flyTo', coordenadas);
		$scope.mapScope.flyTo(coordenadas);
	});	

	$scope.$on('setAcontecimiento', function(e, acontecimiento) {
		$scope.acontecimiento = acontecimiento;
	});

	$scope.showImagen = function(acontecimiento, imagen) {

	}

	$scope.resources = {
		controlFooter : "angular/partials/control-footer.html",
		controlSidebar : "angular/partials/control-sidebar.html",
		carousel : "angular/partials/carousel.html",
		detailPane : "angular/partials/detail-pane.html"
	}

	$scope.openMap = function() {
		$scope.mapWindow = $window.open("map.html", "mapWindow", "resizable=yes");
		$interval(function() {
			$scope.mapScope = $scope.mapWindow.angular.element("#root").scope();
		}, 1000);
	};
	
	$scope.zoom = 6; 
	$scope.zoomIn = function(){
		$scope.zoom++;
	};
	$scope.zoomOut = function(){
		$scope.zoom--;		
	};
	$scope.$watch('zoom', function(newValue){
		console.log("zoom "+newValue);
		$scope.mapScope.setZoom($scope.zoom);
		$scope.$broadcast('zoom', newValue);
	});
	
	$scope.doZoom = function(zoom) {
		$scope.$broadcast('zoom', zoom);
	}
	
	
//	$scope.linkCenter = function(zoom)

});

app.controller('mapCtrl', function($scope, $window) {
	$scope.carousel = {
		toggleCarousel : false,
		toggleNavs : true
	};
	$scope.setZoom = function(zoom){
		$scope.$broadcast('zoom', zoom);
	}
	
	
	
	$scope.controlScope = window.opener.angular.element('#controlRoot').scope();
	$scope.acontecimiento = {};

	$scope.addOverlay = function(overlayInfo) {
		$scope.$broadcast('addOverlay', overlayInfo);
	}
	$scope.removeOverlay = function(overlay){
		$scope.$broadcast('removeOverlay', overlay);
	}
	
	$scope.flyTo = function(coordenadas) {
		$scope.$broadcast('flyTo', coordenadas);
	}
	
	
	$scope.resources = {
		controlFooter : "angular/partials/control-footer.html",
		controlSidebar : "angular/partials/control-sidebar.html",
		carousel : "angular/partials/carousel.html"
	}

	$scope.toggleCarousel = function() {
		$scope.carousel.toggleCarousel = !$scope.carousel.toggleCarousel;
		$scope.$broadcast('toggleCarousel',
		$scope.carousel.toggleCarousel);
	}
	$scope.toggleCarouselNavs = function() {
		$scope.carousel.toggleNavs = !$scope.carousel.toggleNavs;
		$scope.$broadcast('toggleNavs',
		$scope.carousel.toggleNavs);
	}

	$scope.nextSlide = function() {
		$scope.$broadcast('nextSlide');
	}
	$scope.prevSlide = function() {
		$scope.$broadcast('prevSlide');
	}
	$scope.setSlides = function() {
		$scope.$broadcast('setSlides', $scope.otherSlides);
	}

});

app.controller('imagenesController', function($scope, $window) {
});
