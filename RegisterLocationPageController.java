package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nobu on 2017/04/30.
 */
public class RegisterLocationPageController extends AnchorPane implements Initializable {

    @FXML
    Label label;

    private String message;

    // Constructor
    RegisterLocationPageController(String message){
        this.message = message;

        loadFXML();
    }

    /**
     * Load FXML file
     *
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
     * Sample button action
     */
    @FXML
    protected void handleButtonAction(){
        Main.getInstance().sendWeatherInformationPage("This is Weather Information Page.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(message);
    }
}
