package goodday;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Nobu on 2017/04/30.
 */
public class WeatherInformationPageController extends AnchorPane implements Initializable{

    @FXML
    private Label labelLocation, labelWeather, labelTemp, labelUnit;
    private AnchorPane anchorPaneBack;

    private GoodDayModel gdm = new GoodDayModel();
    private OpenWeatherMapAPI owma = new OpenWeatherMapAPI();

    private String message;

    public WeatherInformationPageController(String message){
        this.message = message;

        loadFXML();

    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("WeatherInformationPageView.fxml"));
        fxmlLoader.setRoot(this);

        // Sets controller in fxml file myself
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void GoToSettingPage(){
        Main.getInstance().sendSettingPage("This is Setting Page.");
    }

    @FXML
    protected void inThreeHourAction(){
        // read 3 hour data
    }

    /**
     * Connects methods based on weather data and display them for the user.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

// if user press button NOW:


        //formatting temperature to display positive or negative integer number:
//System.out.println("loop initialize");
        System.out.println(owma.openWeatherMap());
//        owma.openWeatherMap().get("now").;
//        int comma = temp_format.indexOf(".");
//        String temp_label = temp_format.substring(0,comma);
//
//       //connecting labels to methods:'
//      labelLocation.setText(gdm.getUserData().get(1));
//      labelWeather.setText(owma.openWeatherMap().get("now"));
//      labelTemp.setText(temp_label);
//      labelUnit.setText(gdm.getUserData().get(2));

    }
}
