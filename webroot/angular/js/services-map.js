var mapServices = angular.module('mapServices', ['mapControlServices']);

mapServices.factory('MapInstance', function(view, osmLayer,doZoom, flyTo, addOverlay, removeOverlay){
	var Map = {};	
	
	Map.view = view;
	
	Map.map = new ol.Map({
		target: 'map',
		controls: [],
		layers: [osmLayer],
		view: Map.view
	});
		
	Map.doZoom = function(zoom){
		doZoom(Map.map, zoom);
	};
	
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


