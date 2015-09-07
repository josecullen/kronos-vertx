package utils;

import java.util.List;

import model.Acontecimiento;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class Util {
	public static JsonArray acontecimientosToJsonArray(List<Acontecimiento> acontecimientos){
		System.out.println("acontecimientos: "+acontecimientos.size());
		JsonArray aconts = new JsonArray();
		
		for(int i = 0; i < acontecimientos.size(); i++){
			Acontecimiento acontecimiento = acontecimientos.get(i);
			System.out.println(acontecimiento.getAño());

			JsonObject acont = new JsonObject();
			JsonArray coords = new JsonArray();
			coords.add(acontecimiento.getCoordX()).add(acontecimiento.getCoordY());
			
			acont
				.put("titulo", acontecimiento.getTitulo())
				.put("categoria", acontecimiento.getCategoria())
				.put("coordenadas", coords)
				.put("contenido", acontecimiento.getContenido())
				.put("id", acontecimiento.getId())
				.put("dia", acontecimiento.getDia())
				.put("mes", acontecimiento.getMes())
				.put("año", acontecimiento.getAño());
			aconts.add(acont);
		}
		
//		acontecimientos.forEach(acontecimiento ->{
//			System.out.println(acontecimiento.getAño());
//
//			JsonObject acont = new JsonObject();
//			JsonArray coords = new JsonArray();
//			coords.add(acontecimiento.getCoordX()).add(acontecimiento.getCoordY());
//			
//			acont
//				.put("titulo", acontecimiento.getTitulo())
//				.put("categoria", acontecimiento.getCategoria())
//				.put("coordenadas", coords)
//				.put("contenido", acontecimiento.getContenido())
//				.put("id", acontecimiento.getId());
//			aconts.add(acont);
//		});		
		
		return aconts;
	}
	

}
