package com.example.aforosapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeTextButton = findViewById(R.id.changeTextBtn);

        changeTextButton.setOnClickListener(changeTextListener);

    }

    public void changeText (View v) {
        Log.d("PRUEBA", "PRUEBA GUAY");
        v.setEnabled(false);
    }

    private View.OnClickListener changeTextListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            counter += 1;
            TextView text = findViewById(R.id.hello);
            text.setText("Pulsado " + counter + " veces.");
        }
    };
}