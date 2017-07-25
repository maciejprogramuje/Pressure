package commaciejprogramuje.facebook.pressure;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeasurementActivity extends AppCompatActivity {
    @Bind(R.id.timeTextView)
    TextView timeTextView;
    int secondsToCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_measurement);
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
                NavUtils.navigateUpFromSameTask(MeasurementActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
