package server.handler.db;

import service.AcontecimientoService;
import utils.Globals;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

public class FindAllAcontecimientosHandler implements Handler<RoutingContext> {
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		JsonArray aconts = utils.Util.acontecimientosToJsonArray(acontService.findAll());
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json");
		routingContext.response().end(aconts.encode());		
	}
	

}
