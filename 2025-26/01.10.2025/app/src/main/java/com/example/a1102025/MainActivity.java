package com.example.a1102025;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String[] quotes = {"Dzień dobry", "Good morning", "Buenos diasR"};
    private int currentQuoteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView sizeLabel = findViewById(R.id.textView2);
        SeekBar fontSeekBar = findViewById(R.id.seekBar);
        TextView quoteText = findViewById(R.id.textView3);
        Button changeQuoteButton = findViewById(R.id.button);

        int initialSize = fontSeekBar.getProgress();
        sizeLabel.setText("Rozmiar: " + initialSize);
        quoteText.setTextSize(initialSize);

        fontSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 0) progress = 0;
                sizeLabel.setText("Rozmiar: " + progress);
                quoteText.setTextSize(progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        changeQuoteButton.setOnClickListener(v -> {
            currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
            quoteText.setText(quotes[currentQuoteIndex]);
        });
    }
}