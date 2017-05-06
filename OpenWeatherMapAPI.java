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
    private GoodDayModel gdm = new GoodDayModel();

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
     * @author Alex
     * @return
     */
    public HashMap<String, HashMap<String, String>> openWeatherMap() {

        // VancouverID 6173331
        String cityID = gdm.getUserData().get(0);

        String requestURL = "http://api.openweathermap.org/data/2.5/forecast?id=" + cityID + "&APPID=" + this.getApiKey();
        // Result weather information from API
        String data;
        String line; // one line from API result or a file

        HashMap<String, HashMap<String, String>> allWeatherData = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // Flag witch allows connecting API or not
        boolean allowingConnectionFlag = getAllowingConnectionFlag();

        if (allowingConnectionFlag) { // Read data from API
            URL url = null;
            try {
                url = new URL(requestURL);
                InputStream is = url.openConnection().getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("URL error@OpenWeatherMapAPI");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException@OpenWeatherMapAPI:code1");
            }

        } else { // Data read from a local file
            try {
                File localFile = new File("src/weatherInformation");
                FileReader fileReader = null;
                fileReader = new FileReader(localFile);
                BufferedReader br = new BufferedReader(fileReader);

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("FileNotFoundException@OpenWeatherMapAPI");
            } catch (IOException e) {
                System.out.println("IOException@OpenWeatherMapAPI:code2");
            }
        }
        // Sets data from a file or API
        data = sb.toString();

        // Convert JSON
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

            String temp = getTemperature(jObject.getJSONObject("main").getDouble("temp"));

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

        // Save weather information(file)
        if (allowingConnectionFlag) {
            File file = new File("src/weatherInformation");
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(data);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException@OpenWeatherMapAPI:code3");
            }
        }

        return allWeatherData;
    }

    /**
     * Checks user data for unit user chose
     * uses Celcius temp as a base and from there calculates Farenheit
     *
     * @return temp C or F
     * @author Alex
     */

    public String getTemperature(double baseTemp) {

        String unit = gdm.getUserData().get(2); // checking which unit user chose C or F
        String temp = "";
        double double_temp = 0.0;

        if (unit.equals("Celsius") || unit.equals("C°")) {
            temp = String.format("%.2f", baseTemp - 273.15f) + "°";
            return temp;
        } else {
            temp = String.format("%.2f", ((baseTemp - 273.15f) * 9 / 5) + 32) + "°";
            return temp;
        }
    }

    /**
     * If 10 minutes has past after this program connect to API, return true.
     * Check when weatherInformation file has modified
     *
     * @return boolean true => allows connecting API, false => not allow connecting API
     * @throws IOException file read error, a user use this APP for the first time
     * @author Juria
     */
    public boolean getAllowingConnectionFlag() {

        Long lastModified;
        long currentTime;
        long timeDiff;

        try {
            File file = new File("src/weatherInformation");
            FileReader fileReader = new FileReader(file);

            // File time when it made
            lastModified = file.lastModified();

            // Check 10 mins
            currentTime = System.currentTimeMillis();
            timeDiff = currentTime - lastModified;

            if (timeDiff < 600000) return false;
            else return true;

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
