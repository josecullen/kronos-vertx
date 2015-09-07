import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
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

import server.CentralVerticle;
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
	//	primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});

		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new CentralVerticle());
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
