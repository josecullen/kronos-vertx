package server.handler.control;

import java.util.List;
import service.AcontecimientoService;
import utils.Globals;
import model.Acontecimiento;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class DataByYearHandler implements Handler<RoutingContext> {	
	static JsonObject update = new JsonObject().put("sended", true);
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		int year = Integer.parseInt(routingContext.request().getParam("year"));
		List<Acontecimiento> acontecimientos = acontService.getAcontecimientoByYear(year);

		JsonArray aconts = utils.Util.acontecimientosToJsonArray(acontecimientos);

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json");

		routingContext.response().end(aconts.encode());		
	}

}
