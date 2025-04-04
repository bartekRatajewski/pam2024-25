package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

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

        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText toastText = findViewById(R.id.edit2);

                String textValue = toastText.getText().toString();

                Toast.makeText(getApplicationContext(), textValue, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void InsertText(View view) {
        EditText text = findViewById(R.id.edit1);

        String textValue = text.getText().toString();

        TextView output = findViewById(R.id.textView);

        output.setText(textValue);
    }
}