var carousel = angular.module('jc-carousel', []); 

carousel.controller('carouselCtrl', function($scope) {
	$scope.carousel = false;
	$scope.navOnOff = true;
	$scope.count = 0;

	$scope.$on('toggleCarousel', function(e, toggle) {
		$scope.carousel = toggle;
		$scope.count = 0;
		$scope.$apply();
	});

	$scope.$on('toggleNavs', function(e, toggle) {
		$scope.navOnOff = toggle;
	});

	$scope.$on('setSlides', function(e, slides) {
		$scope.slides = slides;
	});

	$scope.$on('nextSlide', function() {
		if ($scope.count == ($scope.slides.length - 1)) {
			$scope.count = 0;
		} else {
			$scope.count++;
		}
		$scope.$apply();
	});

	$scope.$on('prevSlide', function() {
		if ($scope.count == 0) {
			$scope.count = ($scope.slides.length - 1);
		} else {
			$scope.count--;
		}
		$scope.$apply();
	});
});

carousel.controller('infoNavCtrl', function($scope) {
	$scope.showInfo = true;
	$scope.titulo = "Título";
	$scope.$on('showInfo', function(){
		console.log("showInfo "+$scope.showInfo);
		$scope.showInfo = !$scope.showInfo;
		$scope.$apply();
	});
});




carousel.directive('jcCarousel', function() {
	return {
		controller: 'carouselCtrl',
		scope : {
			titulo: '=',
			fecha: '=',
			slides : '='
		},
		templateUrl : '/angular/views/jc-carousel.html'
	}
});

carousel.directive('jcInfoNav', function() {
	return {
		controller : 'infoNavCtrl',
		scope : {
			info : '='
		},
		templateUrl : '/angular/views/jc-info-nav.html'
	}
});
