package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SerialActivity extends AppCompatActivity {
    public static final String PRESSURE_1_DATA = "pressure1Data";
    public static final String PRESSURE_2_DATA = "pressure2Data";
    public static final String PULSE_DATA = "pulseData";
    @Bind(R.id.timeTextView)
    TextView timeTextView;
    int secondsToCount;

    private static String[] tempPressure1 = {"150", "160", "170", "180", "190"};
    private static String[] tempPressure2 = {"90", "80", "70", "60", "50"};
    private static String[] tempPulse = {"60", "61", "62", "63", "64"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_serial);
        ButterKnife.bind(this);

        secondsToCount = 10;
        CountDownTimer cutDownTimer = new CountDownTimer(1000 * secondsToCount, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(getString(R.string.time_counting, l / 1000));
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        cutDownTimer.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(SerialActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNewMeasurementOnClick(View view) {
        Random random = new Random();

        Intent data = new Intent();
        data.putExtra(PRESSURE_1_DATA, tempPressure1[random.nextInt(tempPressure1.length)]);
        data.putExtra(PRESSURE_2_DATA, tempPressure2[random.nextInt(tempPressure2.length)]);
        data.putExtra(PULSE_DATA, tempPulse[random.nextInt(tempPulse.length)]);

        setResult(RESULT_OK, data);
        finish();
    }
}
