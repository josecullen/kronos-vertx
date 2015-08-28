import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Acontecimiento;
import service.AcontecimientoService;
import view.Menu;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class EmbeddedServer extends Application{
	
	static javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("VertxJPA");
	static EntityManager em = emf.createEntityManager(); 
	static AcontecimientoService acontService = new AcontecimientoService(em);
	static int i = 0;

  public static void main(String[] args) {
	  launch(args);
	  
  }

  	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Menu(),1200,800);
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
		
		Route routeDBTest = router.route("/dbtest").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			i++;
			routingContext.response().end();
		});
		
		
		Route routeAdd = router.route("/db/add").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			routingContext.request().absoluteURI();
			System.out.println(routingContext.request().getParam("titulo"));
			
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
			
			
			em.getTransaction().commit();
			
			
			routingContext.response().end();
		});
		
		Route routeUpdate = router.route("/getUpdate").handler(routingContext -> {
			  HttpServerResponse response = routingContext.response();
			  response.putHeader("content-type", "text/plain");

			  routingContext.response().end("route1\n");
		});

		router.route("/*").handler(StaticHandler.create());

		
		server.requestHandler(router::accept).listen(8080);
		
		
	}
  	
  	private void maximize(Stage stage){
		Screen screen = Screen.getPrimary();

		Rectangle2D bounds = screen.getVisualBounds();

		stage.setX(bounds.getMinX());
		stage.setY(bounds.getMinY());
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
	}
	
  	
  	
}

