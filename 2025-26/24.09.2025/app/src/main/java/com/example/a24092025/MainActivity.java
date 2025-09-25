package com.example.a24092025;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }
    public void WashingMachine(View view) {
        EditText WashNumberInput = findViewById(R.id.WashNumber);
        String WashNumberText = WashNumberInput.getText().toString().trim();

        if (!WashNumberText.isEmpty()) {
            try {
                int WashNumber = Integer.parseInt(WashNumberText);

                if (WashNumber >= 1 && WashNumber <= 12) {
                    TextView WashNumberOut = findViewById(R.id.WashNumberOut);
                    WashNumberOut.setText("Numer prania: " + WashNumber);
                } else {
                    WashNumberInput.setError("Podaj liczbę od 1 do 12");
                }
            } catch (NumberFormatException e) {
                WashNumberInput.setError("Nieprawidłowa liczba");
            }
        } else {
            WashNumberInput.setError("To pole nie może być puste");
        }
    }
    public void VacuumCleaner(View view) {
        Button StateBtn = findViewById(R.id.VacuumStateBtn);
        TextView VacuumState = findViewById(R.id.VacuumState);

        String currentState = VacuumState.getText().toString();

        if (currentState.equals("Odkurzacz wyłączony")) {
            StateBtn.setText("Wyłącz");
            VacuumState.setText("Odkurzacz włączony");
        }
        else if (currentState.equals("Odkurzacz włączony")) {
            StateBtn.setText("Włącz");
            VacuumState.setText("Odkurzacz wyłączony");
        }
    }
}