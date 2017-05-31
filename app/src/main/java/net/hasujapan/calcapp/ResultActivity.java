package net.hasujapan.calcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    // -----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        showResult();
    }

    // -----------------------------------------------------------------------

    private void showResult() {
        Intent intent = getIntent();
        String lfs = intent.getStringExtra("lfsValue");
        String rfs = intent.getStringExtra("rfsValue");
        String operator = intent.getStringExtra("operator");
        String result = intent.getStringExtra("resultValue");
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(lfs + " " + operator + " " + rfs + " = " + result);
    }

    // -----------------------------------------------------------------------
}
