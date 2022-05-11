package covide19;

import com.sun.javaws.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

    //les button :
    @FXML
    private Button btnHome;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnExit;
    
    @FXML
    private BorderPane borderpane;

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadPage("HomePage");
    }
    

    public void exit(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setHeaderText("you want to close this interface?");
        
        Optional<ButtonType> res = a.showAndWait();
        
        if(res.get() == ButtonType.OK){
             Stage s = (Stage) btnExit.getScene().getWindow();
             s.close();
        }
    }
    

    public void LoadPage(String page){
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }
    
    @FXML
    public void Onclick(MouseEvent actionEvent) {
        if (actionEvent.getSource() == btnHome) {
            /*btnHome.setStyle("-fx-background-color : #017070");
            btnDashboard.setStyle("-fx-background-color : #004a4a");*/
            LoadPage("HomePage");
        }
        if (actionEvent.getSource() == btnDashboard) {
            /*btnDashboard.setStyle("-fx-background-color : #017070");
            btnHome.setStyle("-fx-background-color : #004a4a");*/
            LoadPage("Dashboard");
        }
        if (actionEvent.getSource() == btnExit) {
           exit();
        }
    }
}
