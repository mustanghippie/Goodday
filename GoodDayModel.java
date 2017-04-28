package goodday;

import java.util.ArrayList;

/**
 * Created by musta on 2017/04/28.
 */
public class GoodDayModel {


    public class SportActivityArray {
        private String[][] activitiesList = new String[2][2];

        // Constructor
        public SportActivityArray(){
            /**
             * -Category-
             * 1 => Weather is snowy
             * 2 => Indoor/stay at home/good for rainy and snowy days
             * 3 => wind & not rainy - temp more that 10C and less than 35C, not rainy
             * 4 => Outdoor / mild weather - temp more than 10C and less than 35C, not rainy
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

        }


        public ArrayList<String> getActivities(String category){
            ArrayList<String > resultActivities = new ArrayList<>();

            for(int i=0;i<this.activitiesList.length;i++){
                if(this.activitiesList[i][1].equals(category)){
                    resultActivities.add(this.activitiesList[i][0]);
                }
            }
            return resultActivities;

        }
    }
}
