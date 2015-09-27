var osmLayer = new ol.layer.Tile({
	source : new ol.source.OSM()
});
var stamenLayer = new ol.layer.Tile({
    source: new ol.source.Stamen({
      layer: 'watercolor'
    })
  });
var birmingham = ol.proj.transform([ 2.08, 48.66 ], 'EPSG:4326', 'EPSG:3857');

var view = new ol.View({
	center : birmingham,
	zoom : 1
});
var map = new ol.Map({
	target : 'map',
	controls : [],
	view : view
});
var newLayer = new ol.layer.Tile({
	source : new ol.source.OSM({
		url : '/images/osm/{z}/{x}/{y}.png'
	})
});

map.on('moveend', function(e){
//	console.log(e);	
//	console.log(view.getZoom());
});


map.addLayer(osmLayer);
map.addLayer(stamenLayer);

//map.addLayer(newLayer);

function changeMapSrc(cb) {
	console.log("changeMapSrc");
	if (cb.checked) {
		newLayer.setVisible(true);
		osmLayer.setVisible(false);
	} else {
		newLayer.setVisible(false);
		osmLayer.setVisible(true);
	}
}

function doBounce(acontecimiento, showNavs) {
	var location = ol.proj.transform(acontecimiento.coordenadas, 'EPSG:4326', 'EPSG:3857');
    
	// bounce by zooming out one level and back in
	var bounce = ol.animation.bounce({
      resolution: map.getView().getResolution() * 3,
      duration: 2000
    });
	
    // start the pan at the current center of the map
    var pan = ol.animation.pan({
      source: map.getView().getCenter()
    });
    
    var zoomAnimation = ol.animation.zoom({
    	resolution: map.getView().getResolution()
    });
    if(showNavs){
    	map.once('moveend', function(){
           	console.log("moveEnd");     
           	var topNav = $('#topNav');
        	var bottomNav = $('#bottomNav');
        
    	    topNav.addClass('topNav');
    	    bottomNav.addClass('bottomNav');
        
    	    topNav.bind('webkitAnimationEnd onAnimationEnd onanimationend msAnimationEnd animationend',
    	      function(e) {
    	      	console.log("fin");
    	        topNav.removeClass('topNav');
    	    });
    	    
    	    bottomNav.bind('webkitAnimationEnd onAnimationEnd onanimationend msAnimationEnd animationend',
    	      function(e) {
    	        console.log("fin");
    	        bottomNav.removeClass('bottomNav');
    	    });     
    	});

    }
    
    
    map.beforeRender(bounce);
    map.beforeRender(pan);
    map.beforeRender(zoomAnimation);
    //map.beforeRender(after);
    
    view.setZoom(acontecimiento.zoom);
    // when we set the center to the new location, the animated move will
    // trigger the bounce and pan effects
    map.getView().setCenter(location);
}
  
function doPan(location) {
    // pan from the current center
    var pan = ol.animation.pan({
      source: map.getView().getCenter()
    });
    map.beforeRender(pan);
    // when we set the new location, the map will pan smoothly to it
    map.getView().setCenter(location);
}
 
 // Con true aumenta el zoom, con false decrementa
function setZoom(zoomIn){
 	var zoom = ol.animation.zoom({
    	resolution: map.getView().getResolution()
    });
    map.beforeRender(zoom);
    if(zoomIn){
    	view.setZoom(view.getZoom()+1);
    }else{
    	view.setZoom(view.getZoom()-1);
    }        
}	


function addOverlay(coord){
	var div = document.createElement("div");
	var img = document.createElement("img");
	img.src = "images/icon.png";
	div.appendChild(img);
	
	var overlay = new ol.Overlay({
		element : div,
		positioning : 'bottom-center'
	});  	
	var pos = ol.proj.transform(coord, 'EPSG:4326', 'EPSG:3857');
	overlay.setPosition(pos);
	map.addOverlay(overlay);
	return overlay;
}

function removeOverlay(overlay){
	map.removeOverlay(overlay);
}