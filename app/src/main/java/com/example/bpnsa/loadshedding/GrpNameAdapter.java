package com.example.bpnsa.loadshedding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bipin on 5/30/16.
 */
public class GrpNameAdapter extends RecyclerView.Adapter<GrpNameAdapter.MyViewHolder> {
    Context context;
    LayoutInflater inflater;
    GrpNameAdapter(Context context)
    {
        this.context=context;
       inflater= LayoutInflater.from(context);
    }



    @Override
    public GrpNameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.grp_slection,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GrpNameAdapter.MyViewHolder holder, int position) {
        holder.bt.setText(names[position]);

    }


    @Override
    public int getItemCount() {
        return names.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button bt;

        public MyViewHolder(View itemView) {
            super(itemView);
            bt= (Button) itemView.findViewById(R.id.groupName);
            bt.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            SharedPreferences preferences=context.getSharedPreferences("GroupNumber",context.MODE_PRIVATE);
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("position",position+"");
            editor.apply();

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }
    }



    String[] names={"Group No. 1","Group No. 2","Group No. 3","Group No. 4","Group No. 5","Group No. 6","Group No. 7"};


}
