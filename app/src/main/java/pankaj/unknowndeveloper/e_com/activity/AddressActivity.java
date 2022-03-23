package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pankaj.unknowndeveloper.e_com.Adapter.AddressAdapter;
import pankaj.unknowndeveloper.e_com.Model.AddressModel;
import pankaj.unknowndeveloper.e_com.Model.ShowAllModel;
import pankaj.unknowndeveloper.e_com.Model.NewProductModel;
import pankaj.unknowndeveloper.e_com.Model.PopulerProductModel;
import pankaj.unknowndeveloper.e_com.R;

import static android.content.ContentValues.TAG;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {


    Button addaddress,payment;
   Toolbar toolbar;
    RecyclerView addressrecycler;
    AddressAdapter addressAdapter;
    List<AddressModel> addressModelList;
      FirebaseFirestore firestore;
         FirebaseAuth auth;

         String maddress=" ";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address);

          toolbar = findViewById(R.id.address_toolbar);
          setSupportActionBar(toolbar);
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  finish();
              }
          });



          firestore=FirebaseFirestore.getInstance();
          auth=FirebaseAuth.getInstance();

          addressrecycler=findViewById(R.id.address_recycler);


       //Getting Data from Detailed ACtivity

             Object object=getIntent().getSerializableExtra("item");


        payment=(Button)findViewById(R.id.payment_btn);
        addaddress=(Button)findViewById(R.id.add_address_btn);
        System.out.println("addressclicked1");
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });
          payment.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  double amount=0.0;
                  if(object instanceof NewProductModel)
                  {
                      NewProductModel newProductModel=(NewProductModel)object;
                      amount=newProductModel.getPrice();
                  }
                  if(object instanceof PopulerProductModel)
                  {
                      PopulerProductModel newProductModel=(PopulerProductModel)object;
                      amount=newProductModel.getPrice();
                  }
                  if(object instanceof ShowAllModel)
                  {
                      ShowAllModel newProductModel=(ShowAllModel)object;
                      amount=newProductModel.getPrice();
                  }
                  Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                  intent.putExtra("amount",amount);
                  startActivity(intent);

                  //startActivity(new Intent(AddressActivity.this,PaymentActivity.class));

              }
          });


        addressrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList=new ArrayList<>();
        addressAdapter= new AddressAdapter(getApplicationContext(),addressModelList,this);
        addressrecycler.setAdapter(addressAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //  Log.d(TAG, document.getId() + " => " + document.getData());
                        AddressModel addressModel=document.toObject(AddressModel.class);
                        //System.out.println(catagoryModel.img_url);
                        addressModelList.add(addressModel);
                        addressAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());


                 System.out.println("Not Getting ");
                }
            }
        });



    }

    @Override
    public void setaddress(String address) {
   maddress=address;
    }
}