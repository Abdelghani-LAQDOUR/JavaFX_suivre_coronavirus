package covide19;

import ClassBean.CovidDatas;
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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HomePageController implements Initializable {


    @FXML
    private Label c;

    @FXML
    private Label vcc;

    @FXML
    private Label deaths;
   
    public static Number nbrTotalCase;
    public static Number nbrTotalDeaths;
    public static Number nbrTotalVcc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nbrTotalCase = CovidDatas.getStaticNumber("total_Cases");
        c.setText(nbrTotalCase.toString());
        
        nbrTotalDeaths = CovidDatas.getStaticNumber("total_Deaths");
        deaths.setText(nbrTotalDeaths.toString());
        
        nbrTotalVcc = CovidDatas.getStaticNumber("total_Vaccinations");
        vcc.setText(nbrTotalVcc.toString());
    }
   
}
