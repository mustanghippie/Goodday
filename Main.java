package goodday;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    // For changing scene
    private static Main instance;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        instance = this;

        // Setting stage
        stage = primaryStage;
        stage.setWidth(1280);
        stage.setHeight(800);

        // If user's setting doesn't exist, this skips Register Location Page
        if(!checkUserExists()) this.sendRegisterLocationPage();
        else this.sendWeatherInformationPage();

        stage.show();
    }


    public static void main(String[] args) throws IOException {

        // Debug code. you can switch debug mode to set true or false
        debugCode(false);

        // Connects Firebase
        FirebaseConnectionClass firebase = new FirebaseConnectionClass();

        launch(args);

    }

    /**
     * Debug code
     * You can check items and activities data
     *
     * @author Nobu
     * @param flag
     * @throws IOException
     */
    public static void debugCode(boolean flag) throws IOException {
        if (flag) {
            System.out.println("---DEBUG---");

            UploadContentsClass ucc = new UploadContentsClass();
            System.out.println(ucc.getActivitiesMap());
            System.out.println(ucc.getItemListMap());
            System.out.println(ucc.getWindMap());

        }
    }


    /**
     * Goes to Register Location Page.
     *
     * @author Nobu
     *
     */
    public void sendRegisterLocationPage(){
        stage.setTitle("Register Location Page");
        RegisterLocationPageController rlpController = new RegisterLocationPageController();
        this.replaceSceneContent(rlpController);
    }

    /**
     * Goes to Weather Information Page.
     *
     * @author Nobu
     */
    public void sendWeatherInformationPage(){
        stage.setTitle("Weather Information Page");
        WeatherInformationPageController wipController = new WeatherInformationPageController();
        this.replaceSceneContent(wipController);
    }

    public void sendSettingPage(){
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
    private boolean checkUserExists() throws IOException{
        try {
            File file = new File("src/user_setting_file.json");
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
