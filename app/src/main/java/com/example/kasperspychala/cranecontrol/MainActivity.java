package com.example.kasperspychala.cranecontrol;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    EditText editText;
    String micResult = new String();
    String[] commands = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent recIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        fab = (FloatingActionButton) findViewById(R.id.mic);
        editText = (EditText) findViewById(R.id.editText);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                recIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(recIntent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Oops! Your device doesn't support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    /* When Mic activity close */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                        micResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                        editText.setText(micResult);
                }
                break;
            }
        }
    }
}
