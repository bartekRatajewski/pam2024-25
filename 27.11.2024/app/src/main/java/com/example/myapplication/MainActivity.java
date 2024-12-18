package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        if(email.getText().toString().contains("@") && (email.getText().toString().contains("."))){

            if(password.getText().toString().equals(passwordRepeat.getText().toString())){
                if(password.getText().toString().matches(regex)){
                    TextView output = findViewById(R.id.output);

                    Intent Login = new Intent(MainActivity.this, LoggedInActivity.class);

                    Login.putExtra("email", email.getText().toString());

                    startActivity(Login);

                    email.setText("e-mail...");
                    password.setText("");
                    passwordRepeat.setText("");
                }
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