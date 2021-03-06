package com.dailycoding.examplesharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Button btnAdd, btnSub;
    private TextView txtTotal;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTotal = findViewById(R.id.txt_total);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);

        counter = PrefConfig.loadTotalFromPref(this);
        txtTotal.setText("Your total is: " + counter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                PrefConfig.saveTotalInPref(getApplicationContext(), counter);
                //txtTotal.setText("Your total is: " + counter);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                PrefConfig.saveTotalInPref(getApplicationContext(), counter);
                //txtTotal.setText("Your total is: " + counter);
            }
        });
    }

    public void resetPreferences(View view) {
        PrefConfig.removeDataFromPref(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PrefConfig.PREF_TOTAL_KEY)) {
            counter = PrefConfig.loadTotalFromPref(this);
            txtTotal.setText("Your total is: " + counter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        PrefConfig.registerPref(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PrefConfig.unregisterPref(this, this);
    }
}
