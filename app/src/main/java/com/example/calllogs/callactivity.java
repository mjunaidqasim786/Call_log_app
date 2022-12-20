package com.example.calllogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class callactivity extends AppCompatActivity {

    EditText edittext1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callactivity);

        //Getting the edittext and button instance
        edittext1=(EditText)findViewById(R.id.editText1);
        button1=(Button)findViewById(R.id.button1);

        //Performing action on button click
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String number=edittext1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
    }


}