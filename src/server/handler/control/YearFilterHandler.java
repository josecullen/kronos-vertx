package server.handler.control;

import service.AcontecimientoService;
import utils.Globals;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class YearFilterHandler implements Handler<RoutingContext> {
	JsonObject update = new JsonObject().put("sended", true);
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		System.out.println("routeYearFilter");

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "text/plain");
		int year = Integer.parseInt(routingContext.request().getParam("year"));
		JsonArray aconts = utils.Util.acontecimientosToJsonArray(acontService.getAcontecimientoByYear(year));

		update.put("acont", aconts);
		update.put("update", "filter");
		update.put("sended", false);

		routingContext.vertx().eventBus().publish("update", update);
		

		
		routingContext.response().end("");
		
	}

}
