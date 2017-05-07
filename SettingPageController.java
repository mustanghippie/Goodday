package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Paulo on 02/05/2017.
 */
public class SettingPageController extends AnchorPane implements Initializable {

    private GoodDayModel gdm = new GoodDayModel();

    @FXML
    TextField locationName;
    @FXML
    RadioButton celsiusRadioButton;
    @FXML
    RadioButton fahrenheitRadioButton;
    @FXML
    Label messageLabel;
    @FXML
    Button closeSettingPageBtn;

    public SettingPageController() {

        loadFXML();

    }

    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SettingPage.fxml"));
        fxmlLoader.setRoot(this);

        // Sets controller in fxml file myself
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * APPLY CHANGES BUTTON
     * This method applies all the changes made to location
     * and temperature unit.
     *
     * @param
     * @param
     * @return
     * @author Paulo
     */
    @FXML
    protected void applyChanges() {

        String location = locationName.getText();
        int unit = 0;

        if (location.equals("")) location = gdm.getUserData().get(1);

        if (fahrenheitRadioButton.isSelected() == true) unit = 2;
        if (celsiusRadioButton.isSelected() == true) unit = 1;

        gdm.setUserSetting(location, unit);

    }

    @FXML
    public void goToWeatherInformationPage() {
        Main.getInstance().sendWeatherInformationPage("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // User setting
        ArrayList<String> userData = gdm.getUserData();
        // Sets user's city name
        locationName.setPromptText(userData.get(1));
        // Sets user's unit
        if (userData.get(2).equals("C°")) celsiusRadioButton.setSelected(true);
        else fahrenheitRadioButton.setSelected(true);

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

    /**
     * Shows error message on Register Location Page
     * when a user doesn't enter location or unit setting.
     *
     * @author Nobu
     */
    public class NotSetPropertyException extends IOException {
        public NotSetPropertyException(String message) {
            super(message);
        }
    }
}
