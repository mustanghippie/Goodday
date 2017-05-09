package goodday;

import net.sf.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by Nobu on 2017/04/28.
 */
public class GoodDayModel {

    public GoodDayModel() {
    }

    /**
     * Saves user's location that is inputted from keyboard as a file.
     * File data format:
     * {cityID}\n
     * {cityName}\n
     * {unit}\n
     *
     * @param location
     * @param unit     1 => Celsius, 2 => Fahrenheit
     * @return boolean If location can't find, return false
     * @throws IOException
     */
    public boolean setUserSetting(String location, int unit) {

        Map<String, String> userSetting = new LinkedHashMap<>();

        // Finds cityID from location
        String cityID = this.searchCityID(location);

        // If location can't find, return false
        if (cityID.equals("")) return false;

        String degree;
        if (unit == 1) degree = "Celsius";
        else degree = "Fahrenheit";

        // make json type
        userSetting.put("cityID", cityID);
        userSetting.put("cityName", location);
        userSetting.put("unit", degree);

        JSONObject jsonUserSetting = JSONObject.fromObject(userSetting);

        try {
            // Save user's setting as a file in local
            File file = new File("src/user_setting_file.json");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(jsonUserSetting.toString(4));
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FileWriteError@GoodDayModel:setUserSetting");
            return false;
        }
        return true;
    }

    /**
     * Finds cityID in cityID_list.json from location.
     * This method uses cityID_table(src/goodday/files/cityID_table.json).
     * If a location can't find a city id in cityID_table.json, this return "".
     *
     * @param location
     * @return cityID city name or "" when a location doesn't exist
     * @throws FileNotFoundException,IOException,net.sf.json.JSONException
     * @author Nobu
     */
    private String searchCityID(String location) {

        String cityID = "", line, cityIdTable;

        StringBuilder sb = new StringBuilder();
        File file = new File("src/goodday/files/cityID_table.json");
        FileReader filereader = null;

        try {
            filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            cityIdTable = sb.toString();
            // Convert JSON
            JSONObject jsonObject = JSONObject.fromObject(cityIdTable);
            //System.out.println(jsonObject);
            cityID = jsonObject.getString(location);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException@GoodDayModel:searchCityID");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException@GoodDayModel:searchCityID");
        } catch (net.sf.json.JSONException e) {
            // This location name doesn't exist
            cityID = "";
        }

        return cityID;
    }

    /**
     * Finds and reads user_setting_file.
     * Creates empty ArrayList and add to it user data:city Id, city name and temp. unit.
     *
     * @return userData ArrayList 0 => ID, 1 => city name, 2 => unit
     * @author Alex
     */
    public ArrayList<String> getUserData() {

        ArrayList<String> userData = new ArrayList<>();
        String unit;
        JSONObject jsonUserSetting = JSONObject.fromObject(this.fileReaderFunction("src/user_setting_file.json"));

        if (jsonUserSetting.getString("unit").equals("Celsius")) unit = "C°";
        else unit = "F°";

        userData.add(jsonUserSetting.getString("cityID"));// cityId index 0
        userData.add(jsonUserSetting.getString("cityName"));// city name index 1
        userData.add(unit); //temp unit index 2

        return userData;
    }

    /**
     * Obtains image path by using category.
     *
     * @param content
     * @param weather
     * @param temperature
     * @param windCondition
     * @return ArrayList image name (Activities, item_weather, item_temperature, wind condition)
     * @author Nobu
     */
    public ArrayList<String> findContentsImgName(String content, String weather, String temperature, String windCondition) {
        ArrayList<String> resultContents = new ArrayList<>();
        int temp = Integer.parseInt(temperature);
        String contentsJson;
        String category = "";

        // Sets category
        switch (content) {
            case "Activities":
                if (temp < 0) {
                    category = "Category1";
                    break;
                }

                if (weather.equals("Rainy")) {
                    category = "Category2";
                    break;
                }

                if (temp > 10 && !weather.equals("Rainy") &&
                        (windCondition.equals("LightBreeze") || windCondition.equals("GentleBreeze"))) {
                    category = "Category3";
                    break;
                }

                if (temp > 10 && !weather.equals("Rainy")) {
                    category = "Category4";
                    break;
                }
                category = "Category2";
                break;
            case "Items_weather":
                if (weather.equals("Thunderstorm")) {
                    category = "Category1";
                    break;
                }

                if (weather.equals("Drizzle")) {
                    category = "Category2";
                    break;
                }

                if (weather.equals("Rainy")) {
                    category = "Category3";
                    break;
                }

                if (weather.equals("Snowy")) {
                    category = "Category4";
                    break;
                }

                if (weather.equals("Sunny")) {
                    category = "Category5";
                    break;
                }

                if (weather.equals("Cloudy")) {
                    category = "Category6";
                    break;
                }
            case "Items_temp":
                if (temp < 5) {
                    category = "Category7";
                    break;
                }

                if (temp < 15) {
                    category = "Category8";
                    break;
                }

                if (temp > 30) {
                    category = "Category11";
                    break;
                }

                if (temp > 20) {
                    category = "Category10";
                    break;
                }

                if (temp >= 15) {
                    category = "Category9";
                    break;
                }

            default:
                category = "Category99";
        }

        if (content.equals("Items_weather") || content.equals("Items_temp")) content = "Items";
        // search contents by using category
        contentsJson = fileReaderFunction("src/goodday/files/Contents.json");

        // Converts json type
        JSONObject jsonContents = JSONObject.fromObject(contentsJson);

        for (int i = 0; ; i++) {
            try {
                resultContents.add(jsonContents.getJSONArray(content).getJSONObject(0).getJSONObject(category).getString(String.valueOf(i)));
            } catch (net.sf.json.JSONException e) {
                break;
            }
        }
        // if resultContents > 5 Shuffle  and delete over 5 content
        if (resultContents.size() > 5) {
            Collections.shuffle(resultContents);
            for (int i = resultContents.size() - 1; i >= 5; i--) {
                resultContents.remove(i);
            }
        }

        return resultContents;
    }

    /**
     * Reads a text or json file.
     *
     * @param filePath
     * @return String file content
     * @author Nobu
     */
    public String fileReaderFunction(String filePath) {
        String data = "";
        try {

            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(filePath + " is not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(filePath + " IOException");
        }

        return data;
    }

    /**
     * Deletes a file function.
     * If it could delete a file, return true.
     *
     * @param filePath
     * @return boolean delete successfully => true, fails to delete -> false
     */
    protected boolean deleteFileFunction(String filePath){

        File file = new File(filePath);

        if(file.exists()){
            file.delete();
            return true;
        }else{
            return false;
        }
    }

}