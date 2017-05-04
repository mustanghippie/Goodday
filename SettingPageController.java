package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Paulo on 02/05/2017.
 */
public class SettingPageController extends AnchorPane implements Initializable{

    private GoodDayModel gdm = new GoodDayModel();

    @FXML
    TextField locationName;
    @FXML
    RadioButton convertToFahrenheit;
    @FXML
    RadioButton convertToCelsius;

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
        /*
        if (unit == 1) // if input unit is celsius (unit 1)
        {
            //  convert to fahrenheit (unit 2)
        }
        */
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

        /*
        if (unit == 2)  // if input unit is fahrenheit (unit 2)
        {
            //  convert to celsius (unit 1)
        }
        */

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
        //System.out.println(locationName.getText());
        String location = locationName.getText();

        try {
            gdm.setUserSetting(location,2);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("--- IOException|@applyChanges---");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // suggestion function
        String[] suggestion = new String[35586];

        try {
            File file = new File("src/goodday/CityData/city_name_list");
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < suggestion.length; i++) {
                suggestion[i] = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        }
        TextFields.bindAutoCompletion(locationName, suggestion);
    }
}
