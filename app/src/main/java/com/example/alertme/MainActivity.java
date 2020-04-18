package com.example.alertme;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import de.mrapp.android.dialog.MaterialDialog;

public class MainActivity extends AppCompatActivity implements MessageListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MessageReceiver.bindListener(this);

      //  startSound("a1.mp3");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.setView(R.layout.custom_dialog_content);
        MaterialDialog dialog = builder.create();
        dialog.show();

        //builder.setCustomTitle(R.layout.custom_dialog_title);
        //builder.setCustomMessage(R.layout.custom_dialog_message);
        //builder.setCustomButtonBar(R.layout.custom_dialog_button_bar);
        //builder.setCustomHeader(R.layout.custom_dialog_header);

    }

    @Override
    public void messageReceived(String message) {
       // Toast.makeText(this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
