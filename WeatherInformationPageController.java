package goodday;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Nobu on 2017/04/30.
 */
public class WeatherInformationPageController extends AnchorPane implements Initializable{

    @FXML
    private Label labelLocation, labelWeather, labelTemp, labelUnit;
    @FXML
    private Button btn3hour, btn6hour, btn9hour;
    @FXML
    private ImageView imageActivitie1;

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
    protected void changeWeatherTimeBtn(ActionEvent ae){

        Button b = (Button)ae.getSource();
        String buttonID = b.getId();

        changeLayoutByHour(buttonID);

    }

    public void changeLayoutByHour(String btnTime){

        // Weather information from Open Weather Map API
        HashMap<String, HashMap<String, String>> weatherInformation = owma.openWeatherMap();

        switch (btnTime){
            case ("btnNow"):
                labelWeather.setText(weatherInformation.get("now").get("weather"));
                labelTemp.setText(weatherInformation.get("now").get("temp"));
                break;
            case ("btn3hour"):
                labelWeather.setText(weatherInformation.get("in3").get("weather"));
                labelTemp.setText(weatherInformation.get("in3").get("temp"));
                break;
            case ("btn6hour"):
                labelWeather.setText(weatherInformation.get("in6").get("weather"));
                labelTemp.setText(weatherInformation.get("in6").get("temp"));
                break;
            case ("btn9hour"):
                labelTemp.setText(weatherInformation.get("in9").get("weather"));
                labelTemp.setText(weatherInformation.get("in9").get("temp"));
                break;
            default:
        }

        // Sets time each buttons
        btn3hour.setText(weatherInformation.get("in3").get("time"));
        btn6hour.setText(weatherInformation.get("in6").get("time"));
        btn9hour.setText(weatherInformation.get("in9").get("time"));
    }

    /**
     * Connects methods based on weather data and display them for the user.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // user data 0 => ID, 1 => city name, 2 => unit
        ArrayList<String> userData = gdm.getUserData();

        // Sets a location
        labelLocation.setText(userData.get(1));
        // Sets unit
        labelUnit.setText(userData.get(2));

        changeLayoutByHour("btnNow");
        Image image = null;

        try {
            // Notice path starts from /goodday
            //image = new Image("/goodday/img/icon_sports/sport_01.png");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        //imageActivitie1.setImage(image);
        //formatting temperature to display positive or negative integer number:
//System.out.println("loop initialize");
        //System.out.println(owma.openWeatherMap());
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
