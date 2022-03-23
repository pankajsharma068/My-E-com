package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import pankaj.unknowndeveloper.e_com.Model.ShowAllModel;
import pankaj.unknowndeveloper.e_com.Model.NewProductModel;
import pankaj.unknowndeveloper.e_com.Model.PopulerProductModel;
import pankaj.unknowndeveloper.e_com.R;

public class DetailedActivity extends AppCompatActivity {
ImageView detailed_img,addproduc,removeproduc;
TextView  name,discription,rating,price,quantity;
Button add,buy;
Toolbar toolbar;
int TotalQUantity=1;
int TotalPrice;
private FirebaseFirestore   firestore;
    //New Product
    NewProductModel newProductModel=null;

    //Populer Product
    PopulerProductModel populerProductModel=null;

    //ShowAll Activity
    ShowAllModel showAllModel=null;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        //toolbar
        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        final  Object obj=getIntent().getSerializableExtra("detailed");


        if(obj instanceof NewProductModel)
        {
            newProductModel=(NewProductModel) obj;
        }

        else if(obj instanceof  PopulerProductModel)
        {
            populerProductModel=(PopulerProductModel) obj;
        }
        else if(obj instanceof ShowAllModel)
        {
            showAllModel=(ShowAllModel) obj;
        }


        detailed_img=(ImageView) findViewById(R.id.detailed_img);
        addproduc=(ImageView) findViewById(R.id.addprod);
        removeproduc=(ImageView)findViewById(R.id.removeprod);
        name=(TextView)  findViewById(R.id.detailed_name);
        discription=(TextView)  findViewById(R.id.details);
        rating=(TextView)  findViewById(R.id.rating);
        price=(TextView)  findViewById(R.id.detailed_price);
         quantity=(TextView) findViewById(R.id.quantity);
        add=(Button)findViewById(R.id.add_to_cart);
        buy=(Button)findViewById(R.id.buy_now);
         if(newProductModel !=null)
         {


             Glide.with(getApplicationContext()).load(newProductModel.getImg_url()).into(detailed_img);
           name.setText(newProductModel.getName());
           discription.setText(newProductModel.getDiscription());
              rating.setText(newProductModel.getRating());
            price.setText(String.valueOf(newProductModel.getPrice()));
         }

         if(populerProductModel != null)
         {
             Glide.with(getApplicationContext()).load(populerProductModel.getImg_url()).into(detailed_img);
             name.setText(populerProductModel.getName());
             discription.setText(populerProductModel.getDiscription());
             rating.setText(populerProductModel.getRating());
             price.setText(String.valueOf(populerProductModel.getPrice()));
         }
        if(showAllModel != null)
        {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailed_img);
            name.setText(showAllModel.getName());
            discription.setText(showAllModel.getDiscription());
            rating.setText(showAllModel.getRating());
            price.setText(String.valueOf(showAllModel.getPrice()));
        }

        addproduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TotalQUantity<10)
                {
                    TotalQUantity++;
                    quantity.setText(String.valueOf(TotalQUantity));

                    if(newProductModel !=null)
                    {
                        TotalPrice=newProductModel.getPrice()*TotalQUantity;
                    }if(showAllModel != null)
                      {
                    TotalPrice=showAllModel.getPrice()*TotalQUantity;
                      }
                    if(populerProductModel != null)
                    {
                        TotalPrice=populerProductModel.getPrice()*TotalQUantity;
                    }
                }
                else
                {
                    Toast.makeText(DetailedActivity.this,"Maximum Limit Reached",Toast.LENGTH_LONG).show();
                }

            }
        });

        removeproduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TotalQUantity>1)
                {
                    TotalQUantity--;
                    quantity.setText(String.valueOf(TotalQUantity));
                }
                else
                {
                    Toast.makeText(DetailedActivity.this,"Minimum Limit Reached",Toast.LENGTH_LONG).show();
                }
            }
        });


        //Buy Now
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent intent=new Intent(DetailedActivity.this,AddressActivity.class);

                 if(newProductModel !=null)
                 {
                     intent.putExtra("item",newProductModel);
                 }
                if(populerProductModel !=null)
                {
                    intent.putExtra("item",populerProductModel);
                }
                if(showAllModel !=null)
                {
                    intent.putExtra("item",showAllModel);
                }
                startActivity(intent);
                       }
        });



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
//Add To Cart
    public void addtocart(View view) {

        String savecurruntTime,savecurDate;
        Calendar calFordata=Calendar.getInstance();
        SimpleDateFormat currDate= new SimpleDateFormat("MM dd, yyyy");
        savecurDate=currDate.format(calFordata.getTime());

        SimpleDateFormat currTime= new SimpleDateFormat("HH:mm:ss a");
        savecurruntTime=currTime.format(calFordata.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();

        System.out.println(savecurDate +" "+ savecurruntTime);
        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("Time",savecurruntTime);
        cartMap.put("Date",savecurDate);
        cartMap.put("TotalQuantity",TotalQUantity);
        cartMap.put("TotalPrice",TotalPrice);


        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Added to a cart",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}