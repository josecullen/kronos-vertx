package server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import service.AcontecimientoService;
import service.LineaDeTiempoService;
import model.Acontecimiento;
import model.LineaDeTiempo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class DbVerticle extends AbstractVerticle{
	static javax.persistence.EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("VertxJPA");
	static EntityManager em = emf.createEntityManager();

	AcontecimientoService acontService = new AcontecimientoService(em);
	LineaDeTiempoService lineaService = new LineaDeTiempoService(em);

	@Override
	public void start() throws Exception {
		super.start();	
		HttpServer server = vertx.createHttpServer();
		server.requestHandler(router::accept).listen(1234);
		
	}
	
	
	Router router = Router.router(vertx);

	Route routeAdd = router.route("/db/add").handler(
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
						int dia = Integer.parseInt(routingContext.request().getParam("dia"));
						int mes = Integer.parseInt(routingContext.request().getParam("mes"));
						int año = Integer.parseInt(routingContext.request().getParam("ano"));
						
						acontecimiento.setCoordX(x);
						acontecimiento.setCoordY(y);
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
							acontecimiento.getLineaDeTiempos().add(linea);
						});
						em.getTransaction().commit();
					}, res -> {
						HttpServerResponse response = routingContext.response();
						response.putHeader("content-type", "text/plain");
						routingContext.response().end();
					});
			}
		);
	
	
}
