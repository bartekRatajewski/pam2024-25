package com.example.rgb;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView colorOut;
    private SeekBar redBar, greenBar, blueBar;
    private TextView redValue, greenValue, blueValue, rgbText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorOut = findViewById(R.id.colorOut);

        redBar = findViewById(R.id.redBar);
        greenBar = findViewById(R.id.greenBar);
        blueBar = findViewById(R.id.blueBar);

        redValue = findViewById(R.id.textView4);
        greenValue = findViewById(R.id.textView6);
        blueValue = findViewById(R.id.textView8);

        rgbText = findViewById(R.id.textView9);

        redBar.setMax(255);
        greenBar.setMax(255);
        blueBar.setMax(255);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        redBar.setOnSeekBarChangeListener(listener);
        greenBar.setOnSeekBarChangeListener(listener);
        blueBar.setOnSeekBarChangeListener(listener);

        updateColor();
    }

    private void updateColor() {
        int r = redBar.getProgress();
        int g = greenBar.getProgress();
        int b = blueBar.getProgress();

        redValue.setText(String.valueOf(r));
        greenValue.setText(String.valueOf(g));
        blueValue.setText(String.valueOf(b));

        int color = Color.rgb(r, g, b);
        colorOut.setBackgroundColor(color);

        rgbText.setText(r + ", " + g + ", " + b);
    }
}
