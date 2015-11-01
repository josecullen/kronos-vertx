package server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class CentralVerticle extends AbstractVerticle {
	static JsonObject update = new JsonObject().put("sended", true);

	@SuppressWarnings("unused")
	@Override
	public void start() throws Exception {
		super.start();
		HttpServer server = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		router.route("/*").handler(StaticHandler.create());
		server.requestHandler(router::accept).listen(8000);
	}

}
