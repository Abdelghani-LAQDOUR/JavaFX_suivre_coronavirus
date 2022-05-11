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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.hibernate.PropertyValueException;

public class DashboardController implements Initializable {

    //les button :
    @FXML
    private Button btnsearch;
    
     @FXML
    private Button Affiche_TC;

    @FXML
    private Button Affiche_TD;

    @FXML
    private Button Affiche_TT;

    @FXML
    private Button Affiche_NC;

    @FXML
    private Button Affiche_ND;

    @FXML
    private Button Affiche_NT;

    @FXML
    private ComboBox countryBox;

    @FXML
    private ComboBox continentBox;
    
    @FXML
    private ComboBox yearBox;

    //pane :
    @FXML
    private TabPane tabPane;

    //tableColumn :
    @FXML
    private TableView<?> tableData;
     
    @FXML
    private TableColumn<CovidDatas, Integer> c_id;

    @FXML
    private TableColumn<CovidDatas, Integer> c_continent;

    @FXML
    private TableColumn<CovidDatas, Integer> c_country;

    @FXML
    private TableColumn<CovidDatas, Integer> c_date;

    @FXML
    private TableColumn<CovidDatas, Integer> c_totalc;

    @FXML
    private TableColumn<CovidDatas, Integer> c_totald;

    @FXML
    private TableColumn<CovidDatas, Integer> c_totalt;

    @FXML
    private TableColumn<CovidDatas, Integer> c_strIndex;

    @FXML
    private TableColumn<CovidDatas, Integer> c_population;

    @FXML
    private TableColumn<CovidDatas, Integer> c_median;
    
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private NumberAxis yAxis ;
    
    @FXML
    private NumberAxis yAxe;

    @FXML
    private Button bntsearch_D;

    @FXML
    private ComboBox country_D;

    @FXML
    private ComboBox continent_D;
    
    @FXML
    private PieChart piechart;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ComboBox continent_v;

    @FXML
    private ComboBox country_v;

    @FXML
    private ComboBox year_v;

    @FXML
    private ComboBox month_v;

    @FXML
    private Button bntsearch_v;
    
    @FXML
    private Label caption;

    
    @FXML
    private BarChart<String, Number> barchart;
    
    @FXML
    private Button bntsearch_o;

    @FXML
    private ComboBox continent_o;

    @FXML
    private ComboBox country_o;

    @FXML
    private BarChart<String, Number> otherChart;
    
    private String country ;
    private String year;
    private String continent; 
    
    
    //------------function to get Max value for Chart YAXis --------------------
    public Number getMax(ArrayList<Number> l){
        Number max = Integer.MIN_VALUE;
        for(int i=0; i<l.size(); i++){
           Number h = l.get(i);
            if( max.longValue() < h.longValue()){
                max =  l.get(i);
            }
        }
        return max;
    }
    
    //------------------- Function afficher Dialog box ------------------------:
    public void dialogueBox(Alert.AlertType type, String title, String message){
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(message);
        
        Optional<ButtonType> res = a.showAndWait();
    }

    //----------------------Function afficher total cases ------------------------:
    @FXML
    public void afficherTC(ActionEvent e){
        lineChart.getData().clear();
        XYChart.Series<String, Number> totalC = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "total_Cases", country, continent);
                String x = Integer.toString(i);
                
