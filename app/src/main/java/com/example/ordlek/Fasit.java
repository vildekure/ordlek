package com.example.ordlek;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Fasit extends AppCompatActivity {
    TextView fasit, found;

    String[] fasitArray;
    ArrayList<String> fasitListe = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fasit);

        Button buttonBack = (Button) findViewById(R.id.back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /* fasit = findViewById(R.id.fasit);
        fasitArray = getResources().getStringArray(R.array.ord);
        fasitListe.addAll(Arrays.asList(fasitArray));
        ArrayAdapter<String> listeFasitAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fasitListe);
        fasit.setAdapter(listeFasitAdapter); */

        found = findViewById(R.id.found);
        ArrayList<String> ordliste = (ArrayList<String>) getIntent().getSerializableExtra("ordliste");
        found.setText(String.valueOf(ordliste));
        /* ArrayAdapter<String> listeFunnetAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fasitListe);
        found.setAdapter(listeFunnetAdapter); */

    }
}
