package goodday;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;

/**
 * Created by Nobu on 2017/04/28.
 */
public class GoodDayModel {

    // Item data
    private String[][] itemList = new String[8][2];
    // Activities data
    private String[][] activitiesList = new String[22][2];

    /** Constructor
     *
     * Prepares data for the following contents.
     * Data type is this APP's contents such as activities and items.
     * -Data type:
     *  1 -> Activities
     *  2 -> Item
     *
     * Each content has category to obtain recommended information.
     * -About detail categories, please make sure constructor's comment
     *
     * @return none
     *
     */
    public GoodDayModel () {

        /**
         * -Activities
         *  Category-
         *    1 => Weather is snowy
         *    2 => Indoor/stay at home/good for rainy and snowy days
         *    3 => wind & not rainy - temp more that 10C and less than 35C, not rainy
         *    4 => Outdoor / mild weather - temp more than 10C and less than 35C, not rainy
         *
         * @Author Alex
         */

        /* 22 sport activities: */
        this.activitiesList[0][0] = "Skiing/Snowboarding";
        this.activitiesList[0][1] = "1";
        this.activitiesList[1][0] = "Hiking";
        this.activitiesList[1][1] = "4";
        this.activitiesList[2][0] = "Yoga" ;
        this.activitiesList[2][1] = "2";
        this.activitiesList[3][0] = "Horseback riding";
        this.activitiesList[3][1] = "4";
        this.activitiesList[4][0] = "Climbing";
        this.activitiesList[4][1] = "4";
        this.activitiesList[5][0] = "Surfing";
        this.activitiesList[5][1] = "3";
        this.activitiesList[6][0] = "Jogging";
        this.activitiesList[6][1] = "4";
        this.activitiesList[7][0] = "Tennis";
        this.activitiesList[7][1] = "4";
        this.activitiesList[8][0] = "Swimming";
        this.activitiesList[8][1] = "2";
        this.activitiesList[9][0] = "Camping";
        this.activitiesList[9][1] = "4";
        this.activitiesList[10][0] = "Canoeing";
        this.activitiesList[10][1] = "4";
        this.activitiesList[11][0] = "Bouldering";
        this.activitiesList[11][1] = "2";
        this.activitiesList[12][0] = "Rock climbing";
        this.activitiesList[12][1] = "4";
        this.activitiesList[13][0] = "Triathlon";
        this.activitiesList[13][1] = "4";
        this.activitiesList[14][0] = "Snowshoeing";
        this.activitiesList[14][1] = "1";
        this.activitiesList[15][0] = "Badminton";
        this.activitiesList[15][1] = "2";
        this.activitiesList[16][0] = "Basketball";
        this.activitiesList[16][1] = "4";
        this.activitiesList[17][0] = "Hockey";
        this.activitiesList[17][1] = "1";
        this.activitiesList[18][0] = "Baseball";
        this.activitiesList[18][1] = "4";
        this.activitiesList[19][0] = "Soccer";
        this.activitiesList[19][1] = "4";
        this.activitiesList[20][0] = "Volleyball";
        this.activitiesList[20][1] = "4";
        this.activitiesList[21][0] = "Kite surfing";
        this.activitiesList[21][1] = "3";

        /**
         * -ITEMS-
         *  Category-
         *
         *  RELATED TO WEATHER
         *    1 => Weather is sunny
         *    2 => Weather is rainy
         *    3 => Weather is snowy.
         *
         *  RELATED TO TEMPERATURE
         *    4 => Temperature is less than 5C.
         *    5 => Temperature is more than 5C and less than 15C.
         *    6 => Temperature is more than 15C and less than 20C.
         *    7 => Temperature is more than 20C and less than 30C.
         *    8 => Temperature is more than 30C.
         *
         * @Author Paulo
         *
         */
//=============================================================================
        //-----------------------------------
        // ITEMS RELATED TO WEATHER
        //-----------------------------------
        this.itemList[0][0] = "Sunglasses";
        this.itemList[0][1] = "1";
        this.itemList[1][0] = "Umbrella";
        this.itemList[1][1] = "2";
        this.itemList[2][0] = "Winter boots";
        this.itemList[2][1] = "3";
//=============================================================================
        //-----------------------------------
        // ITEMS RELATED TO TEMPERATURE
        //-----------------------------------
        this.itemList[3][0] = "Winter coat";
        this.itemList[3][1] = "4";
        this.itemList[4][0] = "Light jacket";
        this.itemList[4][1] = "5";
        this.itemList[5][0] = "Sweatpants";
        this.itemList[5][1] = "6";
        this.itemList[6][0] = "T-shirt";
        this.itemList[6][1] = "7";
        this.itemList[7][0] = "Water bottle";
        this.itemList[7][1] = "8";
//=============================================================================


    /**
     *
     * Searches data that provide user with GoodDay's contents.
     *
     * @author Nobu
     * @param dataType
     *  make sure comment of this class's constructor
     * @param category
     *  make sure comment of this class's constructor
     * @return ArrayList, which is contained appropriate data by data type and category
     * @throws IOException
     */
    public ArrayList<String> searchData(int dataType, String category) throws IOException{

        ArrayList<String > resultData = new ArrayList<>();

        String[][] searchingDataArray = this.getDataType(dataType);

        for(int i=0;i<searchingDataArray.length;i++){
            if(searchingDataArray[i][1].equals(category)){
                resultData.add(searchingDataArray[i][0]);
            }
        }

        return resultData;
    }

    /**
     *
     * Obtains array data such as activitiesList and itemList,
     * depending on dataType.
     *
     * @author Nobu
     * @param dataType
     *  1 -> activitiesList
     *  2 -> itemList
     * @return A two-dimensional array, depending on dataType
     * @Throws NotFoundDataTypeException when {dataType < 1 or dataType > 2}
     * @Todo We'll have to add wind data type
     */
    private String[][] getDataType(int dataType) throws NotFoundDataTypeException{
        switch (dataType){
            case 1:
                return this.activitiesList;
            case 2:
                return this.itemList;
            default:
                try {
                    throw new NotFoundDataTypeException("[Make sure dataType] dataType is not found !!");
                } catch (NotFoundDataTypeException e) {
                    e.printStackTrace();
                    System.out.println("!!!System down!!!");
                    System.exit(1);
                    // meaningless code
                    String[][] error = {};
                    return error;
                }
        }
    }

    /**
     * Saves user's location that is inputted from keyboard as a file.
     *
     * @param location
     * @param unit 1 => Celsius, 2 => Fahrenheit
     * @return
     * @throws IOException
     */
    public boolean setUserSetting(String location, int unit) throws IOException{

        // Save user's setting as a file in local
        File file = new File("src/user_setting_file");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        PrintWriter pw = new PrintWriter(bw);

        String degree;

        if(unit == 1) degree = "Celsius";
        else degree = "Fahrenheit";
        pw.println(location +"," +degree);
        pw.close();

        return true;
    }

    public class NotFoundDataTypeException extends IOException{
        public NotFoundDataTypeException(String message){super(message);}
    }
}
