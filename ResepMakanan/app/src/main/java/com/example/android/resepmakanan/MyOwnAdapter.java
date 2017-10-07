package com.example.android.resepmakanan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by RIZKY on 25/09/2017.
 */

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {

    String data1[],data2[];
    Context ctx;

    public MyOwnAdapter(Context ct,String s1[],String s2[]){
        ctx = ct;
        data1 = s1;
        data2 = s2;
    }

    @Override
    public MyOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ctx);
        View myOwnView= myInflator.inflate(R.layout.my_recipe,parent,false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyOwnHolder holder, final int position) {
        holder.t1.setText(data1[position]);
        holder.t2.setText(data2[position]);
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent it;
                if(position==0){
                    it = new Intent(ctx, OporActivity.class);
                }
                else if(position==1){
                    it = new Intent(ctx, SelatActivity.class);
                }
                else if(position==2){
                    it = new Intent(ctx,SayurActivity.class);
                }
                else if(position==3){
                    it = new Intent(ctx,PindangActivity.class);
                }
                else {
                    it = new Intent(ctx,GarangActivity.class);
                }
                ctx.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder{
        TextView t1,t2;

        public MyOwnHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.text1);
            t2 = (TextView) itemView.findViewById(R.id.text2);
        }
    }
}
