package net.hasujapan.calcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    // -----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAddButtonListener();
        setSubtractButtonListener();
        setMultiplyButtonListener();
        setDivideButtonListener();
    }

    // -----------------------------------------------------------------------

    private void setAddButtonListener() {
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void setSubtractButtonListener() {
        Button subtractButton = (Button) findViewById(R.id.subtractButton);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
            }
        });
    }

    private void setMultiplyButtonListener() {
        Button multiplyButton = (Button) findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiply();
            }
        });
    }

    private void setDivideButtonListener() {
        Button divideButton = (Button) findViewById(R.id.divideButton);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide();
            }
        });
    }

    // -----------------------------------------------------------------------

    private BigDecimal getLfsFieldValue() {
        return getFieldValue(R.id.lfsField);
    }

    private BigDecimal getRfsFieldValue() {
        return getFieldValue(R.id.rfsField);
    }

    private BigDecimal getFieldValue(int fieldViewId) {
        EditText lfsField = (EditText) findViewById(fieldViewId);

        String value = lfsField.getText().toString();

        BigDecimal result;
        try {
            result = new BigDecimal(value);
        }
        catch(NumberFormatException e) {
            result = new BigDecimal(0); // エラー処理は省略して、数値として扱えない値が入力された場合すべて 0 として扱う
        }

        return result;
    }

    // -----------------------------------------------------------------------

    private void add() {
        BigDecimal lfsValue = getLfsFieldValue();
        BigDecimal rfsValue = getRfsFieldValue();

        BigDecimal result;
        try {
            result = lfsValue.add(rfsValue);
        }
        catch(NumberFormatException e) {
            result = new BigDecimal(0); // エラー処理は省略して、計算に失敗した場合はすべて 0 として扱う
        }

        toResultActivity(result, "+");
    }

    private void subtract() {
        BigDecimal lfsValue = getLfsFieldValue();
        BigDecimal rfsValue = getRfsFieldValue();

        BigDecimal result;
        try {
            result = lfsValue.subtract(rfsValue);
        }
        catch(NumberFormatException e) {
            result = new BigDecimal(0); // エラー処理は省略して、計算に失敗した場合はすべて 0 として扱う
        }

        toResultActivity(result, "-");
    }

    private void multiply() {
        BigDecimal lfsValue = getLfsFieldValue();
        BigDecimal rfsValue = getRfsFieldValue();

        BigDecimal result;
        try {
            result = lfsValue.multiply(rfsValue);
        }
        catch(NumberFormatException e) {
            result = new BigDecimal(0); // エラー処理は省略して、計算に失敗した場合はすべて 0 として扱う
        }

        toResultActivity(result, "*");
    }

    private void divide() {
        BigDecimal lfsValue = getLfsFieldValue();
        BigDecimal rfsValue = getRfsFieldValue();

        BigDecimal result;
        try {
            result = lfsValue.divide(rfsValue, 10, BigDecimal.ROUND_HALF_UP);
        }
        catch(NumberFormatException e) {
            result = new BigDecimal(0); // エラー処理は省略して、計算に失敗した場合はすべて 0 として扱う
        }

        toResultActivity(result, "/");
    }

    // -----------------------------------------------------------------------

    private void toResultActivity(BigDecimal resultValue, String operator) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("lfsValue", getLfsFieldValue().toString());
        intent.putExtra("rfsValue", getRfsFieldValue().toString());
        intent.putExtra("operator", operator);
        intent.putExtra("resultValue", resultValue.toString());
        startActivity(intent);
    }

    // -----------------------------------------------------------------------
}
