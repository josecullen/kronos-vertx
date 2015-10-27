package utils;

import java.util.List;

import model.Acontecimiento;
import model.AcontecimientoIcono;
import model.AcontecimientoImagen;
import model.Icono;
import model.Imagen;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class Util {
	public static JsonArray acontecimientosToJsonArray(List<Acontecimiento> acontecimientos){
		System.out.println("acontecimientos: "+acontecimientos.size());
		JsonArray aconts = new JsonArray();
		
		for(int i = 0; i < acontecimientos.size(); i++){
			Acontecimiento acontecimiento = acontecimientos.get(i);

			JsonObject acont = new JsonObject();
			JsonArray coords = new JsonArray();
			coords.add(acontecimiento.getCoordX()).add(acontecimiento.getCoordY());
			
			JsonArray images = new JsonArray();
			
			List<AcontecimientoImagen> acontImagenes = acontecimiento.getAcontecimientoImagenes();
			for(int j = 0; j < acontImagenes.size(); j++){
				JsonObject imagen = new JsonObject();
				Imagen img = acontImagenes.get(j).getImagen();
				imagen.put("titulo", img.getTitulo()).put("src", img.getId()).put("copete", img.getCopete());
				images.add(imagen);
				System.out.println(imagen);
			}
			
			JsonArray iconos = new JsonArray();
			
			List<AcontecimientoIcono> acontIconos = acontecimiento.getAcontecimientoIconos();
			for(int k = 0; k < acontIconos.size(); k++){
				JsonObject icono = new JsonObject();
				Icono ic = acontIconos.get(k).getIcono();
				JsonArray coordenadas = new JsonArray();
				coordenadas.add(acontIconos.get(k).getCoordX()).add(acontIconos.get(k).getCoordY());
				icono
					.put("nombre", ic.getNombre())
					.put("src", ic.getId())
					.put("coordenadas", coordenadas);
				iconos.add(icono);
			}
			
			acont
				.put("titulo", acontecimiento.getTitulo())
				.put("categoria", acontecimiento.getCategoria())
				.put("coordenadas", coords)
				.put("zoom", acontecimiento.getZoom())
				.put("contenido", acontecimiento.getContenido())
				.put("id", acontecimiento.getId())
				.put("dia", acontecimiento.getDia())
				.put("mes", acontecimiento.getMes())
				.put("año", acontecimiento.getAño())
				.put("imagenes", images)
				.put("iconos", iconos);
			aconts.add(acont);
		}

		return aconts;
	}
	

}
