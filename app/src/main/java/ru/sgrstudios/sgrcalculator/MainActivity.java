package ru.sgrstudios.sgrcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private String currentInput = "";
    private double result = 0;
    private char currentOperator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);

        setNumberButtonListeners();
        setOperationButtonListeners();
        setFunctionButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDecimal
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button button = (Button) v;
                currentInput += button.getText().toString();
                updateInputField();
            });
        }
    }

    private void setOperationButtonListeners() {
        int[] operationButtonIds = {
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide
        };

        for (int id : operationButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button button = (Button) v;
                if (!currentInput.isEmpty()) {
                    performCalculation();
                    currentOperator = button.getText().toString().charAt(0);
                    currentInput = "";
                }
            });
        }
    }

    private void setFunctionButtonListeners() {
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentInput = "";
            result = 0;
            currentOperator = ' ';
            updateInputField();
        });

        findViewById(R.id.btnSquareRoot).setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double number = Double.parseDouble(currentInput);
                result = Math.sqrt(number);
                currentInput = String.valueOf(result);
                updateInputField();
            }
        });

        findViewById(R.id.btnSquare).setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double number = Double.parseDouble(currentInput);
                result = number * number;
                currentInput = String.valueOf(result);
                updateInputField();
            }
        });

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            performCalculation();
            currentOperator = ' ';
            updateInputField();
        });
    }

    @SuppressLint("SetTextI18n")
    private void performCalculation() {
        if (!currentInput.isEmpty()) {
            double number = Double.parseDouble(currentInput);
            switch (currentOperator) {
                case '+':
                    result += number;
                    break;
                case '-':
                    result -= number;
                    break;
                case '*':
                    result *= number;
                    break;
                case '/':
                    if (number != 0) {
                        result /= number;
                    } else {
                        inputField.setText("Error");
                        return;
                    }
                    break;
                default:
                    result = number;
                    break;
            }
            currentInput = String.valueOf(result);
        }
    }

    private void updateInputField() {
        inputField.setText(currentInput);
    }
}