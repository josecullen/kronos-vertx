var mapServices = angular.module('mapServices', ['mapControlServices']);

mapServices.factory('MapInstance', function(view, osmLayer, stamenLayer, localMaps, doZoom, flyTo, addOverlay, removeOverlay){
	var Map = {};	
	
	Map.view = view;
	
	Map.map = new ol.Map({
		target: 'map',
		controls: [],
		layers: [localMaps],
		view: Map.view
	});
		
	Map.doZoom = function(zoom){
		doZoom(Map.map, zoom);
	};
	
	Map.zoomIn = function(){
		Map.doZoom(Map.map.getView().getZoom()+1);
	}
	
	Map.zoomOut = function(){
		Map.doZoom(Map.map.getView().getZoom()-1);
	}
	
	Map.flyTo = function(coordenadas){
		Map.map.once('moveend', function(){
	    	Map.moveEndEvent();
	    });
		flyTo(Map.map, coordenadas);
	};	
	
	Map.addOverlay = function(overlayInfo){
		return addOverlay(Map.map, overlayInfo);
	};	
	
	Map.removeOverlay = function(overlayAdded){
		removeOverlay(Map.map, overlayAdded);
	}
	
	Map.setLayer = function(layer){
		Map.map.removeLayer(Map.map.getLayers()[0]);
		if(layer == 'local'){
			Map.map.addLayer(localMaps);
		}else if(layer == 'stamen'){
			Map.map.addLayer(stamenLayer);
		}else{
			Map.map.addLayer(osmLayer);
		}
	}
	
	Map.moveEndEvent = function(){ console.log("moveend")};
	
    

	
	
	return Map;
});

mapServices.factory('clonedMap', function(view, osmLayer){	
	var Map = {};		
	
	Map.map = new ol.Map({
		target: 'cloneMap',
		layers: [osmLayer],
		view: view
	});	

	return Map;
});

mapServices.factory('view', function(){	
	var zoom = 6;
	var center = ol.proj.transform([ -60, -31 ], 'EPSG:4326', 'EPSG:3857');

	var view = new ol.View({
		center : center,
		zoom: zoom
	});

	return view;
});

mapServices.factory('osmLayer', function(){
	var layer = new ol.layer.Tile({
		source: new ol.source.OSM()
	});
	return layer; 
});

mapServices.factory('stamenLayer', function(){
	var stamenLayer = new ol.layer.Tile({
        source: new ol.source.Stamen({
          layer: 'watercolor'
        })
    });
	return stamenLayer;
});

mapServices.factory('localMaps', function(){
	var newLayer = new ol.layer.Tile({
        source: new ol.source.OSM({
          url: 'C:\\Users\\jcullen\\osm\\osm\\{z}\\{x}\\{y}.png'
        })
    });
	return newLayer;
});

//'/images/watercolor/{z}/{x}/{y}.png'
