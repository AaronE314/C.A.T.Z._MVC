package calculator.mvc.aaron.calculator;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double dFirstNum = 0;
    boolean prepForClear = true;
    MathFunction mFunc = new MathFunction();
    ArrayList<Button> alButtons;
    TextView tvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnMinus,
                btnMultiply, btnDivide, btnClear, btnPercent, btnNegative, btnPoint, btnEquals;

        alButtons = new ArrayList<>();
        btn0 = (Button) findViewById(R.id.btn0);
        alButtons.add(btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        alButtons.add(btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        alButtons.add(btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        alButtons.add(btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        alButtons.add(btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        alButtons.add(btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        alButtons.add(btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        alButtons.add(btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        alButtons.add(btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        alButtons.add(btn9);
        btnAdd = (Button) findViewById(R.id.btnadd);
        btnMinus = (Button) findViewById(R.id.btnsubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btndivide);
        btnClear = (Button) findViewById(R.id.btnclear);
        btnPercent = (Button) findViewById(R.id.btnpercent);
        btnNegative = (Button) findViewById(R.id.btnnegative);
        btnPoint = (Button) findViewById(R.id.btnpoint);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);

        for (int i = 0; i < 10; i++) {
            alButtons.get(i).setText(String.valueOf(i));
            alButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v;
                    Double dValue;
                    if (prepForClear) {
                        dValue =Double.parseDouble(btn.getText().toString());
                        prepForClear = false;
                    }else {
                        dValue =Double.parseDouble(tvDisplay.getText() +btn.getText().toString());
                    }
                    tvDisplay.setText(stringifyNum(dValue));
                }
            });
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMath();
                dFirstNum = Double.parseDouble(tvDisplay.getText().toString());
                mFunc = new MathFunction() {
                    @Override
                    public double function(double dNum1, double dNum2) {
                        return dNum1 + dNum2;
                    }
                };
                prepForClear = true;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMath();
                dFirstNum = Double.parseDouble(tvDisplay.getText().toString());
                mFunc = new MathFunction() {
                    @Override
                    public double function(double dNum1, double dNum2) {
                        return dNum1 - dNum2;
                    }
                };
                prepForClear = true;
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMath();
                dFirstNum = Double.parseDouble(tvDisplay.getText().toString());
                mFunc = new MathFunction() {
                    @Override
                    public double function(double dNum1, double dNum2) {
                        return dNum1 * dNum2;
                    }
                };
                prepForClear = true;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMath();
                dFirstNum = Double.parseDouble(tvDisplay.getText().toString());
                mFunc = new MathFunction() {
                    @Override
                    public double function(double dNum1, double dNum2) {
                        return dNum1 / dNum2;
                    }
                };
                prepForClear = true;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("0");
                mFunc = new MathFunction();
                prepForClear = true;
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dValue = Double.valueOf(tvDisplay.getText().toString()) / 100;
                tvDisplay.setText(stringifyNum(dValue));
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dValue = Double.valueOf(tvDisplay.getText().toString()) * -1;
                tvDisplay.setText(stringifyNum(dValue));
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvDisplay.getText().toString().contains(".")) {
                    tvDisplay.setText(tvDisplay.getText().toString() + ".");
                }
            }
        });
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMath();
                dFirstNum = Double.parseDouble(tvDisplay.getText().toString());
                mFunc = new MathFunction();
                prepForClear = true;
            }
        });


    }

    public String stringifyNum(double dNum){
        String sNum;
        if (dNum % 1 == 0) {
            sNum = String.valueOf(dNum);
            if (sNum.contains(".")){
                sNum = sNum.replace(".0","");
            }

        } else {
            sNum = String.valueOf(dNum);
        }
        return sNum;
    }
    public void doMath(){
        double dNum1 = dFirstNum;
        double dNum2 = Double.parseDouble(tvDisplay.getText().toString());
        Double dValue = mFunc.function(dNum1,dNum2);
        if (dValue % 1 == 0) {
            tvDisplay.setText(String.valueOf(dValue.intValue()));
        } else {
            tvDisplay.setText(String.valueOf(dValue));
        }
    }
}

class MathFunction {
    public double function(double dNum1, double dNum2){
        return dNum2;
    }
}