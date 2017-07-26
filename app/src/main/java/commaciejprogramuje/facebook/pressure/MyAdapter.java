package commaciejprogramuje.facebook.pressure;

import android.support.v7.widget.RecyclerView;
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


    private List<SingleMeasurement> measurements = new ArrayList<>();
    private RecyclerView measurementHistoryRecyclerView;

    MyAdapter(List<SingleMeasurement> measurements, RecyclerView measurementHistoryRecyclerView) {
        this.measurements = measurements;
        this.measurementHistoryRecyclerView = measurementHistoryRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_single_measurement, parent, false);

        //usuwanie po kliknięciu - do zmiany - może z potwierdzeniem?
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionToDelete = measurementHistoryRecyclerView.getChildAdapterPosition(v);
                measurements.remove(positionToDelete);
                notifyItemRemoved(positionToDelete);
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
}
