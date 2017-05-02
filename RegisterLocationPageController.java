package goodday;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

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
     * @return none
     * @throws IOException
     * @author Nobu
     */
    public void setUserSetting(String location, int unit) throws IOException {
        //gdm.setUserSetting(location, unit);
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
    protected void handleButtonAction() throws IOException {
        // Register user's setting
        this.setUserSetting(inputCityName.getText(), 1);
        Main.getInstance().sendWeatherInformationPage("This is Weather Information Page.");
    }

    /**
     * Add rototype suggestion function.
     *
     *
     * @author Nobu
     * @param location
     * @param resources
     * @todo Choose countries and save deta
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] suggestion = {"Alex","Yuria","Paulo", "Nobuaki"};
//        String[] suggestion = new String[209579];
//
//        try {
//            File file = new File("src/goodday/city_name");
//            FileReader filereader = new FileReader(file);
//            BufferedReader br = new BufferedReader(filereader);
//
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < suggestion.length; i++) {
//                if (br.readLine() == null) break;
//                suggestion[i] = br.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("}");
//        }
//        System.out.println(Arrays.asList(suggestion));
        TextFields.bindAutoCompletion(inputCityName, suggestion);
    }
}
