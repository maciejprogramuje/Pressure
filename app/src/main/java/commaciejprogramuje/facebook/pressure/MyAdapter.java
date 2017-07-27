package commaciejprogramuje.facebook.pressure;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.szymczyk on 2017-07-26.
 */

class MyAdapter extends RecyclerView.Adapter {
    private List<SingleMeasurement> measurements = new ArrayList<>();
    private RecyclerView measurementHistoryRecyclerView;

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

    MyAdapter(List<SingleMeasurement> measurements, RecyclerView measurementHistoryRecyclerView) {
        this.measurements = measurements;
        this.measurementHistoryRecyclerView = measurementHistoryRecyclerView;
    }

    private AlertDialog setAlertDialog(final View v) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setMessage(R.string.delete_this_line)
                .setPositiveButton(v.getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int positionToDelete = measurementHistoryRecyclerView.getChildAdapterPosition(v);
                        measurements.remove(positionToDelete);
                        notifyItemRemoved(positionToDelete);
                    }
                })
                .setNegativeButton(v.getContext().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return alertBuilder.create();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_single_measurement, parent, false);

        //usuwanie po kliknięciu
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(v).show();
            }
        });

        return new MyViewHolder(view);
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

    public void remove(int position) {
        measurements.remove(position);
        notifyItemRemoved(position);
    }
}
