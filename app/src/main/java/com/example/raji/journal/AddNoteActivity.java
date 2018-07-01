package com.example.raji.journal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raji.journal.Data.JournalDb;

import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {
    EditText mTitles, mNote;
    private static final int CAMERA_REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mNote = (EditText) findViewById(R.id.mNote);
        mTitles = (EditText) findViewById(R.id.mTitle);
//        String mBundleTitle = getIntent().getExtras().getString( "Title" );
//        String mBundleText = getIntent().getExtras().getString( "Text" );
//        if (!mBundleText.isEmpty() || !mBundleTitle.isEmpty()){
//            mNote.setText( mBundleText );
//            mTitles.setText( mBundleTitle );
//        }
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab_cam );
        fab.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent();
//                intent.putExtra(Intent.ACTION_CAMERA_BUTTON,)
                //startActivityForResult( intent,CAMERA_REQUEST_CODE );
            }
        } );
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult( requestCode, resultCode, data );
////        if (requestCode == CAMERA_REQUEST_CODE){
////
////        }
//    }

    public void grabAndSave(){
        String titles = mTitles.getText().toString().trim();
            String note = mNote.getText().toString().trim();

            if (!titles.isEmpty() && !note.isEmpty()){
                JournalDb db = new JournalDb(this);
               boolean result =  db.insert(titles,note);
               if (result){
                   Toast.makeText( this, "Inserted", Toast.LENGTH_SHORT ).show( );

                   finish();
               }
            }else {
                Toast.makeText( this, " at least one of the fields is left blank", Toast.LENGTH_SHORT ).show( );
            }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_addNote:
                // Save pet to database
                grabAndSave();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
