package com.example.raji.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toolbar;

import com.example.raji.journal.Data.JournalContract.JournalContractSchema;
import com.example.raji.journal.Data.JournalDb;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private JournalDb mDbHelper;
    ArrayList<String> mText,mTitles;

    @Override
    protected void onStart() {
        super.onStart( );
        mText = new ArrayList<>(  );
        mTitles = new ArrayList<>(  );
        initRecycler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new JournalDb(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivity(i);
            }
        });
    }

    public void initRecycler(){
        grabFromDb();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        JournalRecyclerView journalRecyclerView= new JournalRecyclerView(getApplicationContext(),mText,mTitles);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager( this );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(journalRecyclerView);
        recyclerView.setOnFlingListener( new RecyclerView.OnFlingListener( ) {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                Log.i( TAG, "onFling: "+velocityX + " "+velocityY );
                DeleteItem();
                return true;
            }
        } );


    }

    private void DeleteItem() {

    }


    private void grabFromDb() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                JournalContractSchema.TITLES,
                JournalContractSchema.NOTE,
                };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                JournalContractSchema.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.

            // Figure out the index of each column
            int titleColumnIndex = cursor.getColumnIndex(JournalContractSchema.TITLES);
            int noteColumnIndex = cursor.getColumnIndex(JournalContractSchema.NOTE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentTitle = cursor.getString(titleColumnIndex);
                String currentNote = cursor.getString(noteColumnIndex);


                // Display the values from each column of the current row in the cursor in the TextView
                mTitles.add( currentTitle );
                mText.add( currentNote );
                for (String i : mTitles) {
                    Log.i( TAG, "grabFromDb: "+i );
                }
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_item) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    }
