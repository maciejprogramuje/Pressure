package commaciejprogramuje.facebook.pressure;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Random;

/**
 * Created by m.szymczyk on 2017-07-25.
 */

class SingleMeasurement {
    private DateTime date;
    private String pressure1;
    private String pressure2;
    private String pulse;

    private static String[] tempPressure1 = {"150", "160", "170", "180", "190"};
    private static String[] tempPressure2 = {"90", "80", "70", "60", "50"};
    private static String[] tempPulse = {"60", "61", "62", "63", "64"};

    SingleMeasurement() {
        date = new DateTime();
        Random random = new Random();
        pressure1 = tempPressure1[random.nextInt(tempPressure1.length)];
        pressure2 = tempPressure2[random.nextInt(tempPressure2.length)];
        pulse = tempPulse[random.nextInt(tempPulse.length)];
    }

    String getDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d/MM/yyyy, HH:mm");
        return date.toString(dateTimeFormatter);
    }

    String getPressure1() {
        return pressure1;
    }

    String getPressure2() {
        return pressure2;
    }

    String getPulse() {
        return pulse;
    }
}
