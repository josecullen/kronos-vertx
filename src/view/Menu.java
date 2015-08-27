package view;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	SplitPane split;
	HBox north;
	
	public Menu() {
		north = new HBox(btnForm, btnMap);
		
		setTop(north);
		
		split = new SplitPane();
		split.getItems().addAll(webLeft, webCenter);
		split.setDividerPositions(0.2d);
		split.autosize();
		
		/*
		setLeft(webLeft);
		setCenter(webCenter);
		*/
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
