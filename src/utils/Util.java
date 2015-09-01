package utils;

import java.util.List;

import model.Acontecimiento;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class Util {
	public static JsonArray acontecimientosToJsonArray(List<Acontecimiento> acontecimientos){
		JsonArray aconts = new JsonArray();
		acontecimientos.forEach(acontecimiento ->{
			JsonObject acont = new JsonObject();
			JsonArray coords = new JsonArray();
			coords.add(acontecimiento.getCoordX()).add(acontecimiento.getCoordY());
			
			acont
				.put("titulo", acontecimiento.getTitulo())
				.put("categoria", acontecimiento.getCategoria())
				.put("coordenadas", coords)
				.put("contenido", acontecimiento.getContenido())
				.put("id", acontecimiento.getId());
			aconts.add(acont);
		});
		return aconts;
	}
	

}
