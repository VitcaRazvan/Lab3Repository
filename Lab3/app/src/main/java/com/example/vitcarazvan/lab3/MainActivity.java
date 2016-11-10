package com.example.vitcarazvan.lab3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sendEmail;
    EditText message;

    ListView listView;
    String[] carsArray= {"Seat","VW","BMW","Toyota"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_view);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carsArray);
        listView.setAdapter(adapter);

        sendEmail = (Button) findViewById(R.id.sendBtn);
        sendEmail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                message = (EditText)findViewById(R.id.multilineMsgTxt);
                String theMessage = message.getText().toString();
                sendEmail(theMessage);
            }
        });
    }

    private void sendEmail(String theMessage) {
        String[] to = {"vatca_razvan@yahoo.com"};
        String subject = ("A message from your app!");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, theMessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            finish();
            Log.i("Finished!...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
