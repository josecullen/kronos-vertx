package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Menu extends BorderPane {
	public static String URL = "http://localhost:8080";
	WebView 
		webCenter = new WebView(),
		webLeft = new WebView();
	WebEngine
		engineCenter = webCenter.getEngine(),
		engineLeft = webLeft.getEngine();
	
	Button
		btnForm = new Button("Cargar Datos"),
		btnMap = new Button("Ver Mapa");
	SplitPane split;
	HBox north;
	
	public Menu() {
		north = new HBox(btnForm, btnMap);
		
		setTop(north);
		
		split = new SplitPane();
		split.getItems().addAll(webLeft, webCenter);
		split.setDividerPositions(0.2d);
		split.autosize();
		
		engineCenter.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

	        @Override
	        public WebEngine call(PopupFeatures p) {
	            Stage stage = new Stage(StageStyle.UTILITY);
	            WebView wv2 = new WebView();
	            stage.setScene(new Scene(wv2));
	            stage.show();
	            return wv2.getEngine();
	        }
	    });
		
		
		setCenter(split);
		btnForm.setOnAction(value ->{
			engineCenter.load(URL+"/formulario.html");
			engineLeft.load("");
		});
		
		btnMap.setOnAction(value ->{
			engineCenter.load(URL+"/map.html");
			engineLeft.load(URL+"/control.html");
		});
		
		engineCenter.load(URL+"/map.html");
		engineLeft.load(URL+"/control.html");
		
	}
}
