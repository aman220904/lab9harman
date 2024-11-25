package com.example.lab9harman;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new FragmentDetails())
                .commit();


        // Find the Toolbar by its ID and set it as the app bar
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        // Optional: Customize the Toolbar (e.g., add a title)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Toolbar");
        }

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            // Create an AlertDialog to confirm deletion
            new android.app.AlertDialog.Builder(this)
                    .setTitle("Delete Message")
                    .setMessage("Do you want to delete this message?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // If user confirms, delete the selected message
                        FragmentDetails detailsFragment = (FragmentDetails) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);

                        if (detailsFragment != null) {
                            detailsFragment.deleteSelectedMessage();
                        } else {
                            Toast.makeText(this, "No fragment found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User chose not to delete, just dismiss the dialog
                        dialog.dismiss();
                    })
                    .show();

            return true;

        } else if (id == R.id.about) {
            // Show an AlertDialog when About item is clicked
            new android.app.AlertDialog.Builder(this)
                    .setTitle("About")
                    .setMessage("Version 1.0\nCreated by Harmandeep Kaur\nThis app demonstrates a simple toolbar with fragments.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // User clicked OK, dismiss the dialog
                        dialog.dismiss();
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Method to delete the selected chat message.
     * This method can be expanded to include fragment interaction or database updates.
     */
    private void deleteChatMessage() {
        // Assuming DetailsFragment is used to display chat messages
        FragmentDetails detailsFragment = (FragmentDetails) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (detailsFragment != null) {
            detailsFragment.deleteSelectedMessage(); // Call fragment-specific deletion logic
        } else {
            Toast.makeText(this, "No message selected", Toast.LENGTH_SHORT).show();
        }
    }
}
