package br.unip.myapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.unip.myapplication.R;

// TODO: Implement the rules to register the user APP.
// After the validations, change the intention to rule for the activity list the maps.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, ListingActivity.class);
        startActivity(intent);
    }
}
