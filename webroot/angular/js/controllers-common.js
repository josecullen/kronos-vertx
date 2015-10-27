var componentsModule = angular.module('componentsModule', []);

componentsModule.controller('appCtrl', ['$scope',function($scope, $rootScope){
	$scope.acontecimiento = {
		images: [{}],
		icon: {}
	};
	$scope.names = {
			x : "coordx",
			y : "coordy"
	}
	
	$scope.newImage = function(){
		$scope.acontecimiento.images.push({});
	}
	$scope.removeImage = function(index){
		$scope.acontecimiento.images.splice(index,1);
		console.log("remove");
	}

	
	$scope.$watch('acontecimiento', function(oldV, newV){
		$scope.showAcont = JSON.stringify($scope.acontecimiento);
	}, true);
	$scope.log = function(){
		console.log($scope.acontecimiento);
	}
}]);

componentsModule.controller("imgController", function($scope, $rootScope) {
	$scope.fileNameChanged = function(input, index) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$scope.data.imgSrc = e.target.result;
				$scope.$apply();
			}
			reader.readAsDataURL(input.files[0]);
		}
	};	

	$scope.selectImagen = function(index){		
		$rootScope.imageSelected = $rootScope.acontecimiento.images[index];
	}	
});

