package com.example.cmpe277_assignment2_implicit_intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText urlLinkEditText;
    private  EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlLinkEditText = findViewById(R.id.et_url);
        phoneNumberEditText = findViewById(R.id.et_phone);

        Button openURLBtn = findViewById(R.id.btn_url_launch);
        Button ringPhoneBtn = findViewById(R.id.btn_phone);
        Button exitBtn = findViewById(R.id.btn_exit);

        openURLBtn.setOnClickListener(v -> {
            String url = urlLinkEditText.getText().toString();
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
            goToUrl(url);
        });

        ringPhoneBtn.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString();
            Toast.makeText(getApplicationContext(), phoneNumber, Toast.LENGTH_SHORT).show();
            makePhoneCall(phoneNumber);
        });

        exitBtn.setOnClickListener(v -> finishAffinity());
    }

    /**
     * Initiates a phone call to the specified phone number.
     * Requires the CALL_PHONE permission. If the permission is not granted,
     * it requests the permission from the user.
     *
     * @param phoneNumber The phone number to call.
     */
    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
        } else {
            startActivity(intent);
        }
    }

    /**
     * Opens the specified URL in a web browser.
     *
     * @param url The URL to open.
     */
    private void goToUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}