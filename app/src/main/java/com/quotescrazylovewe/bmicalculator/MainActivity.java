package com.quotescrazylovewe.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //class variables are also called as fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }


    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18) {
                    displayResult(bmiResult);

                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();
// converting the number variables to strings

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        // bmi formula
        return weight / (heightInMeters * heightInMeters);
        //String sBmi = String.valueOf(bmi);


    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");

        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            //display underweight
            fullResultString = bmiTextResult + "You are underwight ";
        } else if (bmi > 25) {
            // overweight
            fullResultString = bmiTextResult + "You are overweight";
        } else {
            fullResultString = bmiTextResult + "You are healthy ";
            //display healthy
        }

        resultText.setText(fullResultString);

    }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (maleButton.isChecked()) {
            fullResultString = bmiTextResult + "As you are under 18 please consult with your doctor for the healthy range for males";
        } else if (femaleButton.isChecked()) {
            fullResultString = bmiTextResult + "As you are under 18 please consult with your doctor for the healthy range for females";
        } else {
            fullResultString = bmiTextResult + "As you are under 18 please consult with your doctor for the healthy range ";
        }
        resultText.setText(fullResultString);
    }

}