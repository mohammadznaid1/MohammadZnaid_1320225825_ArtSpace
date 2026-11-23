package com.example.mohammadznaid_1320225825_artspace;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_artwork_details);

        TextView detailsTitle = findViewById(R.id.detailsTitle);
        TextView detailsArtist = findViewById(R.id.detailsArtist);
        TextView detailsYear = findViewById(R.id.detailsYear);

        Button visitWebsiteButton = findViewById(R.id.visitWebsiteButton);
        Button backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        String year = intent.getStringExtra("year");

        detailsTitle.setText(title);
        detailsArtist.setText("Artist: " + artist);
        detailsYear.setText("Year: " + year);

        backButton.setOnClickListener(v -> finish());

        visitWebsiteButton.setOnClickListener(v -> {
            Intent websiteIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/search?q=Palestinian+art")
            );

            try {
                startActivity(websiteIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No browser available", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
