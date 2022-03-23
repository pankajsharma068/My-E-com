package pankaj.unknowndeveloper.e_com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pankaj.unknowndeveloper.e_com.Model.MyCartModel;
import pankaj.unknowndeveloper.e_com.R;

public class MyCartAdapter  extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

     private Context context;
     List<MyCartModel>list;
     int totalamount=0;
    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_cart_demo,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
      viewHolder.name.setText(list.get(i).getProductName());
        viewHolder.price.setText("$"+list.get(i).getProductPrice());
        viewHolder.time.setText(list.get(i).getTime());
        viewHolder.date.setText(list.get(i).getDate());
        viewHolder.totalprice.setText("$"+String.valueOf(list.get(i).getTotalPrice()));
        viewHolder.totalquantity.setText(String.valueOf(list.get(i).getTotalQuantity()));

         totalamount+=list.get(i).getTotalPrice();
        Intent intent=new Intent("MyTotalamount");
        intent.putExtra("totalamount",totalamount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,date,time,totalquantity,totalprice;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            totalquantity=itemView.findViewById(R.id.total_quantity);
            totalprice=itemView.findViewById(R.id.total_price);
        }
    }
}
