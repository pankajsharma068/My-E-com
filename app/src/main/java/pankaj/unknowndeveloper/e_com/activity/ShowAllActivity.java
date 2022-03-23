package pankaj.unknowndeveloper.e_com.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pankaj.unknowndeveloper.e_com.Adapter.ShowAllAdapter;
import pankaj.unknowndeveloper.e_com.Model.ShowAllModel;
import pankaj.unknowndeveloper.e_com.R;

public class ShowAllActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView showallrecyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);


       toolbar=(Toolbar)findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String type=getIntent().getStringExtra("type");


        firestore=FirebaseFirestore.getInstance();
        showallrecyclerView=(RecyclerView) findViewById(R.id.show_all_rec);
        showallrecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList=new ArrayList<>();
        showAllAdapter=new ShowAllAdapter(this,showAllModelList);
        showallrecyclerView.setAdapter(showAllAdapter);


      System.out.println(type);


//        firestore.collection("ShowAll")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//
//                        if(task.isSuccessful())
//                        {
//                            for(DocumentSnapshot doc : task.getResult().getDocuments())
//                            {
//                                ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
//                                showAllModelList.add(showAllModel);
//                                showAllAdapter.notifyDataSetChanged();
//
//                            }
//                        }
//                    }
//                });

        if(type==null || type.isEmpty())
        {
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {
                                for(DocumentSnapshot doc : task.getResult().getDocuments())
                                {
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }

      else  if(type!=null && type.equalsIgnoreCase("men"))
        {
            firestore.collection("ShowAll").whereEqualTo("type","men")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {
                                for(DocumentSnapshot doc : task.getResult().getDocuments())
                                {
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }

        else if(type!=null && type.equalsIgnoreCase("watch"))
        {
            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {
                                for(DocumentSnapshot doc : task.getResult().getDocuments())
                                {
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
        if(type!=null && type.equalsIgnoreCase("camera"))
        {
            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {
                                for(DocumentSnapshot doc : task.getResult().getDocuments())
                                {
                                    ShowAllModel showAllModel=doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}