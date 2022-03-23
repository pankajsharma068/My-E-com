package pankaj.unknowndeveloper.e_com.ui.home;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import pankaj.unknowndeveloper.e_com.R;



public class InfoViewHolder
        extends RecyclerView.ViewHolder {

        TextView examName;
        TextView examMessage;
        TextView examDate;
        View view;

        InfoViewHolder(View itemView)
        {
            super(itemView);
            examName
                    = (TextView)itemView
                    .findViewById(R.id.examName);
            examDate
                    = (TextView)itemView
                    .findViewById(R.id.examDate);
            examMessage
                    = (TextView)itemView
                    .findViewById(R.id.examMessage);
            view  = itemView;
        }
    }

