package server.handler.db;

import java.util.ArrayList;

import service.AcontecimientoService;
import service.LineaDeTiempoService;
import utils.Globals;
import model.Acontecimiento;
import model.LineaDeTiempo;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

public class SaveLineaDeTiempoHandler implements Handler<RoutingContext> {
	LineaDeTiempoService lineaService = new LineaDeTiempoService(Globals.em);
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		String titulo = routingContext.request().getParam("titulo");
		JsonArray aconts = new JsonArray(routingContext.request().getParam("acontecimientos"));
		System.out.println("titulo:" + titulo);
		System.out.println("aconts: " + aconts.encode());
		routingContext.vertx().executeBlocking(
				future -> {
					Globals.em.getTransaction().begin();
					LineaDeTiempo linea = lineaService.create(titulo);
					ArrayList<Acontecimiento> acontecimientos = new ArrayList<Acontecimiento>();
					aconts.forEach(acont -> {
						System.out.println("acont "	+ acont.toString());
						Acontecimiento acontecimiento = acontService.getAcontecimientoById(Integer.parseInt(acont.toString()));
						System.out.println("acontecimiento "+ acontecimiento);
						acontecimiento.getLineaDeTiempos().add(linea);
					});
					Globals.em.getTransaction().commit();
				},
				res -> {
					HttpServerResponse response = routingContext.response();
					response.putHeader("content-type", "text/plain");
					routingContext.response().end();
				});		
	}

}
