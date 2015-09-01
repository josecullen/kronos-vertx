import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Acontecimiento;
import model.LineaDeTiempo;
import service.AcontecimientoService;
import service.LineaDeTiempoService;
import view.Menu;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class EmbeddedServer extends Application {

	static javax.persistence.EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("VertxJPA");
	static EntityManager em = emf.createEntityManager();
	static AcontecimientoService acontService = new AcontecimientoService(em);
	static LineaDeTiempoService lineaService = new LineaDeTiempoService(em);
	static int i = 0;
	static JsonObject update = new JsonObject().put("sended", true);

	public static void main(String[] args) {
		launch(args);

	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Menu(), 1200, 800);
		primaryStage.setScene(scene);
		maximize(primaryStage);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});

		Vertx vertx = Vertx.vertx();
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);

		Route routeAdd = router.route("/db/add")
				.handler(
						routingContext -> {
							HttpServerResponse response = routingContext.response();
							response.putHeader("content-type", "text/plain");
							routingContext.request().absoluteURI();
							System.out.println(routingContext.request().getParam("categoria"));

							em.getTransaction().begin();
							Acontecimiento acontecimiento = acontService.create(routingContext.request().getParam("titulo"));
							acontecimiento.setContenido(routingContext.request().getParam("contenido"));

							double x = Double.parseDouble(routingContext.request().getParam("coordenadaX"));
							double y = Double.parseDouble(routingContext.request().getParam("coordenadaY"));
							acontecimiento.setCoordX(x);
							acontecimiento.setCoordY(y);

							int dia = Integer.parseInt(routingContext.request().getParam("dia"));
							int mes = Integer.parseInt(routingContext.request().getParam("mes"));
							int año = Integer.parseInt(routingContext.request().getParam("ano"));

							acontecimiento.setAño(año);
							acontecimiento.setMes(mes);
							acontecimiento.setDia(dia);

							acontecimiento.setCategoria(routingContext.request().getParam("categoria"));

							em.getTransaction().commit();

							routingContext.response().end();
						});

		Route getDataByYear = router.route("/db/getDataByYear").handler(
				routingContext -> {
					int year = Integer.parseInt(routingContext.request()
							.getParam("year"));
					List<Acontecimiento> acontecimientos = acontService
							.getAcontecimientoByYear(year);

					JsonArray aconts = utils.Util
							.acontecimientosToJsonArray(acontecimientos);

					HttpServerResponse response = routingContext.response();
					response.putHeader("content-type", "application/json");

					routingContext.response().end(aconts.encode());

				});
		
		Route findAllAcontecimientos = router.route("/db/findAll").handler(
			routingContext ->{
				JsonArray aconts = utils.Util.acontecimientosToJsonArray(acontService.findAll());

				HttpServerResponse response = routingContext.response();
				response.putHeader("content-type", "application/json");
				routingContext.response().end(aconts.encode());


			}
		);
		
		Route saveLineaDeTiempo = router.route("/db/saveLineaDeTiempo").handler(
				routingContext ->{
					
					String titulo = routingContext.request().getParam("titulo");
					JsonArray aconts = new JsonArray(routingContext.request().getParam("acontecimientos"));
					System.out.println("titulo:"+ titulo);
					System.out.println("aconts: "+ aconts.encode());
					vertx.executeBlocking(future -> {
							em.getTransaction().begin();
							LineaDeTiempo linea = lineaService.create(titulo);
							ArrayList<Acontecimiento> acontecimientos = new ArrayList<Acontecimiento>();
							aconts.forEach(acont ->{
								System.out.println("acont "+acont.toString());
								Acontecimiento acontecimiento = acontService.getAcontecimientoById(Integer.parseInt(acont.toString()));
								System.out.println("acontecimiento "+acontecimiento);
								//acontecimientos.add(acontecimiento);
								acontecimiento.getLineaDeTiempos().add(linea);
//								linea.getAcontecimientos().add(acontecimiento);
							});
//							linea.setAcontecimientos(acontecimientos);
							em.getTransaction().commit();
						}, res -> {
							HttpServerResponse response = routingContext.response();
							response.putHeader("content-type", "text/plain");
							routingContext.response().end();
						});
				}
			);
		Route routeUpdate = router.route("/getUpdate").handler(
				routingContext -> {
					System.out.println("routeUpdate " + update.encode());
					HttpServerResponse response = routingContext.response();

					response.putHeader("content-type", "application/json");

					routingContext.response().end(update.encode());
					update.put("sended", true);

				});

		Route yearFilter = router.route("/yearFilter").handler(
				routingContext -> {
					System.out.println("routeYearFilter");

					HttpServerResponse response = routingContext.response();
					response.putHeader("content-type", "text/plain");
					int year = Integer.parseInt(routingContext.request()
							.getParam("year"));
					JsonArray aconts = utils.Util
							.acontecimientosToJsonArray(acontService
									.getAcontecimientoByYear(year));

					update.put("acont", aconts);
					update.put("update", "filter");
					update.put("sended", false);

					routingContext.response().end("");
				});

		Route flyTo = router.route("/flyTo").handler(
				routingContext -> {
					System.out.println("routeFlyTo");

					HttpServerResponse response = routingContext.response();
					response.putHeader("content-type", "text/plain");
					double x = Double.parseDouble(routingContext.request().getParam("x"));
					double y = Double.parseDouble(routingContext.request().getParam("y"));
					
					update.put("x", x);
					update.put("y", y);
					update.put("update", "fly");
					update.put("sended", false);

					routingContext.response().end("");
					
				});

		router.route("/*").handler(StaticHandler.create());

		server.requestHandler(router::accept).listen(8080);

	}

	private void maximize(Stage stage) {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setX(bounds.getMinX());
		stage.setY(bounds.getMinY());
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
	}

}
