package com.adb.shadowrundice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private int goalDice = 5 ;
    private Random r;
    private Spinner spinner;
    private EditText etResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        spinner = (Spinner) findViewById(R.id.spinner_nb_dice);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,

                R.array.dice_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                throwDice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnRetry = findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(v -> throwDice());

        etResult =  findViewById(R.id.edit_result);

    }

    private  void throwDice(){

        StringBuilder sbResult = new StringBuilder();

        int iNbDice = Integer.parseInt(String.valueOf((CharSequence) spinner.getSelectedItem()));
        int hit = 0;
        int rint;
        for (int i = 0; i < iNbDice; i++) {
            rint = r.nextInt(6) +1;

            sbResult.append(String.valueOf(rint)).append(" ");
            if (rint >= goalDice) {
                hit++;
            }


        }

        sbResult.append("\n")
                .append(
                        String.format(
                                getString(
                                        R.string.mainActivity_formatResult
                                ),
                                hit)
                )
                .append("\n")
                .append("\n");

        etResult.append(sbResult);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.main_radioButton4:
                if (checked)
                    goalDice = 4 ;
                    break;

            case R.id.main_radioButton5:
                if (checked)
                    goalDice = 5 ;
                    break;

            case R.id.main_radioButton6:
                if (checked)
                    goalDice = 6 ;
                    break;
        }
    }

}