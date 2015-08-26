package view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
	
	HBox north;
	
	public Menu() {
		north = new HBox(btnForm, btnMap);
		
		setTop(north);
		setLeft(webLeft);
		setCenter(webCenter);
		
		btnForm.setOnAction(value ->{
			engineCenter.load(URL+"/formulario.html");
			engineLeft.load("");
		});
		
		btnMap.setOnAction(value ->{
			engineCenter.load(URL+"/map.html");
			engineLeft.load(URL+"/control.html");
		});
		
	}
}
