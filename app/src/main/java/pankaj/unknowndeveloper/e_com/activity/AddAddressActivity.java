package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import pankaj.unknowndeveloper.e_com.R;

public class AddAddressActivity extends AppCompatActivity {

    EditText name, address, city, pos_code, phonenumber;
    Button addaddressbtn;
    Toolbar toolbar;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar=findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.ad_name);

        address = (EditText) findViewById(R.id.ad_address);
        city = (EditText) findViewById(R.id.ad_city);
        pos_code = (EditText) findViewById(R.id.ad_code);
        phonenumber = (EditText) findViewById(R.id.ad_phone);
        addaddressbtn = (Button) findViewById(R.id.add_address_btn);
    }




    public void addUserAddress (View view)
        {
            String UserName = name.getText().toString();
            String UserAddress = address.getText().toString();
            String UserCity = city.getText().toString();
            String UserPostelCode = pos_code.getText().toString();
            String UserPhone = phonenumber.getText().toString();
            String FinalAddresss = " ";

            if (!UserName.isEmpty()) {
                FinalAddresss += UserName;
                FinalAddresss+=" ";
            }
            if (!UserCity.isEmpty()) {
                FinalAddresss += UserCity;
                FinalAddresss+=" ";
            }
            if (!UserAddress.isEmpty()) {
                FinalAddresss += UserAddress;
                FinalAddresss+=" ";
            }
            if (!UserPostelCode.isEmpty()) {
                FinalAddresss += UserPostelCode;
                FinalAddresss+=" ";
            }
            if (!UserPhone.isEmpty()) {
                FinalAddresss += UserPhone;
                FinalAddresss+=" ";
            }

            if (!UserName.isEmpty() && !UserAddress.isEmpty() && !UserCity.isEmpty() && !UserPostelCode.isEmpty() && !UserPhone.isEmpty()) {


                Map<String, String> map = new HashMap<>();
                map.put("userAddress", FinalAddresss);

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(AddAddressActivity.this,DetailedActivity.class));


                        }
                    }
                });

            } else {
                Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_LONG).show();
                System.out.println("Not Added");
            }

        }

//        @Override
//        public boolean onOptionsItemSelected (MenuItem item){
//            // handle arrow click here
//            if (item.getItemId() == android.R.id.home) {
//                finish(); // close this activity and return to preview activity (if there is any)
//            }
//            return super.onOptionsItemSelected(item);
//        }

}
