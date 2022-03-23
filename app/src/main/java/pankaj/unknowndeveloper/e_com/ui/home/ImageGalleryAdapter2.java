package pankaj.unknowndeveloper.e_com.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import pankaj.unknowndeveloper.e_com.R;
 public class ImageGalleryAdapter2
        extends RecyclerView.Adapter<InfoViewHolder> {

    List<RecycleData.examData> list
            = Collections.emptyList();

    Context context;
    ClickListiner listiner;

    public ImageGalleryAdapter2(List<RecycleData.examData> list,
                                Context context, ClickListiner listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public InfoViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.product_info,
                        parent, false);

        InfoViewHolder viewHolder
                = new InfoViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final InfoViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
//        viewHolder.examName
//                .setText(list.get(position).name);
        viewHolder.examDate
                .setText(list.get(position).date);
        viewHolder.examMessage
                .setText(list.get(position).message);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listiner.click(index);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
