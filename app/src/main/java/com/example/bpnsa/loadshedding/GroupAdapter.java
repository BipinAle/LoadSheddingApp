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

            holder.dayTv.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackground));
            calculateTime(position, holder);

        }

    }

    private void calculateTime(int position, MyViewHolder holder) {
        RoutineItem item = data.get(position);


        String range1 = item.getTime1();//09:00-12:30
        String time1 = range1.substring(0, 5);
        Log.e("time111", time1);//09:00
        String time2 = range1.substring(6, 11);
        Log.e("time122", time2);//12:30

        String range2 = item.getTime2();//09:00-12:30
        String time3 = range2.substring(0, 5);//(0,5]..
        String time4 = range2.substring(6, 11);


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        int t1 = convertToMinute(time1);
        int t2 = convertToMinute(time2);
        int t3 = convertToMinute(time3);
        int t4 = convertToMinute(time4);
        int ct = hour * 60 + minute;


        getStatus(ct, t1, t2, t3, t4, holder);


    }

    private void  getStatus(int ct, int t1, int t2, int t3, int t4, MyViewHolder holder) {

        int range;

        if (t1 > ct) {

            range = t1 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);


        } else if (t1 < ct && t2 >= ct) {
            range = t2 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);

        } else if (t2 <= ct && t3 >= ct) {
            range = t3 - ct;
            holder.bulbV.setImageResource(R.mipmap.bon);

        } else if (t3 <= ct && t4 >= ct) {
            range = t4 - ct;
            holder.bulbV.setImageResource(R.mipmap.boff);

        } else {

            range = 100;



        }
        int hour=range/60;
        int minute=range%60;
        holder.remTime.setText(hour+"Hr"+" "+minute+"Min"+" "+"Left");



    }


    private int convertToMinute(String time) {//09:30

        String hr = time.substring(0, 2);

       int hour= Integer.parseInt(hr);


        String mn = time.substring(3, 5);
        int minute=Integer.parseInt(mn);
        return hour * 60 +minute;

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
