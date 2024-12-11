package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
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
    public void ValidateData(View view) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText passwordRepeat = findViewById(R.id.passwordRepeat);

        if(email.getText().toString().contains("@")){

            if(password.getText().toString().equals(passwordRepeat.getText().toString())){

                TextView output = findViewById(R.id.output);

                String outputText = "Witaj " + email.getText().toString();

                output.setText(outputText);

            }
            else {
                TextView output = findViewById(R.id.output);

                String outputText = "Hasła się różnią";

                output.setText(outputText);
            }
        }
        else {
            TextView output = findViewById(R.id.output);

            String outputText = "Nieprawidłowy adres email";

            output.setText(outputText);
        }
    }
}