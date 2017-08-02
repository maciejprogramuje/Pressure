package commaciejprogramuje.facebook.pressure;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.szymczyk on 2017-07-26.
 */

class MyAdapter extends RecyclerView.Adapter {
    private static final String MEASUREMENTS_J_SON = "measurementsJSon";
    private RecyclerView measurementsRecyclerView;
    private SharedPreferences sharedPreferences;
    private List<SingleMeasurement> measurements = new ArrayList<>();

    //implementacja wzorca ViewHolder
    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView pressure1;
        TextView pressure2;
        TextView pulse;

        MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateTV);
            pressure1 = itemView.findViewById(R.id.pressure1TV);
            pressure2 = itemView.findViewById(R.id.pressure2TV);
            pulse = itemView.findViewById(R.id.pulseTV);
        }
    }
    //koniec implementacji wzorca ViewHolder

    MyAdapter(RecyclerView measurementsRecyclerView, SharedPreferences sharedPreferences) {
        this.measurementsRecyclerView = measurementsRecyclerView;
        this.sharedPreferences = sharedPreferences;

        Gson gson = new Gson();
        String json = sharedPreferences.getString(MEASUREMENTS_J_SON, "[]");
        measurements = gson.fromJson(json, new TypeToken<List<SingleMeasurement>>() {}.getType());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_measurement, parent, false);

        //usuwanie po kliknięciu
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLineAlertDialog(v).show();
            }
        });

        return new MyViewHolder(view);
    }

    void addNewMeasurement(String pressure1, String pressure2, String pulse) {
        measurements.add(new SingleMeasurement(getCurrentDate(), pressure1, pressure2, pulse));
        notifyItemInserted(measurements.size() - 1);
        storeInPreferences();
    }

    private String getCurrentDate() {
        DateTime tempDate = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d/MM/yyyy, HH:mm");
        return tempDate.toString(dateTimeFormatter);
    }

    private void storeInPreferences() {
        Gson gson = new Gson();
        String json = gson.toJson(measurements, new TypeToken<List<SingleMeasurement>>() { }.getType());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MEASUREMENTS_J_SON, json);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SingleMeasurement singleMeasurement = measurements.get(position);

        ((MyViewHolder) holder).date.setText(singleMeasurement.getDate());
        ((MyViewHolder) holder).pressure1.setText(singleMeasurement.getPressure1());
        ((MyViewHolder) holder).pressure2.setText(singleMeasurement.getPressure2());
        ((MyViewHolder) holder).pulse.setText(singleMeasurement.getPulse());
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }

    void clearAll(Context c) {
        deleteAllAlertDialog(c).show();

    }

    private AlertDialog deleteAllAlertDialog(Context c) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(c);
        alertBuilder.setMessage(R.string.delete_all)
                .setPositiveButton(c.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        measurements.clear();
                        notifyDataSetChanged();
                        sharedPreferences.edit()
                                .clear()
                                .apply();
                    }
                })
                .setNegativeButton(c.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return alertBuilder.create();
    }

    private AlertDialog deleteLineAlertDialog(final View v) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setMessage(R.string.delete_this_line)
                .setPositiveButton(v.getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int positionToDelete = measurementsRecyclerView.getChildAdapterPosition(v);
                        measurements.remove(positionToDelete);
                        notifyItemRemoved(positionToDelete);
                        storeInPreferences();
                    }
                })
                .setNegativeButton(v.getContext().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return alertBuilder.create();
    }
}
