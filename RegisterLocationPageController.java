package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nobu on 2017/04/30.
 */
public class RegisterLocationPageController extends AnchorPane implements Initializable {

//    @FXML
//    Label label;
    @FXML
    TextField inputCityName;

    GoodDayModel gdm = new GoodDayModel();

    private String message;

    // Constructor
    RegisterLocationPageController(String message){
        this.message = message;

        loadFXML();
    }

    /**
     * Load FXML file.
     *
     * @author Nobu
     * @throws IOException
     */
    private void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterLocationPageView.fxml"));
        fxmlLoader.setRoot(this);

        // Sets controller in fxml file myself
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Calls setUserSetting()@GoodDayModel to save user's setting
     * when a user click GO button.
     * Gets user's location from textField on a screen.
     * basically, Unit setting(Celsius and Fahrenheit) saves as Celsius
     *
     * @author Nobu
     * @return none
     * @throws IOException
     */
    public void setUserSetting(String location, int unit) throws IOException{
        gdm.setUserSetting(location, unit);
    }


    /**
     * Executes a click event when a user click GO button
     * on Register Location Page.
     * This method gets text from textField in Register Location Page.
     * And then this calls setUserSetting()@GoodDayModel.
     *
     * @throws IOException
     */
    @FXML
    protected void handleButtonAction() throws IOException{
        // Register user's setting
        this.setUserSetting(inputCityName.getText(), 1);
        Main.getInstance().sendWeatherInformationPage("This is Weather Information Page.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //label.setText(message);
    }
}
