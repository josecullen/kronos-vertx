package server.handler.db;

import model.LineaDeTiempo;
import service.LineaDeTiempoService;
import utils.Globals;
import utils.Util;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class FindLineaDeTiempoHandler implements Handler<RoutingContext> {
	LineaDeTiempoService ldtService = new LineaDeTiempoService(Globals.em);
	@Override
	public void handle(RoutingContext routingContext) {
		String titulo = routingContext.request().getParam("titulo");
		LineaDeTiempo linea = ldtService.find(titulo);
		System.out.println("Acontecimientos "+linea.getAcontecimientos().size());
		JsonArray acontecimientos = Util.acontecimientosToJsonArray(linea.getAcontecimientos());
		
		JsonObject lineaJson = new JsonObject();
		lineaJson.put("titulo", linea.getTitulo());
		lineaJson.put("acontecimientos", acontecimientos);
		routingContext.response().end(lineaJson.encode());
	}

}
