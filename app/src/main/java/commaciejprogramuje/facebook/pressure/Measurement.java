package commaciejprogramuje.facebook.pressure;

/**
 * Created by m.szymczyk on 2017-07-25.
 */

class Measurement {
    private String date;
    private String pressure1;
    private String pressure2;
    private String pulse;

    Measurement(String date, String pressure1, String pressure2, String pulse) {
        this.date = date;
        this.pressure1 = pressure1;
        this.pressure2 = pressure2;
        this.pulse = pulse;
    }

    String getDate() {
        return date;
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
