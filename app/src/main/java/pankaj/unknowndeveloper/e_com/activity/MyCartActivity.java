package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

import pankaj.unknowndeveloper.e_com.Adapter.MyCartAdapter;
import pankaj.unknowndeveloper.e_com.Model.MyCartModel;
import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.ui.home.CatagoryModel;

import static android.content.ContentValues.TAG;

public class MyCartActivity extends AppCompatActivity {

    int Totalamount=0;
    Toolbar toolbar;
    TextView overallamount;
    RecyclerView mycartrecycler;
    List<MyCartModel> myCartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        toolbar = findViewById(R.id.mycart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //get data from my cart adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalamount"));
        auth=FirebaseAuth.getInstance();

        overallamount=(TextView)findViewById(R.id.textView);
        firestore=FirebaseFirestore.getInstance();
        mycartrecycler= findViewById(R.id.cart_rec);
        mycartrecycler.setLayoutManager(new LinearLayoutManager(this));
        myCartModelList=new ArrayList<>();
        myCartAdapter=new MyCartAdapter(this,myCartModelList);
        mycartrecycler.setAdapter(myCartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //  Log.d(TAG, document.getId() + " => " + document.getData());
                        MyCartModel myCartModel=document.toObject(MyCartModel.class);
                        //System.out.println(catagoryModel.img_url);
                        myCartModelList.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });


    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalbil=intent.getIntExtra("totalamount",0);
            overallamount.setText("Total amount: "+(totalbil)  +"$");
        }
    };


}