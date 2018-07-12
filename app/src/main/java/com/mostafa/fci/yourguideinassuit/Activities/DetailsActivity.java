package com.mostafa.fci.yourguideinassuit.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.mostafa.fci.yourguideinassuit.Constants.JSONKeysNames;
import com.mostafa.fci.yourguideinassuit.R;
import com.mostafa.fci.yourguideinassuit.classes.Place;

public class DetailsActivity extends AppCompatActivity {


    String telePhone;
    private AlertDialog.Builder builder1;
    String callingPhones;
    String[] phones;
    private AlertDialog dialog;
    private AlertDialog.Builder builder2;
    TextView nameTextView , phoneTextView ,addressTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameTextView    = findViewById(R.id.namePlaceDetailsActivity);
        phoneTextView   = findViewById(R.id.phonePlaceDetailsActivity);
        addressTextView = findViewById(R.id.addressDetailsActivity);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/DroidNaskh-Bold.ttf");
        nameTextView.setTypeface(typeface);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(),"fonts/DroidNaskh-Regular.ttf");
        phoneTextView.setTypeface(typeface2);
        addressTextView.setTypeface(typeface);
        Intent intent = getIntent();
        Place place = (Place) intent.getExtras().getSerializable("place");
        if (place.getType().equals(JSONKeysNames.CLINICS)) {
            nameTextView.setText("عيادة"+ place.getName());
            phoneTextView.setText(place.getPhone());
            addressTextView.setText(place.getAddress());
        }else {
            nameTextView.setText(place.getName());
            phoneTextView.setText(place.getPhone());
            addressTextView.setText(place.getAddress());
        }
        //******************************************************************************************
        telePhone = phoneTextView.getText().toString();
        phones = telePhone.split(",");
        if (phones.length == 1 ) {
            callingPhones = phones[0];
        } else if (phones.length > 1 ) {
            builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("اختر رقم التليفون")
                    .setIcon(R.drawable.ic_call_black_24dp)
                    .setSingleChoiceItems(phones, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callingPhones = phones[which];
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + callingPhones));
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
            builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("اختر رقم التليفون")
                    .setIcon(R.drawable.ic_sms_black_24dp)
                    .setSingleChoiceItems(phones, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callingPhones = phones[which];
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+callingPhones));
                            intent.putExtra("sms_body", "Message body SMS here ........ ");
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
        }
        //******************************************************************************************

    }

    public void SMS_btn_Clicked(View view) {

        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Button btn_done = findViewById(R.id.sms);
        btn_done.startAnimation(animShake);

        if (phones.length > 1 ) {
            dialog = builder2.create();
            dialog.show();
        }else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+callingPhones));
            intent.putExtra("sms_body", "Message body od SMS");
            startActivity(intent);
        }

    }

    public void Calling_btn_Clicked(View view) {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Button btn_done = findViewById(R.id.call);
        btn_done.startAnimation(animShake);
        if (phones.length > 1 ) {
            dialog = builder1.create();
            dialog.show();
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + callingPhones ));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }
}
