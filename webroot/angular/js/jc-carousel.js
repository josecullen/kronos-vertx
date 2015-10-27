var carousel = angular.module('jc-carousel', []); 

carousel.controller('carouselCtrl', function($scope) {
	$scope.carousel = false;
	$scope.navOnOff = true;
	$scope.count = 0;

	$scope.$on('toggleCarousel', function(e, toggle) {
		$scope.carousel = toggle;
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

carousel.directive('jcCarousel', function() {
	return {
		controller: 'carouselCtrl',
		scope : {
			slides : '='
		},
		templateUrl : '/angular/views/jc-carousel.html'
	}
});
