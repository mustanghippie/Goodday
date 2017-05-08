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
    private ImageView imageActivitie1, imageActivitie2, imageActivitie3, imageActivitie4, imageActivitie5, itemWeatherImage, itemTempImage, windConditionImage, backgroundImage;

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
        // Contents name for getting file name
        ArrayList<String> contentsArrayList = new ArrayList<>();

        String timeIndex = "";

        switch (btnTime){
            case ("btnNow"):
                timeIndex = "now";
                break;
            case ("btn3hour"):
                timeIndex = "in3";
                break;
            case ("btn6hour"):
                timeIndex = "in6";
                break;
            case ("btn9hour"):
                timeIndex = "in9";
                break;
            default:
                System.out.println("Make sure btnTime@WeatherInformationController");
        }

        labelWeather.setText(weatherInformation.get(timeIndex).get("weather"));
        labelTemp.setText(weatherInformation.get(timeIndex).get("temp"));
        // Gets activities name by using category
        contentsArrayList = gdm.findContentsImgName("Activities", weatherInformation.get(timeIndex).get("weather"),
                weatherInformation.get(timeIndex).get("temp"), weatherInformation.get(timeIndex).get("windCondition"));

        // Activity images
        try {
            imageActivitie1.setImage(makeImagePath("Activities", contentsArrayList.get(0)));
            imageActivitie2.setImage(makeImagePath("Activities", contentsArrayList.get(1)));
            imageActivitie3.setImage(makeImagePath("Activities", contentsArrayList.get(2)));
            imageActivitie4.setImage(makeImagePath("Activities", contentsArrayList.get(3)));
            imageActivitie5.setImage(makeImagePath("Activities", contentsArrayList.get(4)));
        } catch (IndexOutOfBoundsException e) {
            // resultContents < 5
        }
        contentsArrayList.clear();

        // Item weather images
        contentsArrayList = gdm.findContentsImgName("Items_weather", weatherInformation.get(timeIndex).get("weather"),
                weatherInformation.get(timeIndex).get("temp"), weatherInformation.get(timeIndex).get("windCondition"));
        itemWeatherImage.setImage(makeImagePath("Item_weather", contentsArrayList.get(0)));
        contentsArrayList.clear();

        // Item temperature images
        contentsArrayList = gdm.findContentsImgName("Items_temp", weatherInformation.get(timeIndex).get("weather"),
                weatherInformation.get(timeIndex).get("temp"), weatherInformation.get(timeIndex).get("windCondition"));
        itemTempImage.setImage(makeImagePath("Item_temp", contentsArrayList.get(0)));
        contentsArrayList.clear();

        // Wind condition images
        windConditionImage.setImage(makeImagePath("Item_wind", weatherInformation.get(timeIndex).get("windCondition")));

        // Sets back ground image
        backgroundImage.setImage(makeImagePath("bg", weatherInformation.get(timeIndex).get("weather")));
        // Time buttons
        btn3hour.setText(weatherInformation.get("in3").get("time"));
        btn6hour.setText(weatherInformation.get("in6").get("time"));
        btn9hour.setText(weatherInformation.get("in9").get("time"));
    }

    /**
     * Makes image path.
     *
     * @author Nobu
     * @param fileName
     * @return Image
     */
    private Image makeImagePath(String content, String fileName){
        Image image = null;
        try {
            image = new Image("/goodday/img/"+content+"/"+fileName+".png");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("[img is not found] " +"/goodday/img/"+content+"/"+fileName+".png");
        }

        return image;
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
        // Initialize default information(now)
        changeLayoutByHour("btnNow");

    }
}