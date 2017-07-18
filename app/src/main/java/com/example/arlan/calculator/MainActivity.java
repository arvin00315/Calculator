package com.example.arlan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_0;
    Button button_period;
    Button button_add;
    Button button_subtract;
    Button button_multiply;
    Button button_divide;
    Button button_equal;
    Button button_clear;
    Button button_back;

    TextView textViewTotal;
    TextView textViewInfo;

    double total = 0;
    String lastItem;

    //This is how many decimals you would want to be displayed when calculated
    DecimalFormat decimalFormat = new DecimalFormat("0.##########");

    ArrayList<ArrayList<String>> multiDimenArray = new ArrayList<ArrayList<String>>();
    ArrayList<String> singleArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = (Button) findViewById(R.id.id_1);
        button_1.setOnClickListener(this);

        button_2 = (Button) findViewById(R.id.id_2);
        button_2.setOnClickListener(this);

        button_3 = (Button) findViewById(R.id.id_3);
        button_3.setOnClickListener(this);

        button_4 = (Button) findViewById(R.id.id_4);
        button_4.setOnClickListener(this);

        button_5 = (Button) findViewById(R.id.id_5);
        button_5.setOnClickListener(this);

        button_6 = (Button) findViewById(R.id.id_6);
        button_6.setOnClickListener(this);

        button_7 = (Button) findViewById(R.id.id_7);
        button_7.setOnClickListener(this);

        button_8 = (Button) findViewById(R.id.id_8);
        button_8.setOnClickListener(this);

        button_9 = (Button) findViewById(R.id.id_9);
        button_9.setOnClickListener(this);

        button_0 = (Button) findViewById(R.id.id_0);
        button_0.setOnClickListener(this);

        button_period = (Button) findViewById(R.id.id_period);
        button_period.setOnClickListener(this);

        button_add = (Button) findViewById(R.id.id_add);
        button_add.setOnClickListener(this);

        button_subtract = (Button) findViewById(R.id.id_subtract);
        button_subtract.setOnClickListener(this);

        button_multiply = (Button) findViewById(R.id.id_multiply);
        button_multiply.setOnClickListener(this);

        button_divide = (Button) findViewById(R.id.id_divide);
        button_divide.setOnClickListener(this);

        button_clear = (Button) findViewById(R.id.id_clear);
        button_clear.setOnClickListener(this);

        button_equal = (Button) findViewById(R.id.id_equal);
        button_equal.setOnClickListener(this);

        button_back = (Button) findViewById(R.id.id_back);
        button_back.setOnClickListener(this);

        textViewInfo = (TextView) findViewById(R.id.text_view_info);
        textViewTotal = (TextView) findViewById(R.id.text_view_total);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("arvinsTag", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("arvinsTag", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("arvinsTag", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("arvinsTag", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("arvinsTag", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("arvinsTag", "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("arvinsTag", "onSaveInstanceState()");
        try {

            //Pass every elements of multiDimenArray to tempArray to be able to use it
            //in onRestoreInstanceState ()
            ArrayList<String> tempArray = new ArrayList<String>();
            for (int i = 0; i < multiDimenArray.size(); i++) {
                for (int j = 0; j < multiDimenArray.get(i).size(); j++) {
                    tempArray.add(multiDimenArray.get(i).get(j));
                }
            }

            //save tempArray
            outState.putStringArrayList("tempArray", tempArray);

            //save singleArray
            outState.putStringArrayList("singleArray", singleArray);

            //save total
            outState.putDouble("total", total);


            //outState.putString("multiDimenArray", multiDimenArray.toString());


        } catch (Exception e) {
            Log.e("arvinsTag", "onSaveInstanceState(): " + e);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("arvinsTag", "onRestoreInstanceState()");

        try {
            ArrayList<String> tempArray = new ArrayList<String>();
            ArrayList<String> tempArray2 = new ArrayList<String>();

            tempArray = (ArrayList<String>) savedInstanceState.get("tempArray");

            Log.i("arvinsTag", "tempArray: "+tempArray);

            //Load the data back of multiDimenArray
            for (int i = 0; i < tempArray.size(); i++) {
                if (tempArray.get(i).matches("[0-9]+|\\.|\\(|\\)")) {

                    //this condition will add "(" and the minus "-"
                    if(tempArray.get(i).matches("\\(")){
                        tempArray2.add(new ArrayList<String>(tempArray).get(i));
                        i++;
                    }


                    tempArray2.add(new ArrayList<String>(tempArray).get(i));
                } else {
                    multiDimenArray.add(new ArrayList<String>(tempArray2));
                    tempArray2.clear();
                    tempArray2.add(tempArray.get(i));
                    multiDimenArray.add(new ArrayList<String>(tempArray2));
                    tempArray2.clear();
                }

            }


            //Load the data back of singleDimenArray
            singleArray = (ArrayList<String>) savedInstanceState.get("singleArray");

            //Load the data back of total
            total = (Double) savedInstanceState.get("total");

            //Re-display the UI
            displayText();

            Log.i("arvinsTag", "singleArray: " + singleArray);
            Log.i("arvinsTag", "multiDimenArray: " + multiDimenArray);

        } catch (Exception e) {
            Log.e("arvinsTag", "onRestoreInstanceState(): " + e);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.id_1) {
            inputToArray("1");
            displayText();
        } else if (view.getId() == R.id.id_2) {
            inputToArray("2");
            displayText();
        } else if (view.getId() == R.id.id_3) {
            inputToArray("3");
            displayText();
        } else if (view.getId() == R.id.id_4) {
            inputToArray("4");
            displayText();
        } else if (view.getId() == R.id.id_5) {
            inputToArray("5");
            displayText();
        } else if (view.getId() == R.id.id_6) {
            inputToArray("6");
            displayText();
        } else if (view.getId() == R.id.id_7) {
            inputToArray("7");
            displayText();
        } else if (view.getId() == R.id.id_8) {
            inputToArray("8");
            displayText();
        } else if (view.getId() == R.id.id_9) {
            inputToArray("9");
            displayText();
        } else if (view.getId() == R.id.id_0) {
            inputToArray("0");
            displayText();
        } else if (view.getId() == R.id.id_period) {
            inputToArray(".");
            displayText();
        } else if (view.getId() == R.id.id_add) {
            inputToArray("+");
            displayText();
        } else if (view.getId() == R.id.id_subtract) {
            inputToArray("-");
            displayText();
        } else if (view.getId() == R.id.id_multiply) {
            inputToArray("*");
            displayText();
        } else if (view.getId() == R.id.id_divide) {
            inputToArray("/");
            displayText();
        } else if (view.getId() == R.id.id_back) {
            removeLastData();
            displayText();
        } else if (view.getId() == R.id.id_clear) {
            total = 0;
            clearData();
            displayText();
        } else if (view.getId() == R.id.id_equal) {
            calculate();
            textViewInfo.setText(null);
        }

    }


    //Display data in UI
    public void displayText() {

        try {

            String totalText = "";

            if (!singleArray.isEmpty()) {

                //Get data from multiDimentArray and store to totalText
                if (!multiDimenArray.isEmpty()) {
                    for (int i = 0; i < multiDimenArray.size(); i++) {
                        String currentData = multiDimenArray.get(i).toString().replaceAll("\\[|\\]|\\,|\\s", "");
                        totalText = totalText + currentData;
                    }
                }

                //Get data from singleArray and store it to totalText
                for (int i = 0; i < singleArray.size(); i++) {
                    String currentData = singleArray.get(i);
                    totalText = totalText + currentData;
                }


            }
            //Get data from multiDimentArray and store to totalText
            else {
                for (int i = 0; i < multiDimenArray.size(); i++) {
                    String currentData = multiDimenArray.get(i).toString().replaceAll("\\[|\\]|\\,|\\s", "");
                    totalText = totalText + currentData;
                }
            }

            textViewInfo.setText(""+totalText);
            textViewTotal.setText("" + decimalFormat.format(total));
            totalText = "";

        } catch (Exception e) {
            Log.e("arvinsTag", "displayText(): " + e);
        }
    }


    public void inputToArray(String buttonText) {
        try {

            //DIGITS
            if (buttonText.matches("[0-9]+")) {


                //Get total to be able to use again if user input another data after pressing '='
                if (total != 0) {
                    total = 0;
                    singleArray.add(buttonText);
                } else {

                    //If the singleArray contains "(-)"
                    //will add the data instead before ")"
                    if (singleArray.contains(")")) {
                        singleArray.add(singleArray.size() - 1, buttonText);
                    } else {
                        singleArray.add(buttonText);
                    }
                }

            }

            //PERIOD
            else if (buttonText.matches("\\.")) {

                //Prevent adding another period('.') if already exist in singleArray
                if (!singleArray.contains(".")) {

                    //If the singleArray contains "(-)"
                    //will add the data instead before ")"
                    if (singleArray.contains(")")) {
                        singleArray.add(singleArray.size() - 1, buttonText);
                    } else {
                        singleArray.add(buttonText);
                    }

                }

            }

            //OPERATORS
            else {

                //Get total to be able to use again if user input another data after pressing '='
                if (total != 0) {
                    singleArray.add(decimalFormat.format(total));
                    total = 0;
                }


                /**
                 * add the singleArray to multiDimenArray then clear the data of singleArray
                 * so that the data in singleArray can be considered as last data
                 * */
                if (!singleArray.isEmpty()) {

                    String concatArray = singleArray.toString().replaceAll("\\,|\\s|\\[|\\]", "");


                    //Compare if it matches to digits, if true, proceed adding it
                    //if found digits or (-digits)
                    if (concatArray.matches("[0-9.]+") || concatArray.matches("\\(-[0-9.]+\\)")) {
                        multiDimenArray.add(new ArrayList<String>(singleArray));
                        singleArray.clear();

                        //if found only (-)
                    } else {
                        buttonText = "(-)";
                    }

                }


                //Get the last element in multiDimenArray and replace brackets with white space
                if (!multiDimenArray.isEmpty()) {
                    lastItem = multiDimenArray.get(multiDimenArray.size() - 1).toString().replaceAll("\\[|\\]", "");
                }else{
                    //Only accept minus "-" and consider it as negative if this is the very first input of the user
                    switch (buttonText) {
                        case "-":
                            singleArray.add("(");
                            singleArray.add("-");
                            singleArray.add(")");
                            break;
                    }
                }


                //Triggers if last item in multiDimenArray does not contains Arithmetic operators
                if (!lastItem.matches("\\+|-|\\*|\\/")) {

                    //Store operator to multiDimenArray
                    switch (buttonText) {
                        case "+":
                            multiDimenArray.add(new ArrayList<String>(Arrays.asList("+")));
                            break;
                        case "-":
                            multiDimenArray.add(new ArrayList<String>(Arrays.asList("-")));
                            break;
                        case "*":
                            multiDimenArray.add(new ArrayList<String>(Arrays.asList("*")));
                            break;
                        case "/":
                            multiDimenArray.add(new ArrayList<String>(Arrays.asList("/")));
                            break;
                    }

                } else {
                    //If there is already an operator
                    switch (buttonText) {
                        case "+":
                            multiDimenArray.set(multiDimenArray.size() - 1, new ArrayList<String>(Arrays.asList("+")));
                            break;
                        case "-":
                            singleArray.add("(");
                            singleArray.add("-");
                            singleArray.add(")");
                            break;
                        case "*":
                            multiDimenArray.set(multiDimenArray.size() - 1, new ArrayList<String>(Arrays.asList("*")));
                            break;
                        case "/":
                            multiDimenArray.set(multiDimenArray.size() - 1, new ArrayList<String>(Arrays.asList("/")));
                            break;
                    }
                }

            }

            Log.v("arvinsTag", "Single Array: " + singleArray);
            Log.v("arvinsTag", "Multidimensional Array: " + multiDimenArray);
            Log.v("arvinsTag", "Total: " + total);

        } catch (Exception e) {
            Log.e("arvinsTag", "inputToArray(): " + e);
        }

    }


    public void removeLastData() {

        try {
            String lastDimenArrayItem;
            int lastIndexofSubArray;
            int lastIndexofMainArray;


            if (!singleArray.isEmpty()) {
                //Get the last index of sub array
                lastIndexofSubArray = singleArray.size() - 1;
                String concatArray = singleArray.toString().replaceAll("\\,|\\s|\\[|\\]", "");


                //If last index of singleArray contains ")"
                //Will remove "(.)" when pressed the "Back" button
                if (concatArray.matches("[0-9.]+")) {

                    //Remove the last index of sub array
                    singleArray.remove(lastIndexofSubArray);

                } else if (concatArray.matches("\\(-[0-9.]+\\)")) {

                    singleArray.remove(lastIndexofSubArray - 1);

                } else {
                    singleArray.remove(lastIndexofSubArray);
                    singleArray.remove(lastIndexofSubArray - 1);
                    singleArray.remove(lastIndexofSubArray - 2);
                }


            } else {
                if (!multiDimenArray.isEmpty()) {

                    //Get the last item in multiDimenArray
                    lastIndexofMainArray = multiDimenArray.size() - 1;

                    //Store the last item of multiDimenArray's sub array(singleArray)
                    lastIndexofSubArray = multiDimenArray.get(lastIndexofMainArray).size() - 1;

                    //Store the last item of multiDimentArray and replace brackets with white space
                    lastDimenArrayItem = multiDimenArray.get(lastIndexofMainArray).toString().replaceAll("\\[|\\]", "");


                    //This will remove the last operator and the last set of numbers of multiDimenArray
                    //and will pass data to singleArray
                    if (lastDimenArrayItem.matches("\\+|-|\\*|\\/") || lastDimenArrayItem.isEmpty()) {
                        multiDimenArray.remove(lastIndexofMainArray);

                        //Pass the last items of multiDimenArray that contains numbers to singleArray
                        for (int i = 0; i < multiDimenArray.get(lastIndexofMainArray - 1).size(); i++) {
                            singleArray.add(multiDimenArray.get(multiDimenArray.size() - 1).get(i));
                        }

                        //Delete the last items of multiDimenArray that contains numbers
                        multiDimenArray.remove(lastIndexofMainArray - 1);

                        //If the last element in Array contains (-) or (-digits)
                        //remove the last element
                    } else if (lastDimenArrayItem.matches("\\(-[0-9]+\\)|\\(-\\)")) {
                        multiDimenArray.remove(lastIndexofMainArray);
                    } else {
                        multiDimenArray.get(lastIndexofMainArray).remove(lastIndexofSubArray);
                    }

                } else {
                    Log.e("arvinsTag", "No Data In Array!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("arvinsTag", "removeLastData(): " + e);
        }

        Log.v("arvinsTag", "Single Array: " + singleArray);
        Log.v("arvinsTag", "Multidimensional Array: " + multiDimenArray);
        Log.v("arvinsTag", "Total: " + decimalFormat.format(total));
    }

    public void clearData() {
        multiDimenArray.clear();
        singleArray.clear();
        lastItem = null;
        textViewInfo.setText(null);
    }

    public void calculate() {

        try {
            /*
            * Add the current data of singleArray to multiDimenArray so that the current data
            * will still be calculated if pressed equal(=)*/
            if (!singleArray.isEmpty()) {

                multiDimenArray.add(new ArrayList<String>(singleArray));
                singleArray.clear();
            }


            for (int i = 0; i < multiDimenArray.size(); i++) {
                //Concatenate all data in each index of multiDimenArray
                String currentData = multiDimenArray.get(i).toString().replaceAll("\\[|\\]|\\,|\\s|\\(|\\)", "");
                String operatorHolder = null;
                double numberHolder = 0;

                //Store currentData to operatorHolder if the data is an operator
                if (currentData.matches("\\+|-|\\*|\\/")) {
                    operatorHolder = currentData;

                    //Jump to next index of multiDimenArray to fetch the digits
                    i++;
                    String tempHolder = multiDimenArray.get(i).toString().replaceAll("\\[|\\]|\\,|\\s|\\(|\\)", "");

                    //If current data is a period(.) the data will be thrown to int variable (numberHolder)
                    if (tempHolder.matches("\\.")) {
                        numberHolder = Integer.parseInt(tempHolder);

                        //Jump again to the next index of multiDimenArray
                        i++;
                        int tempHolder2 = Integer.parseInt(multiDimenArray.get(i).toString().replaceAll("\\[|\\]|\\,|\\s", ""));

                        numberHolder = numberHolder + tempHolder2;

                    } else {
                        numberHolder = Double.parseDouble(tempHolder);
                    }


                    switch (operatorHolder) {
                        case "+":
                            total = total + numberHolder;
                            break;
                        case "-":
                            total = total - numberHolder;
                            break;
                        case "*":
                            total = total * numberHolder;
                            break;
                        case "/":
                            total = total / numberHolder;
                            break;
                    }

                } else {
                    total = Double.parseDouble(currentData);
                }


            }

            Log.v("arvinsTag", "Single Array: " + singleArray);
            Log.v("arvinsTag", "Multidimensional Array: " + multiDimenArray);
            Log.v("arvinsTag", "Total: " + decimalFormat.format(total));
            displayText();
            clearData();
        } catch (Exception e) {
            Log.e("arvinsTag", "calculate(): " + e);
            clearData();
            total = 0;
        }
    }


}
