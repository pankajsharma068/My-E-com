package pankaj.unknowndeveloper.e_com.Adapter;

import android.content.Context;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import pankaj.unknowndeveloper.e_com.R;

public class SlidingAdapter extends PagerAdapter {


     Context context;
    LayoutInflater layoutInflater;
     public SlidingAdapter(Context context)
     {
         this.context=context;
     }

       int ImageArray[]=
    {
            R.drawable.onboardscreen1,
            R.drawable.onboardscreen2,
            R.drawable.onboardscreen3


    };

    int headingArray[]=
            {
                    R.string.first_slide,
                    R.string.second_slide,
                    R.string.third_slide


            };
    int DiscriptionArray[]=
            {
                    R.string.description,
                    R.string.description,
                    R.string.description


            };
    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view==(ConstraintLayout) object;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliding_layout,container,false);
        ImageView img=view.findViewById(R.id.slider_img);
        TextView heading=view.findViewById(R.id.heading);
        TextView description=view.findViewById(R.id.description);
        img.setImageResource(ImageArray[position]);
        heading.setText(headingArray[position]);
        description.setText(DiscriptionArray[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

      container.removeView((ConstraintLayout)object);


    }

}
