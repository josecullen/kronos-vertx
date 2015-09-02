package server.handler.control;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class GetUpdateHandler implements Handler<RoutingContext> {
	JsonObject update = new JsonObject().put("sended", true);

	@Override
	public void handle(RoutingContext routingContext) {
		routingContext.vertx().eventBus().consumer("update", consumer ->{
			System.out.println(consumer.body());
			update = (JsonObject) consumer.body();
		});	

		System.out.println("routeUpdate " + update.encode());
		HttpServerResponse response = routingContext.response();

		response.putHeader("content-type", "application/json");

		routingContext.response().end(update.encode());
		update.put("sended", true);		
	}

}
