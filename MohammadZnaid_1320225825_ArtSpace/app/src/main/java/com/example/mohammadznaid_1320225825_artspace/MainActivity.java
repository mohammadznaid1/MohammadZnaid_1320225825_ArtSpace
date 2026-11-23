
package com.example.mohammadznaid_1320225825_artspace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private TextView artworkCounter, artworkTitle, artworkArtist;
    private ImageView artworkImage;
    private Button previousButton, nextButton;

    private int currentIndex = 0;

    private final int[] artworkImages = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    private final String[] artworkTitles = {
            "Camel of Hardship",
            "Motherhood I",
            "We Shall Return",
            "Palestinian Weddings",
            "White Cube in Brown Cube",
            "Where To?"
    };

    private final String[] artworkArtists = {
            "Sliman Mansour",
            "Nabil Anani",
            "Ismail Shammout",
            "Abdel Rahman Al Muzayen",
            "Samia Halaby",
            "Ismail Shammout"
    };

    private final String[] artworkYears = {
            "1973",
            "1976",
            "1954",
            "1970",
            "1969",
            "1953"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        artworkCounter = findViewById(R.id.artworkCounter);
        artworkImage = findViewById(R.id.artworkImage);
        artworkTitle = findViewById(R.id.artworkTitle);
        artworkArtist = findViewById(R.id.artworkArtist);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        artworkImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);

            intent.putExtra("title", artworkTitles[currentIndex]);
            intent.putExtra("artist", artworkArtists[currentIndex]);
            intent.putExtra("year", artworkYears[currentIndex]);

            startActivity(intent);
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("artwork_index", 0);
        }

        nextButton.setOnClickListener(v -> {
            if (currentIndex < artworkImages.length - 1) {
                currentIndex++;
                updateArtwork();
            }
        });

        previousButton.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtwork();
            }
        });

        updateArtwork();

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    private void updateArtwork() {
        artworkImage.setImageResource(artworkImages[currentIndex]);
        artworkTitle.setText(artworkTitles[currentIndex]);

        artworkArtist.setText(
                artworkArtists[currentIndex]
                        + " ("
                        + artworkYears[currentIndex]
                        + ")"
        );

        artworkCounter.setText(
                (currentIndex + 1)
                        + " / "
                        + artworkImages.length
        );

        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(
                currentIndex < artworkImages.length - 1
        );
        previousButton.setAlpha(currentIndex == 0 ? 0.4f : 1.0f);
        nextButton.setAlpha(currentIndex == artworkImages.length - 1 ? 0.4f : 1.0f);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("artwork_index", currentIndex);
        super.onSaveInstanceState(outState);
    }
}
