package pankaj.unknowndeveloper.e_com.activity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
   MenuItem log;
    TextView mail;
  Fragment hf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String Mail= getIntent().getStringExtra("email");





        mail=(TextView)findViewById(R.id.email_user_signedIn);

//        if(Mail.length()>0)
//        mail.setText(Mail);
         setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_mycart, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_logout) {
                    // DO your stuff
                  Intent intent =new Intent(MainActivity.this,login.class);
                  startActivity(intent);
                }
                if (id == R.id.nav_mycart) {

                    Intent intent =new Intent(MainActivity.this,MyCartActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.nav_logout: {
//                System.out.println("logout Clicked");
//                break;
//            }
//            case R.id.nav_mycart: {
//                System.out.println("Mycart Clicked");
//                break;
//            }
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//
//        int id=item.getItemId();
//        if(id==R.id.menu_logout)
//        {
//            System.out.println("logout Clicked");
//          return true;
//        }
//        //close navigation drawer
//        binding.drawerLayout.closeDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//        int id = item.getItemId();
//        System.out.println("logout Clicked");
//        Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//        if (id == R.id.nav_logout) {
//            // DO your stuff
//            System.out.println("logout Clicked 1");
//            Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }
}