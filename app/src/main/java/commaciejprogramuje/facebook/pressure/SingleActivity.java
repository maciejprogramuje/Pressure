package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleActivity extends AppCompatActivity {
    public static final String SINGLE_ACTIVITY_TEMP_PRESSURE_1 = "tempPressure1";
    public static final String SINGLE_ACTIVITY_TEMP_PRESSURE_2 = "tempPressure2";
    public static final String SINGLE_ACTIVITY_TEMP_PULSE = "tempPulse";
    public static final String SYS_INT = "sysInt";
    public static final String DIA_INT = "diaInt";
    public static final String PUL_INT = "pulInt";
    public static final String ONLY_ONE_TIME_FLAG = "only_one_time_flag";
    @Bind(R.id.sysNp)
    NumberPicker sysNp;
    @Bind(R.id.diaNp)
    NumberPicker diaNp;
    @Bind(R.id.pulNp)
    NumberPicker pulNp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        sysNp.setMinValue(40);
        sysNp.setMaxValue(200);
        diaNp.setMinValue(40);
        diaNp.setMaxValue(200);
        pulNp.setMinValue(30);
        pulNp.setMaxValue(150);
        sysNp.setValue(120);
        diaNp.setValue(80);
        pulNp.setValue(60);
        sysNp.setWrapSelectorWheel(true);
        diaNp.setWrapSelectorWheel(true);
        pulNp.setWrapSelectorWheel(true);
    }

    public void singleMeasurementOnClick(View view) {
        Intent data = new Intent(SingleActivity.this, MainActivity.class);
        data.putExtra(SINGLE_ACTIVITY_TEMP_PRESSURE_1, String.valueOf(sysNp.getValue()));
        data.putExtra(SINGLE_ACTIVITY_TEMP_PRESSURE_2, String.valueOf(diaNp.getValue()));
        data.putExtra(SINGLE_ACTIVITY_TEMP_PULSE, String.valueOf(pulNp.getValue()));
        data.putExtra(ONLY_ONE_TIME_FLAG, true);
        startActivity(data);
    }

    @OnClick({R.id.sysNp, R.id.diaNp, R.id.pulNp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sysNp:
                break;
            case R.id.diaNp:
                break;
            case R.id.pulNp:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SYS_INT, sysNp.getValue());
        outState.putInt(DIA_INT, diaNp.getValue());
        outState.putInt(PUL_INT, pulNp.getValue());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sysNp.setValue(savedInstanceState.getInt(SYS_INT));
        diaNp.setValue(savedInstanceState.getInt(DIA_INT));
        pulNp.setValue(savedInstanceState.getInt(PUL_INT));
    }
}
