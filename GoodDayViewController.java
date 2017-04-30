package goodday;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GoodDayViewController implements Initializable {
    private GoodDayModel gdm = new GoodDayModel();

    @FXML
    private Label itemName;

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void setContentsToScreen(ActionEvent event) throws IOException{
        // ------------ Sample code ------------ //
        ArrayList<String> resultItem = gdm.searchData(2,"1");
        String displayData = "";
        for(String itemName: resultItem){
            displayData += itemName;
        }
        itemName.setText(displayData);
        // ------------ Sample code ------------ //
    }

    @FXML
    public void setUserSetting(){

    }

    @FXML
    public void getUserSetting(){

    }

    @FXML
    public void setWeatherInformationToScreen(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
