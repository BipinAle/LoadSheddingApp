package com.example.bpnsa.loadshedding;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by bpnsa on 5/20/16.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<RoutineItem> data;

    public GroupAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();

    }

    public void setData(ArrayList<RoutineItem> data) {

        this.data = data;
        notifyItemRangeChanged(0, data.size());
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.timerow, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        RoutineItem item = data.get(position);
        String day = item.getDay();
        holder.dayTv.setText(day);
        holder.timeTv.setText(item.getTime1() + "\n" + item.getTime2());


        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        if (day.equals(getDayFromNumber(today))) {

            holder.dayTv.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            calculateTime(position, holder);

        }

    }

    private void calculateTime(int position, MyViewHolder holder) {
        RoutineItem item = data.get(position);


        String range1 = item.getTime1();//09:00-12:30
        String time1 = range1.substring(0, 4);
        String time2 = range1.substring(6, 10);

        String range2 = item.getTime2();//09:00-12:30
        String time3 = range2.substring(0, 4);
        String time4 = range2.substring(6, 10);


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        int t1 = convertToMinute(time1);
        Log.e("t1", t1 + "");
        int t2 = convertToMinute(time2);
        Log.e("t2", t2 + "");
        int t3 = convertToMinute(time3);
        Log.e("t3", t3 + "");
        int t4 = convertToMinute(time4);
        Log.e("t4", t4 + "");
        int ct = hour * 60 + minute;
        Log.e("current time", ct + "");


        getStatus(ct, t1, t2, t3, t4, holder);


    }

    private void getStatus(int ct, int t1, int t2, int t3, int t4, MyViewHolder holder) {

        int range;

        if (500 > ct) {

            range = 500 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);
            holder.remTime.setText(range + R.string.time_goes_in);


        } else if (t1 < ct && t2 >= ct) {
            range = t2 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);
            holder.remTime.setText(range +R.string.time_comes_in);

        } else if (t2 <= ct && t3 >= ct) {
            range = t3 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);
            holder.remTime.setText(range+R.string.time_goes_in);

        } else if (t3 <= ct && t4 >= ct) {
            range = t4 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);
            holder.remTime.setText(range+R.string.time_comes_in);


        } else {

            range = 100;
            holder.bulbV.setImageResource(R.mipmap.bon);
            holder.remTime.setText(range + R.string.time_goes_in);


        }

        holder.remTime.setText(range + R.string.time_comes_in);
    }


    private int convertToMinute(String time) {

        String hr = time.substring(0, 1);
        Log.e("hour", hr);

        String mn = time.substring(3, 4);
//        return Integer.parseInt(hr) * 60 + Integer.parseInt(mn);
        return 0;

    }

    private String getDayFromNumber(int i) {
        switch (i) {
            case 1:
                return "Sunday";

            case 2:
                return "Monday";

            case 3:
                return "Tuesday";

            case 4:
                return "Wednesday";

            case 5:
                return "Thursday";

            case 6:
                return "Friday";

            case 7:
                return "Saturday";

            default:
                return "";

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dayTv, timeTv, remTime;
        ImageView bulbV;

        public MyViewHolder(View itemView) {
            super(itemView);

            dayTv = (TextView) itemView.findViewById(R.id.day);
            timeTv = (TextView) itemView.findViewById(R.id.time);
            remTime = (TextView) itemView.findViewById(R.id.remainingTime);
            bulbV = (ImageView) itemView.findViewById(R.id.bulb);


        }
    }
}
