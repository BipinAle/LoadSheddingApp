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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
        if (day.equals(Utility.getDayFromNumber(today))) {

            holder.dayTv.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            holder.dayTv.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            calculateTime(position, holder);

        }

    }

    private void calculateTime(int position, MyViewHolder holder) {
        RoutineItem item = data.get(position);


        String tFive = data.get(position + 1).getTime1().substring(0, 5);

        String range1 = item.getTime1();//09:00-12:30
        String time1 = range1.substring(0, 5);
        String time2 = range1.substring(6, 11);


        String range2 = item.getTime2();//09:00-12:30
        String time3 = range2.substring(0, 5);//(0,5]..
        String time4 = range2.substring(6, 11);


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String str = sdf.format(new Date());
        int ct = Utility.convertToMinute(str);
        int t1 = Utility.convertToMinute(time1);
        int t2 = Utility.convertToMinute(time2);
        int t3 = Utility.convertToMinute(time3);
        int t4 = Utility.convertToMinute(time4);
        int t5 = Utility.convertToMinute(tFive);


        getStatus(ct, t1, t2, t3, t4, t5, holder);


    }

    private void getStatus(int ct, int t1, int t2, int t3, int t4, int t5, MyViewHolder holder) {

        int range;

        if (t1 > ct) {

            range = t1 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);


        } else if (t1 < ct && t2 > ct) {
            range = t2 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);

        } else if (t2 < ct && t3 > ct) {
            range = t3 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);

        } else if (t3 < ct && t4 > ct) {
            range = t4 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);

        } else if (ct > t4) {


            holder.bulbV.setImageResource(R.mipmap.bon);
            if (ct > t5) {
                range = 24 * 60 - ct + t5;
            } else {
                range = t5 - ct;
            }

        } else {
            range = 0;
        }

        int hour = range / 60;
        int minute = range % 60;
        holder.remTime.setText(hour + "Hr" + " " + minute + "Min");


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
