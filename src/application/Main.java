package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			// Ajout du background
	        final URL imageURL = getClass().getResource("background2.jpg"); 
	        final Image image = new Image(imageURL.toExternalForm()); 
	        final ImageView imageView = new ImageView(image); 
	        imageView.setFitWidth(1200); 
	        imageView.setFitHeight(1200); 
	        root.getChildren().setAll(imageView);
	        
	        //Ajout d'un label

	        
	        Text txt=new Text("Insurance : Moteur de recherche sémantique");
            txt.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            txt.setFill(Color.WHITE);
            txt.setTranslateX(100);
            txt.setTranslateY(100);
            root.getChildren().add(txt);
	        
	
	       
	        
            Text txt2=new Text("Vous recherchez : ");
            txt2.setFill(Color.WHITE);
            txt2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            txt2.setTranslateX(300);
            txt2.setTranslateY(200);
            root.getChildren().add(txt2);
            
            TextField t2=new TextField();
            t2.setPromptText("Exemple : Policy");
            t2.setPrefColumnCount(50);
            t2.setTranslateX(300);
            t2.setTranslateY(220);
            root.getChildren().add(t2);
           
            
 
            Button b2=new Button();
            b2.setText("Recherche");  
            b2.setTranslateX(550);
            b2.setTranslateY(270);
            b2.setVisible(true);
            root.getChildren().add(b2);
            
            
    
        
            
            
            b2.setOnAction((ActionEvent event) -> {
            	  
                // Ajout d'un tableau 
                TableView tableView = new TableView();

                TableColumn<String, URI> column1 = new TableColumn<>("Résultat");
                column1.setCellValueFactory(new PropertyValueFactory<>("label"));


                TableColumn<String, URI> column2 = new TableColumn<>("URL associé");
                column2.setCellValueFactory(new PropertyValueFactory<>("url"));


                tableView.getColumns().add(column1);
                tableView.getColumns().add(column2);

                tableView.getItems().add(new URI("Contract TPPC", "http://www.co-ode.org/ontologies/Insurance/insurance.owl#TPPC"));
                tableView.getItems().add(new URI("Contract Facultés", "http://www.co-ode.org/ontologies/Insurance/insurance.owl#FAC"));
                tableView.getItems().add(new URI("Contract Responsabilité Civile", "http://www.co-ode.org/ontologies/Insurance/insurance.owl#RC"));
                
                tableView.setPrefWidth(900);
                tableView.setPrefHeight(200);
                tableView.setTranslateX(200);
                tableView.setTranslateY(350);
                tableView.setPlaceholder(new Label("Aucun résultat n'est disponible"));

                VBox vbox = new VBox(tableView); 
                root.getChildren().add(vbox);


            	
            });
            
            Scene scene = new Scene(root);

            
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);			
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
