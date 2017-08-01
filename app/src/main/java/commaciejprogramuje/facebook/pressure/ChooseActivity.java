package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SINGLE_CHOOSE = 3;
    public static final String CHOOSE_ACTIVITY_TEMP_PRESSURE_1 = "chooseActivityTempPressure1";
    public static final String CHOOSE_ACTIVITY_TEMP_PRESSURE_2 = "chooseActivityTempPressure2";
    public static final String CHOOSE_ACTIVITY_TEMP_PULSE = "chooseActivityTempPulse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void singleMeasurement(View view) {
        Intent intent = new Intent(ChooseActivity.this, SingleActivity.class);
        startActivity(intent);
    }

    public void serialMeasurement(View view) {
    }
}
