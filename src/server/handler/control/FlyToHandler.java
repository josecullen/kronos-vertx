package server.handler.control;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class FlyToHandler implements Handler<RoutingContext> {
	JsonObject update = new JsonObject().put("sended", true);

	@Override
	public void handle(RoutingContext routingContext) {
		System.out.println("routeFlyTo");

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "text/plain");
		double x = Double.parseDouble(routingContext.request().getParam("x"));
		double y = Double.parseDouble(routingContext.request().getParam("y"));

		update.put("x", x);
		update.put("y", y);
		update.put("update", "fly");
		update.put("sended", false);
		routingContext.vertx().eventBus().publish("update", update);

		routingContext.response().end("");		
	}

}
