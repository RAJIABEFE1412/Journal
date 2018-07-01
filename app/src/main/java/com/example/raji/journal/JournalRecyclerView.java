package com.example.raji.journal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JournalRecyclerView extends RecyclerView.Adapter<JournalRecyclerView.viewHolder> {
    private static final String TAG = "JournalRecyclerView";
    private Context context;
    private ArrayList<String> mTitles = new ArrayList<>(  );
    private ArrayList<String> mTexts = new ArrayList<>();



    public JournalRecyclerView(Context c,ArrayList<String>texts,ArrayList<String> titles){
       mTexts = texts;
       mTitles = titles;
       context = c;

    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_main,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.mTextView.setText(mTexts.get(position));
        holder.mTitleView.setText(mTitles.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "item at"+position, Toast.LENGTH_SHORT ).show( );
                Intent i = new Intent( context,AddNoteActivity.class );
//                i.putExtra( "Title",mTitles );
//                i.putExtra( "Text",mTexts );
                context.startActivity( i );

            }
        });
//        holder.mCardView.setOnLongClickListener( new View.OnLongClickListener( ) {
//            @Override
//            public boolean onLongClick(View v) {
//                holder.mCardView.setCardBackgroundColor( context.getResources().getColor(R.color.light_red ));
//                AlertDialog.Builder builder = new AlertDialog.Builder( context );
//                builder.setIcon( R.drawable.icon )
//                .setCancelable( true )
//                .setMessage( "Would you like to this Note?" )
//                .setTitle( "Delete Note?" )
//                .create()
//                //.setButton( AlertDialog.BUTTON_NEGATIVE,"Cancel" );
//                .show();
//
//
//                return true;
//            }
//        } );
    }

    @Override
    public int getItemCount() {
        return mTexts.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTitleView;
        CardView mCardView;
        public viewHolder(View itemView) {
            super(itemView);
            this.mTextView = itemView.findViewById(R.id.mtext);
            this.mCardView = itemView.findViewById(R.id.cardView);
            this.mTitleView = itemView.findViewById(R.id.mTitle);
        }
    }
}
