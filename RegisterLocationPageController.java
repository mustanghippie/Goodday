package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

/**
 * Created by Nobu on 2017/04/30.
 */
public class RegisterLocationPageController extends AnchorPane implements Initializable {

    @FXML
    Label errorLabelRegisterPage;
    @FXML
    TextField inputCityName;
    @FXML
    RadioButton fahrenheitRadioButton, celsiusRadioButton;

    GoodDayModel gdm = new GoodDayModel();

    private String message;

    // Constructor
    RegisterLocationPageController(String message) {
        this.message = message;

        loadFXML();
    }

    /**
     * Load FXML file.
     *
     * @throws IOException
     * @author Nobu
     */
    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterLocationPageView.fxml"));
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
     * Calls setUserSetting()@GoodDayModel to save user's setting
     * when a user click GO button.
     * Gets user's location from textField on a screen.
     * basically, Unit setting(Celsius and Fahrenheit) saves as Celsius
     *
     * @author Nobu
     * @return none
     * @throws IOException
     */
    public void setUserSetting(String location, int unit) throws IOException {
        gdm.setUserSetting(location, unit);
    }


    /**
     * Executes a click event when a user click GO button
     * on Register Location Page.
     * This method gets text from textField in Register Location Page.
     * And then this calls setUserSetting()@GoodDayModel.
     *
     * @author Nobu
     * @throws IOException
     */
    @FXML
    protected void handleButtonAction() throws IOException {

        try {
            if (inputCityName.getText().equals("")) throw new NotSetPropertyException("Please enter your location");
            if(celsiusRadioButton.isSelected() == false &&
                    fahrenheitRadioButton.isSelected() == false) throw new NotSetPropertyException("Please choose unit");

            // Sets unit that a user enter
            int unit;
            if(celsiusRadioButton.isSelected() == true) unit = 1;
            else unit = 2;

            this.setUserSetting(inputCityName.getText(), unit);
            Main.getInstance().sendWeatherInformationPage("This is Weather Information Page.");
        } catch (NotSetPropertyException e){
            errorLabelRegisterPage.setText(e.getMessage());
        }
    }

    /**
     * Suggests city name when a user input city name in text field.
     * --Notice--
     * There is a problem this suggestion method.
     * There is the same name in a country otherwise sometimes
     * this suggests the same name.
     *
     * @author Nobu
     * @param location
     * @param resources
     */
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
        TextFields.bindAutoCompletion(inputCityName, suggestion);
    }

    /**
     * Shows error message on Register Location Page
     * when a user doesn't enter location or unit setting.
     *
     * @author Nobu
     *
     */
    public class NotSetPropertyException extends IOException{
        public NotSetPropertyException(String message){
            super(message);
        }
    }
}
