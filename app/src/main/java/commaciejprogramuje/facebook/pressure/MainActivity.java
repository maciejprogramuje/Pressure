package commaciejprogramuje.facebook.pressure;

import android.content.Intent;
import android.content.res.Configuration;
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

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setImageResource(R.drawable.ic_fab_add);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseTypeOfMeasurement();
                }
            });
        }

        measurementHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        measurementHistoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyAdapter(measurementHistoryRecyclerView, PreferenceManager.getDefaultSharedPreferences(this));
        measurementHistoryRecyclerView.setAdapter(myAdapter);

        Intent intent = getIntent();
        if(intent.getBooleanExtra(DataEntry.ONLY_ONE_TIME_FLAG, false)) {
            myAdapter.addNewMeasurement(intent.getStringExtra(DataEntry.DATA_ENTRY_TEMP_PRESSURE_1),
                    intent.getStringExtra(DataEntry.DATA_ENTRY_TEMP_PRESSURE_2),
                    intent.getStringExtra(DataEntry.DATA_ENTRY_TEMP_PULSE));
            intent.putExtra(DataEntry.ONLY_ONE_TIME_FLAG, false);
        }
    }

    private void chooseTypeOfMeasurement() {
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_clear_all) {
            myAdapter.clearAll(MainActivity.this);
            return true;
        } else if (id == R.id.action_add) {
            chooseTypeOfMeasurement();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
