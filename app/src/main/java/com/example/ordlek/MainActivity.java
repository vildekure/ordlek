package com.example.ordlek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText text;
    TextView error;
    String word = "";
    String feilMeldKort, feilMeldMain, mainLetter, feilOrd, riktig;

    String [] fasitArray;
    ArrayList<String> fasitListe = new ArrayList<>();

    ArrayList<String> ordListe = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fasitArray = getResources().getStringArray(R.array.ord);
        fasitListe.addAll(Arrays.asList(fasitArray));

        text = (EditText) findViewById(R.id.editText);
        error = (TextView) findViewById(R.id.error);
        mainLetter = getResources().getString(R.string.buttonMain);
        riktig = getResources().getString(R.string.riktig);
        feilMeldKort = getResources().getString(R.string.feilMeldKort);
        feilMeldMain = getResources().getString(R.string.feilMeldMain);
        feilOrd = getResources().getString(R.string.feilOrd);

        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button buttonCheck = (Button) findViewById(R.id.buttonCheck);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        View.OnClickListener buttonClick = (view) -> {
            Button button = (Button) view;
            word += button.getText().toString();
            text.setText(word);
        };

        buttonMain.setOnClickListener(buttonClick);
        button1.setOnClickListener(buttonClick);
        button2.setOnClickListener(buttonClick);
        button3.setOnClickListener(buttonClick);
        button4.setOnClickListener(buttonClick);
        button5.setOnClickListener(buttonClick);
        button6.setOnClickListener(buttonClick);


        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                sjekk om ord er langt nok,
                sjekk om ord inneholder Main
                sjekk om ordet eksisterer i Array
                Legg ord i nytt array
                */

                if (word.length() < 4) {
                    error.setText(feilMeldKort);
                }
                else if (!word.contains(mainLetter)) {
                    error.setText(feilMeldMain);
                }
                else if (!fasitListe.contains(word)) {
                    error.setText(feilOrd);
                }
                else {
                    // ordListe.add(word);
                    error.setText(riktig);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // slett en bokstav fra ordet.
                word = word.replaceAll(".$", "");
                text.setText(word);
            }
        });

        /*
        Trenger en funksjon som sier hint.
        En funksjon som sier hvor mange ord man har/mangler
        */

        /*
        en onClick for å sendes til fasit
         */
    }
}