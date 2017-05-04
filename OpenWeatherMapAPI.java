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
 *
 */
public class OpenWeatherMapAPI {

    private String apiKey = "";
    private Map<String, String> resultWeatherInformation;

    public OpenWeatherMapAPI(){
        // Prepare open weather map API key
        String keyValue = "";
        try {
            // get API key
            File file = new File("src/key");
            FileReader fileReader = new FileReader(file);

            int ch;
            while((ch = fileReader.read()) != -1){
                keyValue += String.valueOf((char)ch);
            }

            this.setApiKey(keyValue);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("File open error");
        } catch (IOException e){
            System.out.println("File read error");
        }
    }

    public HashMap<String,String> openWeatherMap(){
        // VancouverID 6173331
        String requestURL = "http://api.openweathermap.org/data/2.5/forecast?id=6173331&APPID="+this.getApiKey();
        String data="";
        HashMap<String,String> resultWeatherInformation = new HashMap<>();

        // Get weather information from API
        try {
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

            /** ---Debug code--- */
//            File file = new File("src/sampleJavaFX/openWeatherMap/weatherInformation");
//            FileReader fileReader = new FileReader(file);
//            int ch;
//            while((ch = fileReader.read()) != -1){
//                data += String.valueOf((char)ch);
//            }
            /** /---Debug code--- */

            JSONObject jsonObject = JSONObject.fromObject(data);
            //System.out.println(jsonObject);
            JSONArray listArray = jsonObject.getJSONArray("list");
            JSONObject jobj = listArray.getJSONObject(0);
            //System.out.println(jobj.getJSONArray("weather").getJSONObject(0));

            String weather = jobj.getJSONArray("weather").getJSONObject(0).getString("main");
            String temp = String.format("%.2f", jobj.getJSONObject("main").getDouble("temp") - 273.15f) +"°";

            resultWeatherInformation.put("weather",weather);
            resultWeatherInformation.put("temp",temp);


            // Save weather information(JSON)
            File file = new File("src/weatherInformation");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(data);
            pw.close();

        } catch (MalformedURLException e) {
            System.out.println("URL error");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }

        return resultWeatherInformation;

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
