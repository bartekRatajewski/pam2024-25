package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

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

    public int Throw_score = 0;
    public int Game_score = 0;
    public void ThrowSingleDice(ImageView dice){
        Random random = new Random();

        int result = 0;

        result = random.nextInt(6) + 1;

        Throw_score += result;

        Game_score += result;

        switch (result){
            case 1:
                dice.setImageResource(R.drawable.k1);
                break;

            case 2:
                dice.setImageResource(R.drawable.k2);
                break;

            case 3:
                dice.setImageResource(R.drawable.k3);
                break;

            case 4:
                dice.setImageResource(R.drawable.k4);
                break;

            case 5:
                dice.setImageResource(R.drawable.k5);
                break;

            case 6:
                dice.setImageResource(R.drawable.k6);
                break;
        }
    }
    public void ThrowDices(android.view.View view){
        ImageView dice1 = findViewById(R.id.dice1);
        ImageView dice2 = findViewById(R.id.dice2);
        ImageView dice3 = findViewById(R.id.dice3);
        ImageView dice4 = findViewById(R.id.dice4);
        ImageView dice5 = findViewById(R.id.dice5);

        Throw_score = 0;

        ThrowSingleDice(dice1);
        ThrowSingleDice(dice2);
        ThrowSingleDice(dice3);
        ThrowSingleDice(dice4);
        ThrowSingleDice(dice5);

        TextView throw_score = findViewById(R.id.throw_score_txtview);
        TextView game_score = findViewById(R.id.game_score_txtview);

        throw_score.setText("Wynik tego losowania: " + Throw_score);
        game_score.setText("Wynik gry: " + Game_score);

    }
    public void ResetScore(android.view.View view){
        Game_score = 0;
        Throw_score = 0;

        ImageView dice1 = findViewById(R.id.dice1);
        ImageView dice2 = findViewById(R.id.dice2);
        ImageView dice3 = findViewById(R.id.dice3);
        ImageView dice4 = findViewById(R.id.dice4);
        ImageView dice5 = findViewById(R.id.dice5);

        dice1.setImageResource(R.drawable.question);
        dice2.setImageResource(R.drawable.question);
        dice3.setImageResource(R.drawable.question);
        dice4.setImageResource(R.drawable.question);
        dice5.setImageResource(R.drawable.question);

        TextView throw_score = findViewById(R.id.throw_score_txtview);
        TextView game_score = findViewById(R.id.game_score_txtview);

        throw_score.setText("Wynik tego losowania:");
        game_score.setText("Wynik gry:");
    }
}