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

import pankaj.unknowndeveloper.e_com.Model.ShowAllModel;
import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.activity.DetailedActivity;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    private  Context context;
    private List<ShowAllModel> list;

    public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ShowAllAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_all,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShowAllAdapter.ViewHolder viewHolder, int i) {

        Glide.with(context).load(list.get(i).getImg_url()).into(viewHolder.show_img);
        viewHolder.show_name.setText(list.get(i).getName());
        viewHolder.show_price.setText("$"+String.valueOf(list.get(i).getPrice()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, DetailedActivity.class);

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

       private ImageView show_img;
         private  TextView   show_name,show_price;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            show_img=itemView.findViewById(R.id.item_image);
            show_name=itemView.findViewById(R.id.item_nam);
            show_price=itemView.findViewById(R.id.item_cost);
        }
    }
}
