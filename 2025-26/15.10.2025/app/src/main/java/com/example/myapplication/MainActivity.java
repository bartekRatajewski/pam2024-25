package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
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

    int like_counter = 0;

    public void LikeClick(View view) {
        like_counter += 1;

        TextView like_output = findViewById(R.id.likeOut);
        String like_text = like_counter + " polubień";
        like_output.setText(like_text);
    }

    public void DislikeClick(View view) {
        if(like_counter >= 1){
            like_counter -= 1;

            TextView like_output = findViewById(R.id.likeOut);
            String like_text = like_counter + " polubień";
            like_output.setText(like_text);
        }
    }
}