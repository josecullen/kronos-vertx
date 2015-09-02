package server.handler.db;

import service.AcontecimientoService;
import utils.Globals;
import model.Acontecimiento;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class AddAcontecimientoHandler implements Handler<RoutingContext> {
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "text/plain");
		routingContext.request().absoluteURI();
		System.out.println(routingContext.request().getParam("categoria"));

		Globals.em.getTransaction().begin();
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

		Globals.em.getTransaction().commit();

		routingContext.response().end();		
	}
	

}
