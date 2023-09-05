package com.CW.thebookshelf.JavaClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.CW.thebookshelf.Admin.DetailsActivity;
import com.CW.thebookshelf.R;
import com.CW.thebookshelf.User.BuyBookActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<MyUserViewHolder> {

    private Context context;
    private List<AddBook> datalist;

    public UserAdapter(Context context, List<AddBook> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_recycle_view,parent,false);
        return new MyUserViewHolder(view1);
    }



    @Override
    public void onBindViewHolder(@NonNull MyUserViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getBimgurl()).into(holder.recImg);
        holder.rectitel.setText(datalist.get(position).getBname());
        holder.recCatg.setText(datalist.get(position).getBCategory());
        holder.recdiscrip.setText(datalist.get(position).getBdiscription());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = datalist.get(holder.getAdapterPosition()).getBname();
                //Toast.makeText(context, "String.valueOf(name)", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(context, BuyBookActivity.class);
                intent.putExtra("bimgurl",datalist.get(holder.getAdapterPosition()).getBimgurl());
                intent.putExtra("bname",datalist.get(holder.getAdapterPosition()).getBname());
                intent.putExtra("bcategory",datalist.get(holder.getAdapterPosition()).getBCategory());
                intent.putExtra("bdiscription",datalist.get(holder.getAdapterPosition()).getBdiscription());
                intent.putExtra("bcount",datalist.get(holder.getAdapterPosition()).getBcount());
                intent.putExtra("bauthor",datalist.get(holder.getAdapterPosition()).getBauthor());
                intent.putExtra("bprice",datalist.get(holder.getAdapterPosition()).getBprice());
                intent.putExtra("bisbn",datalist.get(holder.getAdapterPosition()).getBisbn());
                intent.putExtra("key",datalist.get(holder.getAdapterPosition()).getKye());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void searchDataList(ArrayList<AddBook> searchList){
        datalist = searchList;
        notifyDataSetChanged();
    }
}



class MyUserViewHolder extends RecyclerView.ViewHolder{

    ImageView recImg;
    TextView rectitel,recCatg,recdiscrip;
    CardView recCard;
    public MyUserViewHolder(@NonNull View itemView) {
        super(itemView);

        recImg = itemView.findViewById(R.id.recImg);
        recCard = itemView.findViewById(R.id.recCard);
        rectitel = itemView.findViewById(R.id.recTitel);
        recCatg = itemView.findViewById(R.id.recCategory);
        recdiscrip = itemView.findViewById(R.id.recDescription);
    }
}