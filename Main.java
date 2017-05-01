package goodday;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    // For changing scene
    private static Main instance;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        instance = this;

        // Setting stage
        stage = primaryStage;
        stage.setWidth(800);
        stage.setHeight(600);

        // Go to RegisterLocationPage
        this.sendRegisterLocationPage("This is RegisterLocationPage.");

        stage.show();
    }


    public static void main(String[] args) throws IOException {

        // Debug code. you can switch debug mode to set true or false
        debugCode(true);

        launch(args);

    }

    /**
     * Debug code
     * You can check items or activities sample data in Model class
     *
     *
     * @param flag
     * @throws IOException
     */
    public static void debugCode(boolean flag) throws IOException {
        if (flag) {
            System.out.println("---DEBUG---");
            ArrayList<String> sample;

            GoodDayModel gdm = new GoodDayModel();
            sample = gdm.searchData(1,"1");

            for(String val: sample){
                System.out.println(val.toString());
            }
        }
    }


    /**
     * Sample changing page method
     * Goes to RegisterLocationPage
     */
    public void sendRegisterLocationPage(String message){
        stage.setTitle("Register Location Page");
        RegisterLocationPageController rlpController = new RegisterLocationPageController(message);
        this.replaceSceneContent(rlpController);
    }

    public void sendWeatherInformationPage(String message){
        stage.setTitle("Weather Information Page");
        WeatherInformationPageController wipController = new WeatherInformationPageController(message);
        this.replaceSceneContent(wipController);
    }

    /**
     * Changes scene
     */
    private void replaceSceneContent(Parent controller){
        Scene scene = stage.getScene();
        if (scene == null){
            scene = new Scene(controller);
            stage.setScene(scene);
        }else{
            stage.getScene().setRoot(controller);
        }
    }
    /**
     * Gets Instance
     */
    public static Main getInstance(){
        return instance;
    }
}
