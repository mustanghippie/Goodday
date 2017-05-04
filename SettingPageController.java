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

    private GoodDayModel gdm = new GoodDayModel();

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




    /** CONVERT TO FAHRENHEIT BUTTON
     * This method is in charge to convert the temperature unit
     * to Fahrenheit after clicking on "Fahrenheit(°F)" radio button.
     * @author Paulo
     * @param
     * @param
     * @return
     */
    @FXML
    protected void convertToFahrenheit() {

    }



    /** CONVERT TO CELSIUS BUTTON
     * This method is in charge to convert the temperature unit
     * to Celsius after clicking on "Celsius(°C)" radio button.
     * @author Paulo
     * @param
     * @param
     * @return
     */
    @FXML
    protected void convertToCelsius () {
    }


    /** APPLY CHANGES BUTTON
     * This method applies all the changes made to location
     * and temperature unit.
     * @author Paulo
     * @param
     * @param
     * @return
     */
    @FXML
    protected void applyChanges() {

    }


}
