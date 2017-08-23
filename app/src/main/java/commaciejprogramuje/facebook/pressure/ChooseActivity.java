package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {
    public static final String NUM_OF_MEASUREMENTS = "NUM_OF_MEASUREMENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void singleMeasurementOnClick(View view) {
        Intent intent = new Intent(ChooseActivity.this, DataEntry.class);
        intent.putExtra(NUM_OF_MEASUREMENTS, 1);
        startActivity(intent);
    }

    public void serialMeasurementOnClick(View view) {
        Intent intent = new Intent(ChooseActivity.this, SerialActivity.class);
        intent.putExtra(NUM_OF_MEASUREMENTS, 3);
        startActivity(intent);
    }
}
