package goodday;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        stage.setWidth(1200);
        stage.setHeight(800);

        // If user's setting doesn't exist, this skips Register Location Page
        if(!checkUserExists()) this.sendRegisterLocationPage("This is RegisterLocationPage.");
        else this.sendWeatherInformationPage("This is RegisterLocationPage.");

        stage.show();
    }


    public static void main(String[] args) throws IOException {

        // Debug code. you can switch debug mode to set true or false
        debugCode(true);

        launch(args);

    }

    /**
     * Debug code
     * You can check items and activities data in Model class
     *
     * @author Nobu
     * @param flag
     * @throws IOException
     */
    public static void debugCode(boolean flag) throws IOException {
        if (flag) {
            System.out.println("---DEBUG---");
            ArrayList<String> sample;

            GoodDayModel gdm = new GoodDayModel();
            sample = gdm.searchData(3,"3");

            for(String val: sample){
                System.out.println(val.toString());
            }
        }
    }


    /**
     * Goes to Register Location Page.
     *
     * @author Nobu
     *
     */
    public void sendRegisterLocationPage(String message){
        stage.setTitle("Register Location Page");
        RegisterLocationPageController rlpController = new RegisterLocationPageController(message);
        this.replaceSceneContent(rlpController);
    }

    /**
     * Goes to Weather Information Page.
     *
     * @author Nobu
     */
    public void sendWeatherInformationPage(String message){
        stage.setTitle("Weather Information Page");
        WeatherInformationPageController wipController = new WeatherInformationPageController(message);
        this.replaceSceneContent(wipController);
    }

    public void sendSettingPage(String message){
        stage.setTitle("Setting Page");
        SettingPageController spController = new SettingPageController();
        this.replaceSceneContent(spController);
    }

    /**
     * Changes a scene.
     *
     * @author Nobu
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
     * Checks user's setting file that exists or not.
     *
     * @author Nobu
     * @return boolean true => a file exists, false => a file doesn't exists
     * @throws IOException
     */
    public boolean checkUserExists() throws IOException{
        try {
            File file = new File("src/user_setting_file");
            FileReader fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Gets Instance
     *
     * @author Nobu
     */
    public static Main getInstance(){
        return instance;
    }
}
