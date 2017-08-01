package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingleActivity extends AppCompatActivity {
    public static final String SINGLE_ACTIVITY_TEMP_PRESSURE_1 = "tempPressure1";
    public static final String SINGLE_ACTIVITY_TEMP_PRESSURE_2 = "tempPressure2";
    public static final String SINGLE_ACTIVITY_TEMP_PULSE = "tempPulse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
    }

    public void singleMeasurementOnClick(View view) {
        Measurements.measurements.


        Intent data = new Intent(SingleActivity.this, MainActivity.class);
        data.putExtra(SINGLE_ACTIVITY_TEMP_PRESSURE_1, "p1");
        data.putExtra(SINGLE_ACTIVITY_TEMP_PRESSURE_2, "p2");
        data.putExtra(SINGLE_ACTIVITY_TEMP_PULSE, "tP");

        https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application


        startActivity(data);
        finish();
    }
}
