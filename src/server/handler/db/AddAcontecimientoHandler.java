package server.handler.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.io.FileOutputStream;

import service.AcontecimientoService;
import utils.Globals;

public class AddAcontecimientoHandler implements Handler<RoutingContext> {
	AcontecimientoService acontService = new AcontecimientoService(Globals.em);

	@Override
	public void handle(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "text/plain");
		
		
		routingContext.request().bodyHandler(	buffer->{			
				JsonObject json;

				json = new JsonObject(buffer.toString());
				System.out.println(json);
				System.out.println(buffer.toString());		
				
				String dateString = json.getString("date");		
				
				String diaString = dateString.substring(8, 10);
				String mesString = dateString.substring(5, 7);
				String añoString = dateString.substring(0, 4);
				System.out.println(diaString+"-"+mesString+"-"+añoString);				
				
				JsonObject coordenadas = json.getJsonObject("coordenadas");
				System.out.println(coordenadas);
				System.out.println(coordenadas.getDouble("x"));
				System.out.println(coordenadas.getDouble("y"));


								
								
				String titulo = json.getString("titulo");
				String contenido = json.getString("contenido");				
				int dia = Integer.parseInt(diaString);
				int mes = Integer.parseInt(mesString);
				int año = Integer.parseInt(añoString);
				double x = coordenadas.getDouble("x");
				double y = coordenadas.getDouble("y");
		});
		
//		Globals.em.getTransaction().begin();
//		Acontecimiento acontecimiento = acontService.create(routingContext.request().getParam("titulo"));
//		acontecimiento.setContenido(routingContext.request().getParam("contenido"));
//
//		double x = Double.parseDouble(routingContext.request().getParam("coordenadaX"));
//		double y = Double.parseDouble(routingContext.request().getParam("coordenadaY"));
//		
//		
		
//		acontecimiento.setCoordX(x);
//		acontecimiento.setCoordY(y);
//		acontecimiento.setAño(año);
//		acontecimiento.setMes(mes);
//		acontecimiento.setDia(dia);
		
		
		
//		Globals.em.getTransaction().begin();
//		Acontecimiento acontecimiento = acontService.create(routingContext.request().getParam("titulo"));
//		acontecimiento.setContenido(routingContext.request().getParam("contenido"));
//
//		double x = Double.parseDouble(routingContext.request().getParam("coordenadaX"));
//		double y = Double.parseDouble(routingContext.request().getParam("coordenadaY"));
//		int dia = Integer.parseInt(routingContext.request().getParam("dia"));
//		int mes = Integer.parseInt(routingContext.request().getParam("mes"));
//		int año = Integer.parseInt(routingContext.request().getParam("ano"));
//
//		acontecimiento.setCoordX(x);
//		acontecimiento.setCoordY(y);
//		acontecimiento.setAño(año);
//		acontecimiento.setMes(mes);
//		acontecimiento.setDia(dia);
//
//		acontecimiento.setCategoria(routingContext.request().getParam("categoria"));
//
//		Globals.em.getTransaction().commit();

		routingContext.response().end();		
	}
	
}

class SaveImage extends AbstractVerticle{
	

	
}

