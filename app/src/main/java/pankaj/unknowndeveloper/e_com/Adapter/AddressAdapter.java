package pankaj.unknowndeveloper.e_com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pankaj.unknowndeveloper.e_com.Model.AddressModel;
import pankaj.unknowndeveloper.e_com.R;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {


      private Context context;
      private List<AddressModel> list;
      SelectedAddress selectedAddres;
   private RadioButton SelectedradioButton;

    public AddressAdapter(Context context, List<AddressModel> list, SelectedAddress selectedAddres) {
        this.context = context;
        this.list = list;
        this.selectedAddres = selectedAddres;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.address_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {

         viewHolder.address.setText(list.get(i).getUserAddress());
        viewHolder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(AddressModel address :list)
                {
                    address.setSelected(false);
                }
                list.get(i).setSelected(true);
                if(SelectedradioButton!=null)
                {
                    SelectedradioButton.setChecked(false);
                }
                SelectedradioButton=(RadioButton) view;
                SelectedradioButton.setChecked(true);
                selectedAddres.setaddress(list.get(i).getUserAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     TextView address;
     RadioButton radio;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            address=itemView.findViewById(R.id.address_add);
            radio=itemView.findViewById(R.id.select_address);
        }
    }
    public  interface  SelectedAddress
    {
         void setaddress(String address);

    }
}
