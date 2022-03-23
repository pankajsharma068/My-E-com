package pankaj.unknowndeveloper.e_com.ui.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import pankaj.unknowndeveloper.e_com.Adapter.CatagoryAdapter;
import pankaj.unknowndeveloper.e_com.Adapter.NewProductAdapter;
import pankaj.unknowndeveloper.e_com.Adapter.PopulerProductAdapter;
import pankaj.unknowndeveloper.e_com.Model.PopulerProductModel;
import pankaj.unknowndeveloper.e_com.Adapter.SliderAdapter;
import pankaj.unknowndeveloper.e_com.Model.NewProductModel;
import pankaj.unknowndeveloper.e_com.R;
import pankaj.unknowndeveloper.e_com.activity.ShowAllActivity;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {



    // ProgressBar

    ProgressDialog progressDialog;
   private HomeViewModel homeViewModel;
    FirebaseFirestore db ;
   CatagoryAdapter catagoryAdapter;
   RecyclerView catrecyclerView;
    List<CatagoryModel> catagoryModellist;
     // New Product RecyclerView

   RecyclerView newproductrecycler;
   List<NewProductModel> newProductModellist;
   NewProductAdapter newProductAdapter;

   //Populer Product

    RecyclerView populerproductrecycler;
    List<PopulerProductModel> populerProductModellist;
    PopulerProductAdapter populerProductAdapter;

    //See ALl

    TextView pop_see_all,cat_see_all,new_see_all;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



     progressDialog=new ProgressDialog(getActivity());
      View root=inflater.inflate(R.layout.fragment_home,container,false);

      // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        //See all

        cat_see_all=root.findViewById(R.id.category_see_all);
        new_see_all=root.findViewById(R.id.newProducts_see_all);
        pop_see_all=root.findViewById(R.id.popular_see_all);


        cat_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        new_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        pop_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        //catrecyclerView

        catrecyclerView = root.findViewById(R.id.rec_category);

        // new Product Recycler
        newproductrecycler=root.findViewById(R.id.new_product_rec);

        //populer Product

        populerproductrecycler=root.findViewById(R.id.popular_rec);
          //Firestore
        db = FirebaseFirestore.getInstance();

        // initializing the slider view.
        SliderView sliderView = (SliderView) root.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(R.drawable.banner1));
        sliderDataArrayList.add(new SliderData(R.drawable.banner2));
        sliderDataArrayList.add(new SliderData(R.drawable.banner3));


          progressDialog.setTitle("Welcome to EvryKart");
          progressDialog.setMessage("Please Wait.....");
          progressDialog.setCanceledOnTouchOutside(false);
          progressDialog.show();
        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        //Catagory

          catrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
          catagoryModellist=new ArrayList<>();


          catagoryAdapter=new CatagoryAdapter(getActivity(),catagoryModellist);
          catrecyclerView.setAdapter(catagoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              //  Log.d(TAG, document.getId() + " => " + document.getData());
                            CatagoryModel catagoryModel=document.toObject(CatagoryModel.class);
                            System.out.println(catagoryModel.img_url);
                            catagoryModellist.add(catagoryModel);
                            catagoryAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

     //New Product
        newproductrecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductModellist=new ArrayList<>();


        newProductAdapter=new NewProductAdapter(getActivity(),newProductModellist);
        newproductrecycler.setAdapter(newProductAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //  Log.d(TAG, document.getId() + " => " + document.getData());
                                NewProductModel newProductModel=document.toObject(NewProductModel.class);
                                //System.out.println(newProductModel.img_url);
                                newProductModellist.add(newProductModel);
                                newProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        //Populer Product

        populerproductrecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        populerProductModellist=new ArrayList<>();


        populerProductAdapter=new PopulerProductAdapter(getActivity(),populerProductModellist);

        populerproductrecycler.setAdapter(populerProductAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //  Log.d(TAG, document.getId() + " => " + document.getData());
                                PopulerProductModel populerProductModel=document.toObject(PopulerProductModel.class);
                                //System.out.println(newProductModel.img_url);
                                populerProductModellist.add(populerProductModel);
                                populerProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return root;
    }

    private void setContentView(int activity_main) {

    }


};