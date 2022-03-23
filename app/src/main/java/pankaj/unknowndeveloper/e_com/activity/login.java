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

public class login extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.Log_email);
        password=(EditText)findViewById(R.id.Log_pass);


    }



    public void signup (View v)
    {
        startActivity(new Intent(login.this,Register.class));


    }
    public void signin (View v)
    {
       // startActivity(new Intent(login.this,MainActivity.class));
        String UserName,Email,Pass;
     //   UserName=name.getText().toString();
        Email=email.getText().toString();
        Pass=password.getText().toString();
        System.out.println(Email.length() +  Pass.length());

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
     firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
             if(task.isSuccessful())
             {

                 Toast.makeText(login.this,"login Successfull",Toast.LENGTH_LONG).show();

                 Intent intent =new Intent(login.this,MainActivity.class);

                 intent.putExtra("email",Email);
                 startActivity(intent);
             //    startActivity(new Intent(login.this,MainActivity.class));

             }else
             {
                 System.out.println(task.getException());

                 Toast.makeText(login.this,"Login Failed"+task.getException(),Toast.LENGTH_LONG).show();

             }
         }
     });
    }
}