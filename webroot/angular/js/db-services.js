var dbServices = angular.module('dbServices', []);

dbServices.factory('lineaPrueba', function(){
	var linea = {
		titulo: "Primera Invasión Inglesa",
		acontecimientos: [{
			id: 1,
			titulo: "Ataque de los ingleses",
			icono: "sables-sm.png",
			coordenadas: [-58.32, -34.61, 12],
			fecha: new Date(1806,5,27),
			imagenes: [{
				titulo: "William Carr Beresford: comandante de las fuerzas inglesas",
				contenido: "William Carr Beresford comandaba unos 1560 soldados ingleses, que desembarcaron en la localidad de Quilmes.",
				src: "/images/user/beresford.jpg"
			},{
				titulo: "Primera Invasión Inglesa",
				contenido: "Las fuerzas del virreynato son escasas y no pueden hacer nada para defenderse. Las fuerzas inglesas llegan sin inconvenientes hasta el fuerte, la actual casa rosada.",
				src: "/images/user/fuerte-bsas.jpg"
			}]			
		},{
			id: 13,
			titulo: "Huída de Sobremonte",
			icono: "carruaje-sm.png",
			coordenadas: [-62.30, -33.64, 10],
			fecha: new Date(1806,5,24),
			imagenes:[{
				titulo: "Rafael de Sobremonte huye a Córdoba",
				contenido: "El virrey Rafael Sobremonte escapa con todas las riquezas, dejando la capital a merced de los invasores. Los ingleses lo alcanzan, camino a Córdoba.",
				src: "/images/user/sobremonte.jpg"
			}]
		},{
			id: 3,
			titulo: "Batalla por la reconquista",
			icono: "sables-sm.png",
			coordenadas: [-58.32, -34.61, 12],
			fecha: new Date(1806,7,12),
			imagenes: [{
				titulo: "Santiago Liniers: lider de la reconquista",
				contenido: "Santiago Liniers cuenta con alrededor de 1000 hombres, pero mientras se acerca al fuerte, más hombres se van sumando a los que será una batalla feroz por echar a los ingleses.",
				src: "/images/user/liniers.jpg"
			},{
				titulo: "Derrota de los Ingleses",
				contenido: "Los ingleses finalmente asumen su derrota. La bandera inglesa flameó durante 46 días en la Ciudad de Buenos Aires.",
				src: "/images/user/invasion-inglesa02.jpg"
			}]
		},{
			id: 44,
			titulo: "Deposición de Sobremonte. Interinato de Liniers.",
			icono: "cabildo-sm.png",
			coordenadas: [-58.37, -34.60, 12],
			fecha: new Date(1806, 7,30),
			imagenes: [{
				titulo: "Santiago Liniers: Virrey interino",
				contenido: "El Cabildo designa Virrey a Liniers, en lo que fue la primera decisión sin el aval de la corona española.",
				src: "/images/user/liniers.jpg"
			}]
		},{
			id: 12,
			titulo: "Noticia de la deposición del Rey de España.",
			icono: "fragata-sm.png",
			fecha: "13-05-1810",
			coordenadas: [-57.6, -34.7,10],
			imagenes:[{
				titulo: "Llega la noticia de la deposición del Rey de España",
				contenido: "Llega una fragata con la novedades de Europa. Las autoridades del virreynato intentan que el periódico no se distribuya, pero la noticia se termina difundiendo: Napoleón depone al Rey de España.",
				src: "/images/user/napoleón.jpg"
			},{
				titulo: "Manuel Belgrano, ante las novedades",
				contenido: "Uno de los primeros en enterarse es Manuel Belgrano, quien ya estaba compenetrado con las ideas de igualdad, libertad y fraternindad."
					+"A partir de ese momento, se reúne clandestinamente con otros porteños para pensar un futuro de mayor libertad para la colonia. ",
				src:"/images/user/belgrano.jpg"
			}]			
		},{
			id: 15,
			titulo: "Revolución de Mayo",
			icono: "cabildo-sm.png",
			fecha: "25-05-1810",
			coordenadas: [-58.37, -34.61, 15],
			imagenes:[{
				titulo: "Virrey Cisneros",
				contenido: "Cuando los criollos se enteran de la caída de la corona Española, le exigen al Virrey Cisneros la convocatoria a un Cabildo Abierto.",
				src: "/images/user/virrey-cisneros.jpg"
			},{
				titulo: "Debates en el Cabildo",
				contenido: "Se producen acalorados debates dentro del cabildo: Los realistas quieren mantener a toda costa el orden colonial. Los patriotas creen que es insostenible. ",
				src: "/images/user/cabildo03.jpg"
			},{
				titulo: "El pueblo quiere saber",
				contenido: "En la plaza el pueblo presiona y exige liberarse del pueblo español.",
				src: "/images/user/cabildo01.jpg"
			},{
				titulo: "Triunfo de los patriotas",
				contenido: "Luego de horas de deliberaciones, los criollos logran imponerse. ",
				src: "/images/user/cabildo02.jpg"
			},{
				titulo: "Primera Junta",
				contenido: "Asume una nueva junta de gobierno.",
				src: "/images/user/primera-junta.jpg"
			}]
		},{
			id: 22,
			titulo: "Éxodo Jujeño",
			icono: "sables-sm.png",
			fecha: "23-08-1812",
			coordenadas: [-66.02, -23.02, 10],
			imagenes:[{
				titulo: "Éxodo Jujeño",
				contenido: "El Éxodo Jujeño fue la retirada hacia Tucumán que, cumpliendo parcialmente la orden de evacuación hasta Córdoba impartida por el Primer Triunvirato de las Provincias Unidas del Río de la Plata, emprendió —el 23 de agosto de 1812— el Ejército del Norte, comandado por el general Manuel Belgrano.",
				src: "/images/user/éxodo-jujeño.jpg"
			}]
		}
		]
	};
	return linea;
	
});


dbServices.factory('otrasLineas', function(){
	var linea = {
			titulo: "Artes del 1800",
			acontecimientos: [{
				id: 100,
				titulo: "Goethe termina Fausto",
				icono: "carruaje-sm.png",
				fecha: new Date(1806, 5,8),
				coordenadas: [ 11.051,50.861, 10],
				imagenes:[{
					titulo: "Goethe termina Fausto",
					contenido: "La mejor obra dramática de Goethe es sin duda el Fausto, que ha pasado a ser una obra clásica de la Literatura Universal. La primera versión, el Urfaust o Fausto original, estaba acabada en 1773. Pero el autor la siguió retocando hasta 1790; ya en abril de 1806 estaba completo, pero las guerras napoleónicas demoraron dos años la publicación hasta 1808",
					src: "/images/user/goethe.jpg"						
				}]
				
			}]
	};
	
	var lineas = [linea];
	return lineas;
});

dbServices.factory('acontecimientosCercanos', function(otrasLineas){
	return function(fecha){
		var acontecimientos = new Array();
		
		otrasLineas.forEach(function(linea){
			console.log(linea);
			linea.acontecimientos.forEach(function(acontecimiento){
				console.log(acontecimiento);
				console.log(Math.abs(acontecimiento.fecha - fecha ) / 1000 / 60 / 60 / 24 );
				if( (Math.abs(acontecimiento.fecha - fecha ) / 1000 / 60 / 60 / 24 )< 30){
					console.log("adentro!");
					acontecimientos.push(acontecimiento);
				}
			});
		});
		
		return acontecimientos;
	};
	
});

