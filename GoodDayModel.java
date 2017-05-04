package goodday;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nobu on 2017/04/28.
 */
public class GoodDayModel {

    // Item data
    private String[][] itemList = new String[8][2];
    // Activities data
    private String[][] activitiesList = new String[24][2];
    // Wind conditions data
    private String[][] windList = new String [8][2];

    /** Constructor
     *
     * Prepares data for the following contents.
     * Data type is this APP's contents such as activities and items.
     * -Data type:
     *  1 -> Activities
     *  2 -> Item
     *  3 -> Wind
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
         *    1 => Weather is less than 0C & snow  - 5 elements
         *    2 => Suitable for rainy days - backup category, stay at home - 5 elements
         *    3 => Temp more than 10C, not rainy (wind is between 1 and 5m/s) - 5 elements
         *    4 => Temp more than 10C, not rainy -  9 elements
         *
         * @Author Alex
         */

        /* 24 sport activities: */
        this.activitiesList[0][0] = "Snowboarding";
        this.activitiesList[0][1] = "1";
        this.activitiesList[1][0] = "Skiing";
        this.activitiesList[1][1] = "1";
        this.activitiesList[2][0] = "Ice Skating";
        this.activitiesList[2][1] = "1";
        this.activitiesList[3][0] = "Hockey";
        this.activitiesList[3][1] = "1";
        this.activitiesList[4][0] = "Snowschoeing";
        this.activitiesList[4][1] = "1";
        this.activitiesList[5][0] = "Yoga";
        this.activitiesList[5][1] = "2";
        this.activitiesList[6][0] = "Swimming";
        this.activitiesList[6][1] = "2";
        this.activitiesList[7][0] = "Bouldering";
        this.activitiesList[7][1] = "2";
        this.activitiesList[8][0] = "Badminton";
        this.activitiesList[8][1] = "2";
        this.activitiesList[9][0] = "Gym";
        this.activitiesList[9][1] = "2";
        this.activitiesList[10][0] = "Surfing";
        this.activitiesList[10][1] = "3";
        this.activitiesList[11][0] = "Kitesurfing";
        this.activitiesList[11][1] = "3";
        this.activitiesList[12][0] = "Windsurfing";
        this.activitiesList[12][1] = "3";
        this.activitiesList[13][0] = "Paragliding";
        this.activitiesList[13][1] = "3";
        this.activitiesList[14][0] = "Base jumping";
        this.activitiesList[14][1] = "3";
        this.activitiesList[15][0] = "Hiking";
        this.activitiesList[15][1] = "4";
        this.activitiesList[16][0] = "Jogging";
        this.activitiesList[16][1] = "4";
        this.activitiesList[17][0] = "Camping";
        this.activitiesList[17][1] = "4";
        this.activitiesList[18][0] = "Horseback riding";
        this.activitiesList[18][1] = "4";
        this.activitiesList[19][0] = "Tennis";
        this.activitiesList[19][1] = "4";
        this.activitiesList[20][0] = "Climbing";
        this.activitiesList[20][1] = "4";
        this.activitiesList[21][0] = "Triathlon";
        this.activitiesList[21][1] = "4";
        this.activitiesList[22][0] = "Basketball";
        this.activitiesList[22][1] = "4";
        this.activitiesList[23][0] = "Soccer";
        this.activitiesList[23][1] = "4";

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

        //-----------------------------------
        // ITEMS RELATED TO WEATHER
        //-----------------------------------
        this.itemList[0][0] = "Sunglasses";
        this.itemList[0][1] = "1";
        this.itemList[1][0] = "Umbrella";
        this.itemList[1][1] = "2";
        this.itemList[2][0] = "Winter boots";
        this.itemList[2][1] = "3";

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

        /* -WIND-
         *  Categories
         *
         *  1 =>   0.0   to   0.3 m/s
         *  2 =>   0.4   to   1.5 m/s
         *  3 =>   1.6   to   3.3 m/s
         *  4 =>   3.4   to   5.5 m/s
         *  5 =>   5.6   to   7.9 m/s
         *  6 =>   8.0   to  10.7 m/s
         *  7 =>  10.8   to  13.8 m/s
         *  8 => higher than 13.8 m/s
         *
         *  @Author Paulo
         */

        //---------------------
        //  8 WIND CONDITIONS
        //---------------------
        this.windList[0][0] = "Calm";
        this.windList[0][1] = "1";
        this.windList[1][0] = "Light air";
        this.windList[1][1] = "2";
        this.windList[2][0] = "Light breeze";
        this.windList[2][1] = "3";
        this.windList[3][0] = "Gentle breeze";
        this.windList[3][1] = "4";
        this.windList[4][0] = "Moderate breeze";
        this.windList[4][1] = "5";
        this.windList[5][0] = "Fresh breeze";
        this.windList[5][1] = "6";
        this.windList[6][0] = "Strong breeze";
        this.windList[6][1] = "7";
        this.windList[7][0] = "High wind";
        this.windList[7][1] = "8";

    } //    End of GoodDayModel()

    //======================================================================================

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
     *  3 -> windList
     * @return A two-dimensional array, depending on dataType
     * @Throws NotFoundDataTypeException when {dataType < 1 or dataType > 2}
     */
    private String[][] getDataType(int dataType) throws NotFoundDataTypeException{
        switch (dataType){
            case 1:
                return this.activitiesList;
            case 2:
                return this.itemList;
            case 3:
                return this.windList;
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
     * File data format:
     *   {cityID}\n
     *   {cityName}\n
     *   {unit}\n
     *
     * @param location
     * @param unit 1 => Celsius, 2 => Fahrenheit
     * @return
     * @throws IOException
     */
    public boolean setUserSetting(String location, int unit) throws IOException{

        // Finds cityID from location
        String cityID = this.searchCityID(location);

        // Save user's setting as a file in local
        PrintWriter pw = null;
        try {
            File file = new File("src/user_setting_file");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            pw = new PrintWriter(bw);
        } catch (FileNotFoundException e) {
            System.out.println("user_setting_file is not found.");
            System.exit(1);
        }

        String degree;

        if(unit == 1) degree = "Celsius";
        else degree = "Fahrenheit";
        pw.println(cityID +"\n" +location +"\n" +degree);
        pw.close();

        return true;
    }

    /**
     * Finds cityID from location.
     * This method uses cityID_table(src/goodday/CityData/cityID_table).
     *
     * @param location
     * @return cityID
     */
    private String searchCityID(String location){

        String cityID="";

        try {
            File file = new File("src/goodday/CityData/cityID_table");
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);

            while((cityID = br.readLine()) != null){
                if(cityID.indexOf("," +location) == -1) continue;
                cityID = cityID.substring(0,cityID.indexOf(","));
                break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        }
        return cityID;
    }

    /**
     * Finds and reads user_setting_file.
     * Creates empty arraylist and add to it user data:city Id, city name and temp. unit.
     *
     * @return cityName
     */
    public ArrayList<String> getUserData() {


        ArrayList<String> userData = new ArrayList<>();


        try {
            File file = new File("src/user_setting_file");
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);

           userData.add(br.readLine());// cityId index 0
           userData.add(br.readLine()); //cityname index 1
            String unit = "";
            if(br.readLine().equals("Celsius")){
                unit = "C°";
            }else {
                unit = "F°";
            }
            userData.add(unit); //temp unit index 2


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return userData;
    }



    public class NotFoundDataTypeException extends IOException{
        public NotFoundDataTypeException(String message){super(message);}
    }
}
