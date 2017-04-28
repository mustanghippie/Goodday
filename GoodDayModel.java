package goodday;
/**
 * Created by musta on 2017/04/28.
 */
public class GoodDayModel {

    private String[][] itemList = new String[6][2];

    // Constructor using array list containing items related to each category
    public GoodDayModel () {
        /*
         ITEMS
            0 => Sunglasses
            1 => Umbrella
            2 => Coat
            3 => Winter boots
            4 => Sunscreen
            5 => T-shirt

        =================================================================

        CATEGORIES
            1 => weather is sunny.
            2 => weather is rainy.
            3 => temperature is less than 15C.
            4 => Weather is rain or snow, and temperature is less than 5C.
            5 => temperature is more than 20C.
         */

        this.itemList[0][0] = "Sunglasses";
        this.itemList[0][1] = "1";

        this.itemList[1][0] = "Umbrella";
        this.itemList[1][1] = "2";

        this.itemList[2][0] = "Coat";
        this.itemList[2][1] = "3";

        this.itemList[3][0] = "Winter boots";
        this.itemList[3][1] = "4";

        this.itemList[4][0] = "Sunscreen";
        this.itemList[4][1] = "5";

        this.itemList[5][0] = "T-shirt";
        this.itemList[5][1] = "5";
    }
}
