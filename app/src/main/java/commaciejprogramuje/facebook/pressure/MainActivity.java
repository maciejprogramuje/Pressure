package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 1;
    @Bind(R.id.measurement_history)
    RecyclerView measurementHistoryRecyclerView;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMeasurement();
            }
        });

        measurementHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        measurementHistoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyAdapter(measurementHistoryRecyclerView, PreferenceManager.getDefaultSharedPreferences(this));
        measurementHistoryRecyclerView.setAdapter(myAdapter);
    }

    private void goToMeasurement() {
        Intent intent = new Intent(MainActivity.this, MeasurementActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_ADD) {
                myAdapter.addNewMeasurement(data.getStringExtra(MeasurementActivity.PRESSURE_1_DATA),
                        data.getStringExtra(MeasurementActivity.PRESSURE_2_DATA),
                        data.getStringExtra(MeasurementActivity.PULSE_DATA));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_show_archive) {
            return true;
        } else if (id == R.id.action_add) {
            goToMeasurement();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