                listdonne.add(y);
                totalC.getData().add(new XYChart.Data<String, Number>(x, y));
                //System.out.println(y);
            }

            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);
            //yAxis.setTickUnit(1000);
            
            totalC.setName("Total Cases");
            lineChart.getData().add(totalC);           
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");
        }
    }

    //----------------------Function afficher New cases ------------------------:
    @FXML
    public void afficherNC(ActionEvent e){
        lineChart.getData().clear();
        XYChart.Series<String, Number> NewC = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "new_Cases", country, continent);
                 
                 listdonne.add(y);
                String x = Integer.toString(i);
                NewC.getData().add(new XYChart.Data<String, Number>(x, y));
            }
            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(max.longValue());
            //yAxis.setTickUnit(1000);
            
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);

            NewC.setName("New Cases");
            lineChart.getData().add(NewC);
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");

        }
    }
    
    //------------------- Function afficher total Deaths -----------------------:
    @FXML
    public void afficherTD(ActionEvent e){       
        lineChart.getData().clear();
        XYChart.Series<String, Number> totalD = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "total_Deaths", country, continent);
                
                listdonne.add(y);
                String x = Integer.toString(i);
                totalD.getData().add(new XYChart.Data<String, Number>(x, y));
                
            }
            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(max.longValue());
            //yAxis.setTickUnit(1000);
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);

            totalD.setName("Total Deaths");
            lineChart.getData().add(totalD);
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");

        }
    }
    
    //--------------------Function afficher New Deaths ---------------------:
    @FXML
    public void afficherND(ActionEvent e){
        lineChart.getData().clear();
        XYChart.Series<String, Number> NewD = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "new_Deaths", country, continent);
                
                listdonne.add(y);
                String x = Integer.toString(i);
                NewD.getData().add(new XYChart.Data<String, Number>(x, y));
            }

            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(max.longValue());
            //yAxis.setTickUnit(1000);
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);


            NewD.setName("New Deaths");
            lineChart.getData().add(NewD);
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");

        }
    }
    
    //---------------------Function afficher total test -----------------------:
    @FXML
    public void afficherTT(ActionEvent e){
        lineChart.getData().clear();
        XYChart.Series<String, Number> totalT = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "total_Tests", country, continent);
                
                 listdonne.add(y);
                String x = Integer.toString(i);
                totalT.getData().add(new XYChart.Data<String, Number>(x, y));
            }

            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(max.longValue());
            //yAxis.setTickUnit(1000);
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);


            totalT.setName("Total Test");
            lineChart.getData().add(totalT);
        }
        else{
             dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");           
        }
    }
    
    
    //----------------------Function afficher New test ------------------------:
    @FXML
    public void afficherNT(ActionEvent e){
        lineChart.getData().clear();
        XYChart.Series<String, Number> NewT = new XYChart.Series<String, Number>();
        this.year = yearBox.getSelectionModel().getSelectedItem().toString();
        this.continent = continentBox.getSelectionModel().getSelectedItem().toString();
        ArrayList<Number> listdonne = new ArrayList<Number>();
        
        if(!countryBox.getSelectionModel().isEmpty()){
            this.country = countryBox.getSelectionModel().getSelectedItem().toString();
            for(int i=1; i<=12;i++){
                Number y = CovidDatas.getdataLineCharts("'"+i+"/%/"+year+"'", "new_Tests", country, continent);
                
                listdonne.add(y);
                String x = Integer.toString(i);
                NewT.getData().add(new XYChart.Data<String, Number>(x, y));
            }

            //trouver le max dans la list :
            Number max ;
            max = this.getMax(listdonne);
            //System.out.println(listdonne+"******************************"+max);

            //personaliser le graphe :
            yAxis.setAutoRanging(false);
            //yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(max.longValue());
            //yAxis.setTickUnit(1000);
            Number plus = max.longValue()/5;
            yAxis.setUpperBound(max.longValue()+ plus.longValue());
            lineChart.setTitle(" The evolution of the pandemic in "+country);


            NewT.setName("New Test");
            lineChart.getData().add(NewT);
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");            
        }
    }
    
    //----------Function for select country by continent (ComboBox) ------------
    @FXML
    public void selectCountryByContinent(ActionEvent e){
        barchart.getData().clear();
        lineChart.getData().clear();
        otherChart.getData().clear();
        
        if(e.getSource() == continent_D){
            country_D.getSelectionModel().clearSelection();
            ObservableList<String> c = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_D.getSelectionModel().getSelectedItem().toString()));
            country_D.setItems(c);
        }
        if(e.getSource() == continentBox){
            countryBox.getSelectionModel().clearSelection();
            ObservableList<String> contry = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continentBox.getSelectionModel().getSelectedItem().toString()));
            countryBox.setItems(contry);
        }
        if(e.getSource() == continent_v){
            country_v.getSelectionModel().clearSelection();
            ObservableList<String> contry = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_v.getSelectionModel().getSelectedItem().toString()));
            country_v.setItems(contry);
        }
        if(e.getSource() == continent_o){
            country_o.getSelectionModel().clearSelection();
            ObservableList<String> contry = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_o.getSelectionModel().getSelectedItem().toString()));
            country_o.setItems(contry);
        }
    }
    
    //-----Function for remove the zero in date(04/02/2020 => 4/2/2020) --------
    public String removeZero(String str)
    {
        // Count leading zeros
        char [] s = str.toCharArray();
        StringBuffer sb = new StringBuffer(str);
       
        for(int j = 0; j<s.length;j++){
            if(s[j] == '/'){
                if(s[j+1] == '0'){
                    sb.replace(j+1, j+2, "");
                    return sb.toString();
                }
            }
        }
        return sb.toString();  // return in String
    }
    
    //-------------------- Function for Daily statistics -----------------------
    @FXML
    public void ClickSearchDaily(){
        barchart.getData().clear();
       
        String dateString = (datePicker.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)).replaceFirst("^0", "");
        //System.out.println("date :hhh => "+removeZero(dateString));
        dateString = removeZero(dateString);
        String continent_d = continent_D.getSelectionModel().getSelectedItem().toString();
             
        List<String> listAffich = new ArrayList<String>();
        listAffich.add("   New Cases   ");
        listAffich.add("   New Deaths   ");
        listAffich.add("   New Test   ");
        
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("new_Cases");
        listColumn.add("new_Deaths");
        listColumn.add("new_Tests");
        
        if(!country_D.getSelectionModel().isEmpty()){ 
            String country_d = country_D.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i<listAffich.size();i++){
                XYChart.Series<String, Number> serie = new XYChart.Series<>();
                
                
                Number y = CovidDatas.getdataBarCharts(dateString, listColumn.get(i).toString(), country_d, continent_d);
               
                serie.getData().add(new XYChart.Data<>(listAffich.get(i).toString(), y));
                serie.setName(listAffich.get(i));
                barchart.getData().add(serie);
            }

            barchart.setTitle(" The evolution of the pandemic in "+country_d);           
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");            
        }
    }

    //------------------- Function for others statistics -----------------------
    @FXML
    public void ClickSearchOthers(){
        otherChart.getData().clear();
        String cont= continent_o.getSelectionModel().getSelectedItem().toString();
             
        List<String> listAffich = new ArrayList<String>();
        listAffich.add("Female Smokers");
        listAffich.add("Male Smokers");
        listAffich.add("Median Age");
        listAffich.add("Population Density");
                
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("female_Smokers");
        listColumn.add("male_Smokers");
        listColumn.add("median_Age");
        listColumn.add("population_Density");
        
        if(!country_o.getSelectionModel().isEmpty()){ 
            String c = country_o.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i<listAffich.size();i++){
                XYChart.Series<String, Number> serie = new XYChart.Series<>();
                
                Number y = CovidDatas.getdataBarCharts("%2020", listColumn.get(i).toString(), c, cont);
                
                serie.getData().add(new XYChart.Data<>(listAffich.get(i).toString(), y));
                serie.setName(listAffich.get(i));
                otherChart.getData().add(serie);
            }

            otherChart.setTitle(" Population Density and People Smokers in "+c);           
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");            
        }
    }
    
    //-----------------Function for vaccnation statistics ----------------------
    @FXML
    public void ClickSearchVicc(){
        piechart.getData().clear();
        String continent = continent_v.getSelectionModel().getSelectedItem().toString();
        String month = month_v.getSelectionModel().getSelectedItem().toString();
        String annee = year_v.getSelectionModel().getSelectedItem().toString();
             
       List<String> listAffich = new ArrayList<String>();
        listAffich.add("Total Vaccinations");
        listAffich.add("People Vaccinated");
        listAffich.add("People Fully Vaccinated");
        //listAffich.add("New Vaccinations");
        
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("total_Vaccinations");
        listColumn.add("people_Vaccinated");
        listColumn.add("people_Fully_Vaccinated");
        //listColumn.add("new_Vaccinations");
        
        if(!country_v.getSelectionModel().isEmpty()){
            String country = country_v.getSelectionModel().getSelectedItem().toString();
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data(listAffich.get(0), Double.parseDouble(CovidDatas.getdataPieChart("'"+month+"/%/"+annee+"'", listColumn.get(0).toString(), country, continent).toString())),
                new PieChart.Data(listAffich.get(1), Double.parseDouble(CovidDatas.getdataPieChart("'"+month+"/%/"+annee+"'", listColumn.get(1).toString(), country, continent).toString())),
                new PieChart.Data(listAffich.get(2), Double.parseDouble(CovidDatas.getdataPieChart("'"+month+"/%/"+annee+"'", listColumn.get(2).toString(), country, continent).toString()))
                //new PieChart.Data(listAffich.get(3), Double.parseDouble(CovidDatas.getdataLineCharts("'"+month+"/%/"+annee+"'", listColumn.get(3).toString(), country, continent).toString()))
            );

            piechart.setTitle(" The evolution of the pandemic in "+country);
            piechart.setLabelLineLength(9);
            piechart.setData(pieData);   

            //final Label caption = new Label("");
            caption.setTextFill(Color.BLACK);
            caption.setStyle("-fx-font: 12 arial;");

            for (final PieChart.Data data : piechart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        caption.setText(String.valueOf((double)data.getPieValue()));
                    }
                });
            }
        }
        else{
            dialogueBox(Alert.AlertType.INFORMATION, "Inform", "choose a country");            
        }       
    }
    
    
    //--------------------------Function Inialize ------------------------------    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //******************************* ComboBOX => Monthly ***************
        //initialiser yearBox Mounthly:
        ObservableList<String> year = FXCollections.observableArrayList("2020", "2021");
        yearBox.setItems(year);
        yearBox.setValue("2020");
        //initialiser continent box Mounthly:
        ObservableList<String> continent = FXCollections.observableArrayList(CovidDatas.getAllContinent());
        continentBox.setItems(continent);
        continentBox.setValue("Africa");
        
        //initialiser continent box Mounthly:
        ObservableList<String> contry = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continentBox.getSelectionModel().getSelectedItem().toString()));
        countryBox.setItems(contry);
        countryBox.setValue("Morocco");

        //******************************* ComboBOX => daily ***************
        ObservableList<String> continent2 = FXCollections.observableArrayList(CovidDatas.getAllContinent());
        continent_D.setItems(continent2);
        continent_D.setValue("Africa");
        
        //initialiser continent:
        ObservableList<String> contry2 = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_D.getSelectionModel().getSelectedItem().toString()));
        country_D.setItems(contry2);
        country_D.setValue("Morocco");
        
        //initialiser date:
        datePicker.setValue(LocalDate.of(2020, 10, 10));
        
        //******************************* ComboBOX => Vaccined peaple ***************
        ///initialiser year:
        ObservableList<String> annee = FXCollections.observableArrayList("2020", "2021");
        year_v.setItems(annee);
        year_v.setValue("2021");
        //initialiser continent:
        ObservableList<String> c = FXCollections.observableArrayList(CovidDatas.getAllContinent());
        continent_v.setItems(c);
        continent_v.setValue("Africa");
        
        //initialiser continent:
        ObservableList<String> co = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_v.getSelectionModel().getSelectedItem().toString()));
        country_v.setItems(co);
        country_v.setValue("Morocco");
        
        //initialiser month:
        ObservableList<String> m = FXCollections.observableArrayList("1", "2","3", "4","5", "6","7", "8","9", "10","11", "12");
        month_v.setItems(m);
        month_v.setValue("1");
        
        //******************************* ComboBOX => others ***************
        ObservableList<String> cont = FXCollections.observableArrayList(CovidDatas.getAllContinent());
        continent_o.setItems(cont);
        continent_o.setValue("Africa");
        
        //initialiser continent:
        ObservableList<String> contry3 = FXCollections.observableArrayList(CovidDatas.getAllCountryByContinent(continent_o.getSelectionModel().getSelectedItem().toString()));
        country_o.setItems(contry3);
        country_o.setValue("Morocco");
        
        
        //afficher dans tableau :
        List data = CovidDatas.getAllData();
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_continent.setCellValueFactory(new PropertyValueFactory<>("continent"));
        c_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("dateA"));
        c_totalc.setCellValueFactory(new PropertyValueFactory<>("total_Cases"));
        c_totald.setCellValueFactory(new PropertyValueFactory<>("total_Deaths"));
        c_totalt.setCellValueFactory(new PropertyValueFactory<>("total_Tests"));
        c_strIndex.setCellValueFactory(new PropertyValueFactory<>("stringency_Index"));
        c_population.setCellValueFactory(new PropertyValueFactory<>("population"));
        c_median.setCellValueFactory(new PropertyValueFactory<>("median_Age"));
        
        tableData.getItems().addAll(data);
        
        //System.out.println(d);       
        }
}
