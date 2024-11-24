package com.example.sqlitedatabaseapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseHelper.addContact("A","10");
        databaseHelper.addContact("B","20");
        databaseHelper.addContact("C","30");

        ArrayList<ContactModel> arrayList = databaseHelper.fetchContact();

        for (int i=0;i<arrayList.size();i++){
            Log.d("CONTACT INFO "+arrayList.get(i).name,"PHONE NO"+ arrayList.get(i).phone_no);
        }


        ContactModel contactModel = new ContactModel();
        contactModel.id = 1;
        contactModel.name = "Raghav";
        contactModel.phone_no = "123456789";

        databaseHelper.updateData(contactModel);

    }
}