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

    public  HashMap<String, Object> openWeatherMap(){
        // VancouverID 6173331
        String requestURL = "http://api.openweathermap.org/data/2.5/forecast?id=6173331&APPID="+this.getApiKey();
        String data="";
        HashMap<String,Object> allWeatherData = new HashMap<>();

        // Get weather information from API
        try {
//            URL url = new URL(requestURL);
//            InputStream is = url.openConnection().getInputStream();
//
//            // JSON parse String
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while (null != (line = reader.readLine())) {
//                sb.append(line);
//            }
//            // Weather information
//            data = sb.toString();

            /** ---Debug code--- */
            File debugFile = new File("src/weatherInformation");
            FileReader fileReader = new FileReader(debugFile);

            BufferedReader br = new BufferedReader(fileReader);
            String str;
            data = "";

            while((str = br.readLine()) != null){
                data += str;
            }
            /** /---Debug code--- */

            JSONObject jsonObject = JSONObject.fromObject(data);
            //System.out.println(jsonObject);
            JSONArray listArray = jsonObject.getJSONArray("list");
            //JSONObject jobj = listArray.getJSONObject(0);

            HashMap<String, String> nowWeatherData =new HashMap<>();
            HashMap<String, String> in3WeatherData =new HashMap<>();
            HashMap<String, String> in6WeatherData =new HashMap<>();
            HashMap<String, String> in9WeatherData =new HashMap<>();
            JSONObject jobj;

            for (int i=0;i<4;i++) {
                jobj = listArray.getJSONObject(i);

                //System.out.println(jobj.getJSONArray("weather").getJSONObject(0));

                String weather = jobj.getJSONArray("weather").getJSONObject(0).getString("main");
                String temp = String.format("%.2f", jobj.getJSONObject("main").getDouble("temp") - 273.15f) + "°";

                switch (i){
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


        return allWeatherData;


    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
