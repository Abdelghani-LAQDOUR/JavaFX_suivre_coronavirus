package covide19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Covide19 extends Application {
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setTitle("Covid Tracker");

        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/viruse.jpg"));
        Covide19.stage = stage;
        Covide19.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
