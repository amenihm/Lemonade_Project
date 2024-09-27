package com.example.applicationlemonade;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private int step = 1;
    private int squeezeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        updateUI();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        if (step == 1) {
            // Moving to squeezing lemon
            step = 2;
            squeezeCount = (int) (2 + Math.random() * 4); // Random squeezes between 2 and 6
        } else if (step == 2) {
            squeezeCount--;
            if (squeezeCount == 0) {
                // Squeezing done, go to drink stage
                step = 3;
            }
        } else if (step == 3) {
            // Drink finished, go to restart
            step = 4;
        } else {
            // Restart the process
            step = 1;
        }
        updateUI();
    }

    private void updateUI() {
        int imageResId;
        int textResId;

        switch (step) {
            case 1:
                imageResId = R.drawable.lemon_tree;
                textResId = R.string.select_lemon;
                break;
            case 2:
                imageResId = R.drawable.lemon_squeeze;
                textResId = R.string.squeeze_lemon;
                break;
            case 3:
                imageResId = R.drawable.lemon_drink;
                textResId = R.string.drink_lemonade;
                break;
            case 4:
            default:
                imageResId = R.drawable.lemon_restart;
                textResId = R.string.restart_lemonade;
                break;
        }

        imageView.setImageResource(imageResId);
        textView.setText(textResId);
    }
}