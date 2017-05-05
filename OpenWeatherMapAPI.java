package goodday;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Nobu on 2017/04/27.
 *
 * Using json-lib
 * http://json-lib.sourceforge.net/
 */
public class OpenWeatherMapAPI {

    private String apiKey = "";
    private Map<String, String> resultWeatherInformation;
    GoodDayModel gdm = new GoodDayModel();

    public OpenWeatherMapAPI() {
        // Prepare open weather map API key
        String keyValue = "";
        try {
            // get API key
            File file = new File("src/key");
            FileReader fileReader = new FileReader(file);

            int ch;
            while ((ch = fileReader.read()) != -1) {
                keyValue += String.valueOf((char) ch);
            }

            this.setApiKey(keyValue);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("File open error");
        } catch (IOException e) {
            System.out.println("File read error");
        }
    }

    /**
     *
     *
     * @author Alex
     * @return
     */
    public HashMap<String, HashMap<String, String>> openWeatherMap() {
        // VancouverID 6173331
        String cityID = gdm.getUserData().get(0);
        System.out.println(cityID);
        //String requestURL = "http://api.openweathermap.org/data/2.5/weather?id=6173331&APPID=" + this.getApiKey(); // Todo fix id
        String requestURL = "http://api.openweathermap.org/data/2.5/forecast?id=" + cityID + "&APPID=" + this.getApiKey(); // Todo fix id
        // Weather information
        String data = "";
        boolean allowingConnectionFlag = getAllowingConnectionFlag(); // Todo we have to make timer function
        //getAllowingConnectionFlag(requestURL);
        // allowingConnectionFlag = getAllowingConnectionFlag();
        HashMap<String, HashMap<String, String>> allWeatherData = new HashMap<>();

        // Get weather information from API
        try {
            if (allowingConnectionFlag) { // Using API
                URL url = new URL(requestURL);
                InputStream is = url.openConnection().getInputStream();

                // JSON parse String
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while (null != (line = reader.readLine())) {
                    sb.append(line);
                }
                // Weather information
                data = sb.toString();
            } else { // Using local data which is weather information
                File debugFile = new File("src/weatherInformation");
                FileReader fileReader = new FileReader(debugFile);

                BufferedReader br = new BufferedReader(fileReader);
                String str;
                data = "";

                while ((str = br.readLine()) != null) {
                    data += str;
                }
            }

            JSONObject jsonObject = JSONObject.fromObject(data);
            JSONArray listArray = jsonObject.getJSONArray("list");

            HashMap<String, String> nowWeatherData = new HashMap<>();
            HashMap<String, String> in3WeatherData = new HashMap<>();
            HashMap<String, String> in6WeatherData = new HashMap<>();
            HashMap<String, String> in9WeatherData = new HashMap<>();
            JSONObject jObject;

            for (int i = 0; i < 4; i++) {
                jObject = listArray.getJSONObject(i);

                String weather = jObject.getJSONArray("weather").getJSONObject(0).getString("main");
                // Todo We have to make calculate degree method
                // String temp = getTemperature(jObject.getJSONObject("main").getDouble("temp"));
                String temp = String.format("%.2f", jObject.getJSONObject("main").getDouble("temp") - 273.15f) + "°";

                switch (i) {
                    case 0:
                        nowWeatherData.put("weather", weather);
                        nowWeatherData.put("temp", temp);
                        break;
                    case 1:
                        in3WeatherData.put("weather", weather);
                        in3WeatherData.put("temp", temp);
                        break;
                    case 2:
                        in6WeatherData.put("weather", weather);
                        in6WeatherData.put("temp", temp);
                        break;
                    case 3:
                        in9WeatherData.put("weather", weather);
                        in9WeatherData.put("temp", temp);
                        break;
                    default:
                        System.out.println("Ouf of index");

                }
            }

            allWeatherData.put("now", nowWeatherData);
            allWeatherData.put("in3", in3WeatherData);
            allWeatherData.put("in6", in6WeatherData);
            allWeatherData.put("in9", in9WeatherData);

            // Save weather information(JSON)
            if(allowingConnectionFlag) { // Todo we have to make timer function
                File file = new File("src/weatherInformation");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(data);
                pw.close();
            }

        } catch (MalformedURLException e) {
            System.out.println("URL error@OpenWeatherMapAPI");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException@OpenWeatherMapAPI");
            e.printStackTrace();
        }

        return allWeatherData;
    }

    public String getTemperature(double temp){

        // Get unit of user's setting

        // Calculate Celsius if unit is Celsius
        // String.format("%.2f", temp - 273.15f) + "°";

        // Calculate Celsius if unit is Fahrenheit
        //

        // return temp
        return "";
    }

    public boolean getAllowingConnectionFlag(){
        /**
         *
         * If 10 minutes has past after this program connect to API, return true.
         * Memo:
         * File.lastModified();
         *
         */
        try {
            File debugFile = new File("src/weatherInformation");
            FileReader fileReader = new FileReader(debugFile);

            return false;
        } catch (IOException e) {
            return true;
        }

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
