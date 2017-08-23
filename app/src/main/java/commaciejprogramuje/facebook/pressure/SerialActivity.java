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
    @Bind(R.id.timeTextView)
    TextView timeTextView;
    @Bind(R.id.numOfMesurementsLeftTextView)
    TextView numOfMesurementsLeftTextView;
    @Bind(R.id.cancelWaitingButton)
    Button cancelWaitingButton;
    int secondsToCount = 10;
    int numOfMeasurements;
    CountDownTimer cutDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_serial);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        numOfMeasurements = intent.getIntExtra(ChooseActivity.NUM_OF_MEASUREMENTS, 1);
        numOfMesurementsLeftTextView.setText(String.valueOf(numOfMeasurements));

        sitAndWait();
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

    public void cancelWaitingButtonOnClick(View view) {
        cutDownTimer.cancel();
        dontWaitAnyLonger();
    }

    void sitAndWait() {
        cutDownTimer = new CountDownTimer(1000 * secondsToCount, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(l / 1000 + " " + getString(R.string.seconds));
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
        startActivity(intent);
    }
}
