package com.example.s;

import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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
    public void Validate(View view){
        TextView fname = findViewById(R.id.firstName);
        TextView email = findViewById(R.id.emailAdress);

        TextView welcome_output = findViewById(R.id.txt1);
        TextView counter_output = findViewById(R.id.txt2);

        Integer counter = 1;

        String fname_value = fname.getText().toString();
        String email_value = email.getText().toString();

        if(fname_value == "" || email_value == ""){
            Toast.makeText(MainActivity.this, "Najpierw uzupełnij swoje dane", Toast.LENGTH_LONG).show();
        }
        else{
            welcome_output.setText("Witaj, " + fname_value + "! Twój adres e-mail to: " + email_value);
            counter_output.setText("Kliknąłeś przycisk " + counter + " razy");

            counter += 1;
        }
    }
}