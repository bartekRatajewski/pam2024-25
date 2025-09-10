package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[] imageResIds = {
            R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6,
            R.drawable.img7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout container = findViewById(R.id.imageContainer);

        for (int resId : imageResIds) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resId);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 400);
            params.setMarginEnd(16);
            imageView.setLayoutParams(params);

            imageView.setOnClickListener(v -> {
                ImageDialogFragment dialog = ImageDialogFragment.newInstance(resId);
                dialog.show(getSupportFragmentManager(), "imageDialog");
            });

            container.addView(imageView);
        }
    }
}
