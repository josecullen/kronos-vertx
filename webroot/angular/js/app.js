'use strict';

var app = angular.module('app', [ 'componentsModule','ngAnimate', 'commonDirectives',
		'mapControllers', 'mapServices', 'kronosDirectives', 'dbServices', 'jc-carousel' ]);

app.controller('appCtrl', function($scope, $window, lineaPrueba, $interval) {
	$scope.resources = {
		controlFooter : "angular/partials/control-footer.html",
		controlSidebar : "angular/partials/control-sidebar.html",
//		carousel : "angular/partials/carousel.html",
		detailPane : "angular/partials/detail-pane.html"
	}
	$scope.zoom = 6; 
	$scope.linea = lineaPrueba;
	$scope.acontecimientos = lineaPrueba.acontecimientos;
	$scope.mapWindow;
	$scope.mapScope;
	$scope.acontecimientoOverlay;	
	
	$scope.openMap = function() {
		$scope.mapWindow = $window.open("map.html", "mapWindow", "resizable=yes");
		$interval(function() {
			$scope.mapScope = $scope.mapWindow.angular.element("#root").scope();
			$scope.mapScope.tituloLinea = $scope.linea.titulo;
		}, 1000);
	};
	
	$scope.showImagenes = function(acontecimiento){
		$scope.acontecimiento = acontecimiento;
		$scope.mapScope.acontecimiento = acontecimiento;
		$scope.mapScope.$apply();
		$scope.mapScope.toggleCarousel();
	}
	
	$scope.showAcontecimientoInfo = function(acontecimiento){
		$scope.acontecimiento = acontecimiento;
		$scope.mapScope.acontecimiento = acontecimiento;
		$scope.mapScope.showAcontecimientoInfo();
	}
	
	
	$scope.flyAndShowImagenes = function(acontecimiento){
		$scope.addOverlay(acontecimiento);
		
		$scope.$broadcast('setMoveEnd', function(){
			$scope.showImagenes(acontecimiento);
		});		
		
		$scope.$broadcast('flyTo', acontecimiento.coordenadas);
		$scope.mapScope.flyTo(acontecimiento.coordenadas);
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

	$scope.$on('bcToggleOverlay', function(e, acontecimiento) {
		console.log('appCtrl bcToggleOverlay');
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
	
	$scope.addOverlay = function(acontecimiento){
		$scope.acontecimientoOverlay = acontecimiento;		

		if(!$scope.acontecimientoOverlay.overlay){
			var overlayInfo = {src: $scope.acontecimientoOverlay.icono, coordenadas: $scope.acontecimientoOverlay.coordenadas};
			$scope.$broadcast('addOverlay', overlayInfo);
			$scope.mapScope.addOverlay(overlayInfo);
		}
	};
	
	$scope.$on('overlayAdded', function(e, overlayAdded){
		console.log('overlayAdded');
		$scope.acontecimientoOverlay.overlay = overlayAdded;
	});

	$scope.flyTo = function(acontecimiento){
		$scope.addOverlay(acontecimiento);
		$scope.mapScope.hideCarousel();
		$scope.$broadcast('setMoveEnd', function(){
			$scope.showAcontecimientoInfo(acontecimiento);
		});
		
		$scope.$broadcast('flyTo', acontecimiento.coordenadas);
		$scope.mapScope.flyTo(acontecimiento.coordenadas);
	}	
	
	$scope.$on('bcFlyTo', function(e, acontecimiento) {		
		$scope.flyTo(acontecimiento);
	});	

	$scope.flyNext = function(){
		var id = $scope.acontecimiento.id;
		for(var i = 0; i < $scope.linea.acontecimientos.length; i++){
			var acont = $scope.linea.acontecimientos[i];
			if(id == acont.id){
				if(i+1 != $scope.linea.acontecimientos.length){
					$scope.acontecimiento = $scope.linea.acontecimientos[i+1];
					$scope.flyTo($scope.acontecimiento);
				}				
			}
		}
	}
	
	$scope.flyPrevious = function(){
		var id = $scope.acontecimiento.id;
		for(var i = 0; i < $scope.linea.acontecimientos.length; i++){
			var acont = $scope.linea.acontecimientos[i];
			if(id == acont.id){
				if(i-1 != -1){
					$scope.acontecimiento = $scope.linea.acontecimientos[i-1];
					$scope.flyTo($scope.acontecimiento);
				}				
			}
		}

	}
	
	
	$scope.$on('setAcontecimiento', function(e, acontecimiento) {
		$scope.acontecimiento = acontecimiento;
	});
	
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

});

app.controller('mapCtrl', function($scope, $window) {
	$scope.resources = {
		controlFooter : "angular/partials/control-footer.html",
		controlSidebar : "angular/partials/control-sidebar.html",
//		carousel : "angular/partials/carousel.html"
	}
	$scope.controlScope = window.opener.angular.element('#controlRoot').scope();
	$scope.tituloLinea = "";
	$scope.acontecimiento = {};
	$scope.showInfo = true;
	
	$scope.carousel = {
		toggleCarousel : false,
		toggleNavs : true
	};

	$scope.addOverlay = function(overlayInfo) {
		$scope.$broadcast('addOverlay', overlayInfo);
	}
	$scope.removeOverlay = function(overlay){
		$scope.$broadcast('removeOverlay', overlay);
	}
	
	$scope.flyTo = function(coordenadas) {
		$scope.$broadcast('flyTo', coordenadas);
	}

	$scope.toggleCarousel = function() {
		$scope.carousel.toggleCarousel = !$scope.carousel.toggleCarousel;
		$scope.$broadcast('toggleCarousel',	$scope.carousel.toggleCarousel);
	}
	
	$scope.hideCarousel = function(){
		$scope.carousel.toggleCarousel = false;
		$scope.$broadcast('toggleCarousel',	$scope.carousel.toggleCarousel);
	}

	
	$scope.showAcontecimientoInfo = function(){	
		$scope.$broadcast('showInfo', {});
	};
	
	
	
	$scope.setZoom = function(zoom){
		$scope.$broadcast('zoom', zoom);
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

app.controller('imagenesController', function($scope, $window) {});
