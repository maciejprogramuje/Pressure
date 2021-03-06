package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataEntry extends AppCompatActivity {
    public static final String DATA_ENTRY_TEMP_PRESSURE_1 = "tempPressure1";
    public static final String DATA_ENTRY_TEMP_PRESSURE_2 = "tempPressure2";
    public static final String DATA_ENTRY_TEMP_PULSE = "tempPulse";
    public static final String SYS_INT = "sysInt";
    public static final String DIA_INT = "diaInt";
    public static final String PUL_INT = "pulInt";
    public static final String ONLY_ONE_TIME_FLAG = "only_one_time_flag";
    public static final String NUM_OF_MEASUREMENTS = "NUM_OF_MEASUREMENTS";
    public static final String TEMP_AVG_PRESSURE_1 = "temp_avg_pressure1";
    public static final String TEMP_AVG_PRESSURE_2 = "temp_avg_pressure2";
    public static final String TEMP_AVG_PULSE = "temp_avg_pulse";
    @Bind(R.id.sysNp)
    NumberPicker sysNp;
    @Bind(R.id.diaNp)
    NumberPicker diaNp;
    @Bind(R.id.pulNp)
    NumberPicker pulNp;
    int num_of_measurement;
    int sumOfPressure1;
    int sumOfPressure2;
    int sumOfPulse;
    boolean countAvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_entry);
        ButterKnife.bind(this);

        setDividerColor(sysNp, R.color.colorAccent);
        setDividerColor(diaNp, R.color.colorAccent);
        setDividerColor(pulNp, R.color.colorAccent);

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

        Intent intent = getIntent();
        sumOfPressure1 = intent.getIntExtra(TEMP_AVG_PRESSURE_1, 0);
        sumOfPressure2 = intent.getIntExtra(TEMP_AVG_PRESSURE_2, 0);
        sumOfPulse = intent.getIntExtra(TEMP_AVG_PULSE, 0);
        num_of_measurement = intent.getIntExtra(ChooseActivity.NUM_OF_MEASUREMENTS, 1);
        countAvg = intent.getBooleanExtra(SerialActivity.COUNT_AVG, false);
    }

    public void saveMeasurementsOnClick(View view) {
        num_of_measurement--;

        if(num_of_measurement == 0) {
            Intent data = new Intent(DataEntry.this, MainActivity.class);
            if(countAvg) {
                data.putExtra(DATA_ENTRY_TEMP_PRESSURE_1, String.valueOf(Math.round(sysNp.getValue() + sumOfPressure1) / 3));
                data.putExtra(DATA_ENTRY_TEMP_PRESSURE_2, String.valueOf(Math.round(diaNp.getValue() + sumOfPressure2) / 3));
                data.putExtra(DATA_ENTRY_TEMP_PULSE, String.valueOf((pulNp.getValue() + sumOfPulse) / 3));
            } else {
                data.putExtra(DATA_ENTRY_TEMP_PRESSURE_1, String.valueOf(sysNp.getValue() + sumOfPressure1));
                data.putExtra(DATA_ENTRY_TEMP_PRESSURE_2, String.valueOf(diaNp.getValue() + sumOfPressure2));
                data.putExtra(DATA_ENTRY_TEMP_PULSE, String.valueOf(pulNp.getValue() + sumOfPulse));
            }

            data.putExtra(ONLY_ONE_TIME_FLAG, true);
            startActivity(data);
        } else {
            Intent backToSerialActivity = new Intent(DataEntry.this, SerialActivity.class);
            backToSerialActivity.putExtra(NUM_OF_MEASUREMENTS, num_of_measurement);
            backToSerialActivity.putExtra(TEMP_AVG_PRESSURE_1, sysNp.getValue() + sumOfPressure1);
            backToSerialActivity.putExtra(TEMP_AVG_PRESSURE_2, diaNp.getValue() + sumOfPressure2);
            backToSerialActivity.putExtra(TEMP_AVG_PULSE, pulNp.getValue() + sumOfPulse);
            startActivity(backToSerialActivity);
        }
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

    private void setDividerColor(NumberPicker picker, int color) {
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException | IllegalAccessException | Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
