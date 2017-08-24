package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SerialActivity extends AppCompatActivity {
    public static final String COUNT_AVG = "countAvg";
    public static final String KEY_SECONDS_TO_COUNT = "keySecondsToCount";
    @Bind(R.id.timeTextView)
    TextView timeTextView;
    @Bind(R.id.numOfMesurementsLeftTextView)
    TextView numOfMesurementsLeftTextView;
    @Bind(R.id.cancelWaitingButton)
    Button cancelWaitingButton;
    int secondsToCount = 60;
    int numOfMeasurements;
    CountDownTimer cutDownTimer;
    int sumOfPressure1;
    int sumOfPressure2;
    int sumOfPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_serial);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        sumOfPressure1 = intent.getIntExtra(DataEntry.TEMP_AVG_PRESSURE_1, 0);
        sumOfPressure2 = intent.getIntExtra(DataEntry.TEMP_AVG_PRESSURE_2, 0);
        sumOfPulse = intent.getIntExtra(DataEntry.TEMP_AVG_PULSE, 0);
        numOfMeasurements = intent.getIntExtra(ChooseActivity.NUM_OF_MEASUREMENTS, 1);
        numOfMesurementsLeftTextView.setText(String.valueOf(numOfMeasurements));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sitAndWait();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                cutDownTimer.cancel();
                NavUtils.navigateUpFromSameTask(SerialActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cancelWaitingButtonOnClick(View view) {
        cutDownTimer.cancel();
        dontWaitAnyLonger();
    }

    void sitAndWait() {
        cutDownTimer = new CountDownTimer(1000 * secondsToCount, 1000) {
            @Override
            public void onTick(long l) {
                secondsToCount = (int) l / 1000;
                timeTextView.setText(secondsToCount + " " + getString(R.string.seconds));
            }

            @Override
            public void onFinish() {
                dontWaitAnyLonger();
            }
        };
        cutDownTimer.start();
    }

    private void dontWaitAnyLonger() {
        Intent intent = new Intent(SerialActivity.this, DataEntry.class);
        intent.putExtra(ChooseActivity.NUM_OF_MEASUREMENTS, numOfMeasurements);
        intent.putExtra(DataEntry.TEMP_AVG_PRESSURE_1, sumOfPressure1);
        intent.putExtra(DataEntry.TEMP_AVG_PRESSURE_2, sumOfPressure2);
        intent.putExtra(DataEntry.TEMP_AVG_PULSE, sumOfPulse);
        intent.putExtra(COUNT_AVG, true);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SECONDS_TO_COUNT, secondsToCount);
        cutDownTimer.cancel();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        secondsToCount = savedInstanceState.getInt(KEY_SECONDS_TO_COUNT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cutDownTimer.cancel();
    }
}
