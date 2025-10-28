package com.example.weterynarz;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nameIn, purposeIn, timeIn;
    private TextView ageOut, dataOut;
    private SeekBar ageBar;
    private ListView listView;
    private Button button;

    private String selectedSpecies = "";

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

        nameIn = findViewById(R.id.nameIn);
        purposeIn = findViewById(R.id.PurposeIn);
        timeIn = findViewById(R.id.timeIn);
        ageOut = findViewById(R.id.ageOut);
        dataOut = findViewById(R.id.dataOut);
        ageBar = findViewById(R.id.ageBar);
        listView = (ListView) findViewById(R.id.speciesList);
        button = findViewById(R.id.button);

        String[] species = {"Pies", "Kot", "Świnka morska"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                species
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSpecies = species[position];

                switch (selectedSpecies) {
                    case "Pies":
                        ageBar.setMax(18);
                        break;
                    case "Kot":
                        ageBar.setMax(20);
                        break;
                    case "Świnka morska":
                        ageBar.setMax(9);
                        break;
                }

                ageBar.setProgress(0);
                ageOut.setText("0");
            }
        });

        ageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageOut.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        button.setOnClickListener(v -> ProcessData(v));
    }

    public void ProcessData(View view) {
        String name = nameIn.getText().toString();
        String purpose = purposeIn.getText().toString();
        String time = timeIn.getText().toString();
        String age = ageOut.getText().toString();

        if (selectedSpecies.isEmpty()) {
            Toast.makeText(this, "Wybierz gatunek zwierzaka!", Toast.LENGTH_SHORT).show();
            return;
        }

        String output = name + ", " + selectedSpecies + ", " + age + ", " + purpose + ", " + time;
        dataOut.setText(output);
    }
}
