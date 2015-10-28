var mapControlServices = angular.module('mapControlServices', []);

mapControlServices.factory('doZoom', function(){
	return function(map, zoom){
		var zoomAnimation = ol.animation.zoom({
			resolution: map.getView().getResolution()
		});
		map.beforeRender(zoomAnimation);
		map.getView().setZoom(zoom);
	}
});

mapControlServices.factory('flyTo', function(){
	return function(map, coordenadas){
		var zoom;
		console.log(coordenadas);
		if(coordenadas.length == 3){
			zoom = coordenadas[2];
		}else{
			zoom = 6;
		}
		
		var location = ol.proj.transform(coordenadas, 'EPSG:4326', 'EPSG:3857');
	    
		var bounce = ol.animation.bounce({
	      resolution: map.getView().getResolution() * 3,
	      duration: 2000
	    });
		
	    var pan = ol.animation.pan({
	      source: map.getView().getCenter()
	    });
	    
	    var zoomAnimation = ol.animation.zoom({
	    	resolution: map.getView().getResolution()
	    });
	    

	    
	    map.beforeRender(bounce);
	    map.beforeRender(pan);
	    map.beforeRender(zoomAnimation);	  
	    
	    map.getView().setZoom(zoom);	  
	    map.getView().setCenter(location);
	}	
});

mapControlServices.factory('addOverlay', function(){
	return function(map, overlayInfo){	
		
		var div = document.createElement("div");
		
		var img = document.createElement("img");
		img.src = "images/icons/"+overlayInfo.src;		
		
		div.appendChild(img);
		
		var overlay = new ol.Overlay({
			element : div,
			positioning : 'bottom-center'
		});  	
		
		var pos = ol.proj.transform(overlayInfo.coordenadas, 'EPSG:4326', 'EPSG:3857');
		overlay.setPosition(pos);
		map.addOverlay(overlay);
		return overlay;
	}
});

mapControlServices.factory('removeOverlay', function(){
	return function(map, overlayAdded){
		console.log(map.getOverlays());
		map.removeOverlay(overlayAdded);
		
		map.getOverlays().forEach(function(overlay){
			if(overlay.getPosition()[0] == overlayAdded.getPosition()[0] && overlay.getPosition()[1] == overlayAdded.getPosition()[1]){
				map.removeOverlay(overlay);
			}
		});
		
		console.log(map.getOverlays());
		
	}
});

