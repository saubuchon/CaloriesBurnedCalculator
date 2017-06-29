package com.aubuchon.caloriesburnedcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private EditText weightET;
    private EditText nameET;
    private Spinner heightFootSpinner;
    private Spinner heightInchesSpinner;
    private SeekBar milesRanSeekBar;
    private TextView milesRanTV;
    private TextView caloriesBurnedTV;
    private TextView bmiTV;

    private String weightString;
    private String nameString;
    private float milesRan = 0;
    private float foot;
    private float inches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightET = (EditText) findViewById(R.id.weightET);
        nameET = (EditText) findViewById(R.id.nameET);
        heightFootSpinner = (Spinner) findViewById(R.id.heightFootSpinner);
        heightInchesSpinner = (Spinner) findViewById(R.id.heightInchesSpinner);
        milesRanSeekBar = (SeekBar) findViewById(R.id.milesRanSeekBar);
        milesRanTV = (TextView) findViewById(R.id.milesRanTV);
        caloriesBurnedTV = (TextView) findViewById(R.id.caloriesBurnedTV);
        bmiTV = (TextView) findViewById(R.id.bmiTV);

        weightET.setOnEditorActionListener(this);

        milesRanSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                milesRanTV.setText(progress + "mi");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            calculateAndDisplay();
        }
        return false;
    }

    private void calculateAndDisplay(){

        weightString = weightET.getText().toString();

        float weight;
        if(weightString.equals("")){
            weight = 0;
        }
        else {
            weight = Float.parseFloat(weightString);
        }

        int progress = milesRanSeekBar.getProgress();
        milesRan = (float) progress;

        float caloriesBurned =(float) .75*weight*milesRan;

        float bmi = (float) (weight*703)/((12 * foot + inches)* (12 * foot + inches));

    }
}
