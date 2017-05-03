package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Paulo on 02/05/2017.
 */
public class SettingPageController extends AnchorPane implements Initializable{


    public SettingPageController(){

        loadFXML();

    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SettingPage.fxml"));
        fxmlLoader.setRoot(this);

        // Sets controller in fxml file myself
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
  
    //----------------------------------
    //  Button to change input location
    //----------------------------------
    @FXML
    protected void changeLocation() {

    }

    @FXML
    //---------------------------------------------------
    //  Button to convert temperature unit to Fahrenheit
    //---------------------------------------------------
    protected void convertToFahrenheit() {

    }

    @FXML
    //------------------------------------------------
    //  Button to convert temperature unit to Celsius
    //------------------------------------------------
    protected void convertToCelsius () {

    }
}
