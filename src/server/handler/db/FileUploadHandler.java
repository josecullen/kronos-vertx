package server.handler.db;

import java.util.ArrayList;
import java.util.List;

import model.Acontecimiento;
import model.AcontecimientoImagen;
import model.Imagen;
import service.AcontecimientoImagenService;
import service.AcontecimientoService;
import service.ImagenService;
import utils.Globals;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.http.HttpServerFileUpload;

public class FileUploadHandler implements Handler<RoutingContext> {
	String titulo;
	ImagenService imagenService = new ImagenService(Globals.em);
	AcontecimientoService acontecimientoService = new AcontecimientoService(Globals.em);
	AcontecimientoImagenService acontImgService = new AcontecimientoImagenService(Globals.em);
	
	List<Imagen> imagenes = new ArrayList<Imagen>();
	@Override
	public void handle(RoutingContext routingContext) {
		HttpServerRequest req = routingContext.request();
		req.setExpectMultipart(true);
		req.endHandler(a->{
			MultiMap formAttributes = req.formAttributes();
      		System.out.println("endHandler formAttributes: "+ formAttributes);
            
      		Globals.em.getTransaction().begin();
      		Acontecimiento acontecimiento = acontecimientoService.create(formAttributes.get("titulo"));
      		acontecimiento.setContenido(formAttributes.get("titulo"));
      		acontecimiento.setCoordX(Double.parseDouble(formAttributes.get("coordenadaX")));
      		acontecimiento.setCoordY(Double.parseDouble(formAttributes.get("coordenadaY")));
//            Globals.em.getTransaction().commit();
//      		Globals.em.getTransaction().begin();

            for(int i = 0; i < imagenes.size(); i++){
            	imagenes.get(i).setTitulo(formAttributes.get("imgTitulo"+i));
            	imagenes.get(i).setCopete(formAttributes.get("imgContenido"+i));
            	AcontecimientoImagen acontecimientoImagen = acontImgService.create(imagenes.get(i), acontecimiento, i);
            	imagenes.get(i).getAcontecimientoImagenes().add(acontecimientoImagen);
            	Globals.em.merge(imagenes.get(i));
            }
            
            
            Globals.em.getTransaction().commit();
		});
		
        req.uploadHandler(new Handler<HttpServerFileUpload>() {
          @Override
          public void handle(final HttpServerFileUpload upload) {
        	  System.out.println("uploadHandler");
              long max = imagenService.getMax();

              upload.exceptionHandler(new Handler<Throwable>() {
	              @Override
	              public void handle(Throwable event) {
	                req.response().end("Upload failed");
	              }
              });
              upload.endHandler(new Handler<Void>() {
	              @Override
	              public void handle(Void event) {        
	            		System.out.println("endHandler 2");	
	            		if(req.isEnded()){
	            			System.out.println("isEnded");
	            			req.response().end("Upload successful, you should see the file in the server directory");
	            		}
	            		
	              }
              });
              Globals.em.getTransaction().begin();
              Imagen imagen = imagenService.create("last");
              imagenes.add(imagen);
              Globals.em.getTransaction().commit();       
              upload.streamToFileSystem(""+imagen.getId());

          }
        });
		
	}

}
