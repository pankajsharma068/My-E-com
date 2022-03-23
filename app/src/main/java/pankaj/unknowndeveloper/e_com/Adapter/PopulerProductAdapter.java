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

import pankaj.unknowndeveloper.e_com.Model.PopulerProductModel;
import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.activity.DetailedActivity;


public class PopulerProductAdapter extends RecyclerView.Adapter<PopulerProductAdapter.ViewHolder> {

   private Context context;

    private  List<PopulerProductModel> list;

    public PopulerProductAdapter(Context context, List<PopulerProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.populer_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getImg_url()).into(viewHolder.AllImg);
        viewHolder.AllName.setText(list.get(i).getName());
        viewHolder.AllPrice.setText(String.valueOf(list.get(i).getPrice()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new  Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(i));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView AllImg;
        TextView AllName,AllPrice;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            AllImg=itemView.findViewById(R.id.all_img);
            AllName=itemView.findViewById(R.id.all_product_name);
            AllPrice=itemView.findViewById(R.id.all_price);


        }



    }
}
