package com.example.ordlek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText text;
    TextView error, teller;
    String word = "";
    String feilMeldKort, feilMeldMain, mainLetter, feilOrd, riktig;
    int tellerInt = 0;

    String [] fasitArray;
    ArrayList<String> fasitListe = new ArrayList<>();

    ArrayList<String> ordListe = new ArrayList<>();

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        error = (TextView) findViewById(R.id.error);
        teller = (TextView) findViewById(R.id.teller);

        outstate.putInt("tellerInt", tellerInt);
        outstate.putString("teller", teller.getText().toString());
        outstate.putString("error", error.getText().toString());
        outstate.putStringArrayList("ordListe", ordListe);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        error = (TextView) findViewById(R.id.error);
        teller = (TextView) findViewById(R.id.teller);

        tellerInt = savedInstanceState.getInt("tellerInt");
        teller.setText(savedInstanceState.getString("teller"));
        error.setText(savedInstanceState.getString("error"));
        ordListe.addAll(savedInstanceState.getStringArrayList("ordListe"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fasitArray = getResources().getStringArray(R.array.ord);
        fasitListe.addAll(Arrays.asList(fasitArray));
        text = (EditText) findViewById(R.id.editText);
        error = (TextView) findViewById(R.id.error);
        teller = (TextView) findViewById(R.id.teller);
        teller.setText("0/15");
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
        Button buttonHint = (Button) findViewById(R.id.buttonHint);
        Button buttonFasit = (Button) findViewById(R.id.buttonFasit);

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
                else if (ordListe.contains(word)) {
                    error.setText("Allerede gjort"); //må lage i string.xml
                }
                else {
                    ordListe.add(word);
                    error.setText(riktig);
                    text.setText("");
                    word = "";
                    tellerInt++;
                    teller.setText(tellerInt + "/15");
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

        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hint knapp
                error.setText("Her er hint");
                String fasit = "";
                String hint = "";
                for (int i=0;i<fasitListe.size();i++) {
                    if (!ordListe.contains(fasitListe.get(i))){
                        fasit = fasitListe.get(i);
                        break;
                    }
                }
                hint = fasit.substring(0, 2);
                error.setText(hint + "...");
            }
        });

        buttonFasit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // en onClick for å sendes til fasit
                Intent fasitAktivitet = new Intent(MainActivity.this, Fasit.class);
                fasitAktivitet.putExtra("ordliste", ordListe);
                startActivity(fasitAktivitet);
            }
        });


    }
}