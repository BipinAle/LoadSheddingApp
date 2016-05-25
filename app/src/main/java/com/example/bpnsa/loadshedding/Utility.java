package com.example.bpnsa.loadshedding;

/**
 * Created by bipin on 5/25/16.
 */
public class Utility {

    public static String getDayFromNumber(int i) {
        switch (i) {
            case 1:
                return "Sunday";

            case 2:
                return "Monday";

            case 3:
                return "Tuesday";

            case 4:
                return "Wednesday";

            case 5:
                return "Thursday";

            case 6:
                return "Friday";

            case 7:
                return "Saturday";

            default:
                return "";

        }

    }
    public static int convertToMinute(String time) {//09:30

        String hr = time.substring(0, 2);
        int hour= Integer.parseInt(hr);
        String mn = time.substring(3, 5);
        int minute=Integer.parseInt(mn);
        return hour * 60 +minute;

    }
}
