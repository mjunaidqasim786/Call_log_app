package com.example.calllogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class call_logs extends AppCompatActivity {
    TextView call;
    Button makeCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_logs);
        call= findViewById(R.id.call);
        checkPermission(Manifest.permission.READ_CALL_LOG, 100);
        checkPermission(Manifest.permission.READ_CONTACTS, 101);
        checkPermission(Manifest.permission.CALL_PHONE, 102);
        getCallDetails();
        makeCall= findViewById(R.id.btnmakecall);
        makeCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), callactivity.class);
                startActivity(intent);
            }
        });
    }
    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery( CallLog.Calls.CONTENT_URI,null, null,null, null);
        int number = managedCursor.getColumnIndex( CallLog.Calls.NUMBER );
        int type = managedCursor.getColumnIndex( CallLog.Calls.TYPE );
        int date = managedCursor.getColumnIndex( CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex( CallLog.Calls.DURATION);
        sb.append( "Call Details :");
        while ( managedCursor.moveToNext() ) {
            String phNumber = managedCursor.getString( number );
            String callType = managedCursor.getString( type );
            String callDate = managedCursor.getString( date );
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString( duration );
            String dir = null;
            int dircode = Integer.parseInt( callType );
            switch( dircode ) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append( "\nPhone Number:--- "+phNumber +" \nCall Type:--- "+dir+" \nCall Date:--- "+callDayTime+" \nCall duration in sec :--- "+callDuration );
            sb.append("\n----------------------------------");
        }
        managedCursor.close();
        call.setText(sb);
    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(call_logs.this, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(call_logs.this, new String[] { permission }, requestCode);

        }
    }
}