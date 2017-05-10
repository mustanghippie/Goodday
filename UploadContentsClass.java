package goodday;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nobu on 2017/05/08.
 */
public class UploadContentsClass {

    // Item data
    private String[][] itemList = new String[11][2];
    private HashMap<String, Object> itemListMap = new HashMap<>();
    // Activities data
    private String[][] activitiesList = new String[24][2];
    private HashMap<String, Object> activitiesMap = new HashMap<>();
    // Wind conditions data
    private String[][] windList = new String[8][2];
    private HashMap<String, Object> windMap = new HashMap<>();

    /**
     * Constructor
     * <p>
     * Prepares data for the following contents.
     * Data type is this APP's contents such as activities and items.
     * -Data type:
     * 1 -> Activities
     * 2 -> Item
     * 3 -> Wind
     * <p>
     * Each content has category to obtain recommended information.
     * -About detail categories, please make sure constructor's comment
     *
     * @return none
     */
    public UploadContentsClass() {
        /**
         * -Activities
         *  Category-
         *    1 => Weather is less than 0C & snow  - 5 elements
         *    2 => Suitable for rainy days - MakeCityJson category, stay at home - 5 elements
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
        this.activitiesList[2][0] = "IceSkating";
        this.activitiesList[2][1] = "1";
        this.activitiesList[3][0] = "Hockey";
        this.activitiesList[3][1] = "1";
        this.activitiesList[4][0] = "Snowmobile";
        this.activitiesList[4][1] = "1";
        this.activitiesList[5][0] = "Yoga";
        this.activitiesList[5][1] = "2";
        this.activitiesList[6][0] = "Swimming";
        this.activitiesList[6][1] = "2";
        this.activitiesList[7][0] = "Darts";
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
        this.activitiesList[14][0] = "Yachting";
        this.activitiesList[14][1] = "3";
        this.activitiesList[15][0] = "Volleyball";
        this.activitiesList[15][1] = "4";
        this.activitiesList[16][0] = "Jogging";
        this.activitiesList[16][1] = "4";
        this.activitiesList[17][0] = "Camping";
        this.activitiesList[17][1] = "4";
        this.activitiesList[18][0] = "Horseriding";
        this.activitiesList[18][1] = "4";
        this.activitiesList[19][0] = "Tennis";
        this.activitiesList[19][1] = "4";
        this.activitiesList[20][0] = "Climbing";
        this.activitiesList[20][1] = "4";
        this.activitiesList[21][0] = "Baseball";
        this.activitiesList[21][1] = "4";
        this.activitiesList[22][0] = "Basketball";
        this.activitiesList[22][1] = "4";
        this.activitiesList[23][0] = "Soccer";
        this.activitiesList[23][1] = "4";



        int mapIndex = 0;
        HashMap<String,Object> category1 = new HashMap<>();
        HashMap<String,Object> category2 = new HashMap<>();
        HashMap<String,Object> category3 = new HashMap<>();
        HashMap<String,Object> category4 = new HashMap<>();
        // Category1
        for (int i = 0; i < 5; i++) {
            category1.put(String.valueOf(i), this.activitiesList[mapIndex][0]);
            mapIndex++;
        }
        // Category2
        for (int i = 0; i < 5; i++) {
            category2.put(String.valueOf(i), this.activitiesList[mapIndex][0]);
            mapIndex++;
        }
        // Category3
        for (int i = 0; i < 5; i++) {
            category3.put(String.valueOf(i), this.activitiesList[mapIndex][0]);
            mapIndex++;
        }
        // Category4
        for (int i = 0; i < 9; i++) {
            category4.put(String.valueOf(i), this.activitiesList[mapIndex][0]);
            mapIndex++;
        }
        getActivitiesMap().put("Category1",category1);
        getActivitiesMap().put("Category2",category2);
        getActivitiesMap().put("Category3",category3);
        getActivitiesMap().put("Category4",category4);

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
        this.itemList[0][0] = "No data";
        this.itemList[0][1] = "1";
        this.itemList[1][0] = "Rain coat";
        this.itemList[1][1] = "2";
        this.itemList[2][0] = "Rain boots";
        this.itemList[2][1] = "3";
        this.itemList[3][0] = "Winter boots";
        this.itemList[3][1] = "4";
        this.itemList[4][0] = "Sunglasses";
        this.itemList[4][1] = "5";
        this.itemList[5][0] = "Nothing special";
        this.itemList[5][1] = "6";

        //-----------------------------------
        // ITEMS RELATED TO TEMPERATURE
        //-----------------------------------
        this.itemList[6][0] = "Winter coat";
        this.itemList[6][1] = "7";
        this.itemList[7][0] = "Light jacket";
        this.itemList[7][1] = "8";
        this.itemList[8][0] = "Sweatpants";
        this.itemList[8][1] = "9";
        this.itemList[9][0] = "T-shirt";
        this.itemList[9][1] = "10";
        this.itemList[10][0] = "Water bottle";
        this.itemList[10][1] = "11";

        for (int i = 0; i < this.itemList.length; i++) {
            getItemListMap().put("Category"+String.valueOf(i+1), makeFirebaseData(i, "itemList"));
        }

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
        this.windList[1][0] = "LightAir";
        this.windList[1][1] = "2";
        this.windList[2][0] = "LightBreeze";
        this.windList[2][1] = "3";
        this.windList[3][0] = "GentleBreeze";
        this.windList[3][1] = "4";
        this.windList[4][0] = "ModerateBreeze";
        this.windList[4][1] = "5";
        this.windList[5][0] = "FreshBreeze";
        this.windList[5][1] = "6";
        this.windList[6][0] = "StrongBreeze";
        this.windList[6][1] = "7";
        this.windList[7][0] = "HighWind";
        this.windList[7][1] = "8";

        for (int i = 0; i < this.windList.length; i++) {
            getWindMap().put("Category"+String.valueOf(i+1), makeFirebaseData(i, "windList"));
        }

        //System.out.println(windMap);

    }

    public HashMap<String, Object> makeFirebaseData(int index, String dataType) {
        HashMap<String, Object> result = new HashMap<>();
        switch (dataType){
            case "itemList":
                result.put("0", this.itemList[index][0]);
                break;
            case "windList":
                result.put("0", this.windList[index][0]);
                break;
            default:

        }

        return result;
    }

    /**
     * Searches data that provide user with GoodDay's contents.
     *
     * @param dataType make sure comment of this class's constructor
     * @param category make sure comment of this class's constructor
     * @return ArrayList, which is contained appropriate data by data type and category
     * @throws IOException
     * @author Nobu
     * @todo delete
     */
    public ArrayList<String> searchData(int dataType, String category) throws IOException {

        ArrayList<String> resultData = new ArrayList<>();

        String[][] searchingDataArray = this.getDataType(dataType);

        for (int i = 0; i < searchingDataArray.length; i++) {
            if (searchingDataArray[i][1].equals(category)) {
                resultData.add(searchingDataArray[i][0]);
            }
        }

        return resultData;
    }

    /**
     * Obtains array data such as activitiesList and itemList,
     * depending on dataType.
     *
     * @param dataType 1 -> activitiesList
     *                 2 -> itemList
     *                 3 -> windList
     * @return A two-dimensional array, depending on dataType
     * @author Nobu
     * @Throws NotFoundDataTypeException when {dataType < 1 or dataType > 2}
     * @todo delete
     */
    private String[][] getDataType(int dataType) throws NotFoundDataTypeException {
        switch (dataType) {
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

    public HashMap<String, Object> getItemListMap() {
        return itemListMap;
    }

    public void setItemListMap(HashMap<String, Object> itemListMap) {
        this.itemListMap = itemListMap;
    }

    public HashMap<String, Object> getActivitiesMap() {
        return activitiesMap;
    }

    public void setActivitiesMap(HashMap<String, Object> activitiesMap) {
        this.activitiesMap = activitiesMap;
    }

    public HashMap<String, Object> getWindMap() {
        return windMap;
    }

    public void setWindMap(HashMap<String, Object> windMap) {
        this.windMap = windMap;
    }

    public class NotFoundDataTypeException extends IOException {
        public NotFoundDataTypeException(String message) {
            super(message);
        }
    }
}
