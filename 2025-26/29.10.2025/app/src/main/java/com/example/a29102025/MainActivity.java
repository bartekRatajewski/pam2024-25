package com.example.a29102025;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;

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

    public void CypherText(View view) {
        TextView key_input = findViewById(R.id.keyInput);
        TextView text_input = findViewById(R.id.textInput);
        TextView output = findViewById(R.id.cypherOut);

        String key_string = key_input.getText().toString();
        String text_string = text_input.getText().toString().toUpperCase();

        int key = Integer.parseInt(key_string);
        int iterations = text_string.length();

        String[] Alphabet = {
                "A", "Ą", "B", "C", "Ć", "D", "E", "Ę", "F", "G", "H", "I", "J",
                "K", "L", "Ł", "M", "N", "Ń", "O", "Ó", "P", "Q", "R", "S", "Ś",
                "T", "U", "V", "W", "X", "Y", "Z", "Ź", "Ż"
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < iterations; i++) {
            String letter = String.valueOf(text_string.charAt(i)).toUpperCase();

            int index = -1;
            for (int j = 0; j < Alphabet.length; j++) {
                if (Alphabet[j].equals(letter)) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                int newIndex = (index + key) % Alphabet.length;
                result.append(Alphabet[newIndex]);
            } else {
                result.append(letter);
            }
        }

        output.setText(result.toString());
    }

    public void SaveToFile(View view) {
        try {
            String filename = "szyfr.txt";
            TextView output = findViewById(R.id.cypherOut);
            String data = output.toString();

            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(this, "Zapisano do pliku: " + filename, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd zapisu pliku!", Toast.LENGTH_SHORT).show();
        }
    }
}