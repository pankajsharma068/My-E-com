package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import pankaj.unknowndeveloper.e_com.R;

public class Register extends AppCompatActivity {

    EditText name,email,password;
     private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        name=(EditText)findViewById(R.id.Rname);
        email=(EditText)findViewById(R.id.Remail);
        password=(EditText)findViewById(R.id.Rpassword);



    }

    public void signup(View v)
    {
        String UserName,Email,Pass;
        UserName=name.getText().toString();
        Email=email.getText().toString();
        Pass=password.getText().toString();
        System.out.println(Email.length() +  Pass.length());
        if(TextUtils.isEmpty(UserName))
        {
            Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Email))
        {
            Toast.makeText(this,"Enter Email",Toast.LENGTH_LONG).show();
          return;
        }
        if(TextUtils.isEmpty(Pass))
        {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_LONG).show();
          return;
        }
        if(Pass.length()<6)
        {
            Toast.makeText(this,"Password length Should be more than 6! ",Toast.LENGTH_LONG).show();
             return;
        }
//        startActivity(new Intent(Register.this,MainActivity.class));

     firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

            if(task.isSuccessful())
            {
                System.out.println("Succesfull");
                Toast.makeText(Register.this,"RegisTration Successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Register.this,Onbording.class));

            }else
            {
                System.out.println("Failed");

                Toast.makeText(Register.this,"RegisTration Failed"+task.getException(),Toast.LENGTH_LONG).show();

            }
        }
    });

    }
    public void signin (View v)
    {
        startActivity(new Intent(Register.this,login.class));


    }


}