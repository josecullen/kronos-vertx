package server;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import server.handler.control.DataByYearHandler;
import server.handler.control.FlyToHandler;
import server.handler.control.GetUpdateHandler;
import server.handler.control.YearFilterHandler;
import server.handler.db.AddAcontecimientoHandler;
import server.handler.db.FindAllAcontecimientosHandler;
import server.handler.db.FindLineaDeTiempoHandler;
import server.handler.db.SaveLineaDeTiempoHandler;
import service.AcontecimientoService;
import service.LineaDeTiempoService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class CentralVerticle extends AbstractVerticle {
	static javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("VertxJPA");
	static EntityManager em = emf.createEntityManager();
	static JsonObject update = new JsonObject().put("sended", true);

	AcontecimientoService acontService = new AcontecimientoService(em);
	LineaDeTiempoService lineaService = new LineaDeTiempoService(em);

	@SuppressWarnings("unused")
	@Override
	public void start() throws Exception {
		super.start();
		HttpServer server = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		Route routeAdd = router.route("/db/add").handler(new AddAcontecimientoHandler());
		Route getDataByYear = router.route("/db/getDataByYear").handler(new DataByYearHandler());
		Route saveLineaDeTiempo = router.route("/db/saveLineaDeTiempo").handler(new SaveLineaDeTiempoHandler());
		Route findAllAcontecimientos = router.route("/db/findAll").handler(new FindAllAcontecimientosHandler());
		Route routeUpdate = router.route("/getUpdate").handler(new GetUpdateHandler());
		Route yearFilter = router.route("/yearFilter").handler(new YearFilterHandler());
		Route flyTo = router.route("/flyTo").handler(new FlyToHandler());
		Route findLineaDeTiempo = router.route("/db/findLinea").handler(new FindLineaDeTiempoHandler());
		router.route("/*").handler(StaticHandler.create());
		server.requestHandler(router::accept).listen(8080);
	}

}
