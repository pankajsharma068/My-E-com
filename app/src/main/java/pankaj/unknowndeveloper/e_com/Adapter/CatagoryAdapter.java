package pankaj.unknowndeveloper.e_com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.activity.ShowAllActivity;
import pankaj.unknowndeveloper.e_com.ui.home.CatagoryModel;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {


    private Context context;
    private List<CatagoryModel> list;

    public CatagoryAdapter(Context context, List<CatagoryModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
//        Context context
//                = viewGroup.getContext();
//        LayoutInflater inflater
//                = LayoutInflater.from(context);
//
//        // Inflate the layout
//
//        View photoView
//                = inflater
//                .inflate(R.layout.product_info,
//                        viewGroup, false);
//
//        ViewHolder viewHolder
//                = new ViewHolder(photoView);
//        return viewHolder;

        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catagory_list,viewGroup,false));
    }

    @Override

    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {

        Glide.with(context).load(list.get(i).getImg_url()).into(viewHolder.Catimg);
        viewHolder.CatName.setText(list.get(i).getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ShowAllActivity.class);
                intent.putExtra("type",list.get(i).getType());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         ImageView Catimg;
         TextView CatName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            Catimg=itemView.findViewById(R.id.cat_img);
            CatName=itemView.findViewById(R.id.cat_name);
        }
    }
}
