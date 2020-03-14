package pk.edu.pucit.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;
import org.w3c.dom.Text;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void makeEqHelper(String ch , TextView tv){
        String eq = tv.getText().toString();
        // Following "switch" statement checks that if an arithmetic operator is last character then if user press the
        // same arithmetic operator again then do not add it in equation and
        // if press any different operator then replace it with previous character.
        if(eq.length() > 0) {
            String lastCh = eq.substring(eq.length() - 1);
            switch (ch) {
                case "+":
                    if (lastCh.equals("x") || lastCh.equals("-") || lastCh.equals("/") || lastCh.equals("%"))
                        eq = eq.substring(0, eq.length() - 1) + ch;
                    else if (!"+".equals(lastCh))
                        eq += ch;
                    tv.setText(eq);
                    break;
                case "-":
                    if (lastCh.equals("x") || lastCh.equals("+") || lastCh.equals("/") || lastCh.equals("%"))
                        eq = eq.substring(0, eq.length() - 1) + ch;
                    else if (!"-".equals(lastCh))
                        eq += ch;
                    tv.setText(eq);
                    break;
                case "x":
                    if (lastCh.equals("+") || lastCh.equals("-") || lastCh.equals("/") || lastCh.equals("%"))
                        eq = eq.substring(0, eq.length() - 1) + ch;
                    else if (!"x".equals(lastCh))
                        eq += ch;
                    tv.setText(eq);
                    break;
                case "/":
                    if (lastCh.equals("x") || lastCh.equals("-") || lastCh.equals("+") || lastCh.equals("%"))
                        eq = eq.substring(0, eq.length() - 1) + ch;
                    else if (!"/".equals(lastCh))
                        eq += ch;
                    tv.setText(eq);
                    break;
                case "%":
                    if (lastCh.equals("x") || lastCh.equals("-") || lastCh.equals("/") || lastCh.equals("+"))
                        eq = eq.substring(0, eq.length() - 1) + ch;
                    else if (!"%".equals(lastCh))
                        eq += ch;
                    tv.setText(eq);
                    break;
                default:
                    eq += ch;
                    tv.setText(eq);
                    break;
            }
        }
        else {
            eq += ch;
            tv.setText(eq);
        }
        eq = null;
    }
    public void makeEq(View view){
        TextView tv = findViewById(R.id.tv_equation);
        String eq = null;
        int id = view.getId();
        switch (view.getId()) {
            case R.id.multiply:
                //Log.i("Id of Button pressed", "multiply");
                eq = tv.getText().toString();
                // this "x" symbol can not be written on blank screen
                if(!eq.isEmpty())
                    makeEqHelper("x" , tv);
                break;
            case R.id.addition:
                //Log.i("Id of Button pressed", "addition");
                makeEqHelper("+", tv);
                break;
            case R.id.subtract:
                //Log.i("Id of Button pressed", "subtraction");
                makeEqHelper("-", tv);
                break;
            case R.id.division:
                //Log.i("Id of Button pressed", "division");
                eq = tv.getText().toString();
                // this "/" symbol can not be written on blank screen
                if(!eq.isEmpty())
                    makeEqHelper("/", tv);
                break;
            case R.id.percentage:
                //Log.i("Id of Button pressed", "percentage");
                makeEqHelper("%", tv);
                break;
            case R.id.one:
                //Log.i("Id of Button pressed", "one");
                makeEqHelper("1", tv);
                break;
            case R.id.two:
                //Log.i("Id of Button pressed", "two");
                makeEqHelper("2", tv);
                break;
            case R.id.three:
                //Log.i("Id of Button pressed", "three");
                makeEqHelper("3", tv);
                break;
            case R.id.four:
                //Log.i("Id of Button pressed", "four");
                makeEqHelper("4", tv);
                break;
            case R.id.five:
                //Log.i("Id of Button pressed", "five");
                makeEqHelper("5", tv);
                break;
            case R.id.six:
                //Log.i("Id of Button pressed", "six");
                makeEqHelper("6", tv);
                break;
            case R.id.seven:
                //Log.i("Id of Button pressed", "seven");
                makeEqHelper("7", tv);
                break;
            case R.id.eight:
                //Log.i("Id of Button pressed", "eight");
                makeEqHelper("8", tv);
                break;
            case R.id.nine:
                //Log.i("Id of Button pressed", "nine");
                makeEqHelper("9", tv);
                break;
            case R.id.zero:
                //Log.i("Id of Button pressed", "zero");
                makeEqHelper("0", tv);
                break;
            case R.id.dbZero:
                //Log.i("Id of Button pressed", "dbzero");
                makeEqHelper("00", tv);
                break;
            case R.id.clearScreen:
                //Log.i("Id of Button pressed", "clearScreen");
                tv = findViewById(R.id.tv_equation);
                tv.setText("");
                TextView res = findViewById(R.id.tv_result);
                res.setText("0");
                break;
            case R.id.deleteChar:
                //Log.i("Id of Button pressed", "addition");
                tv = findViewById(R.id.tv_equation);
                eq = tv.getText().toString();
                // deleting last character of string
                if ((eq != null) && (eq.length() > 0)) {
                    eq = eq.substring(0, eq.length() - 1);
                }
                tv.setText(eq);
                eq = null;
                break;
            case R.id.dot:
                //Log.i("Id of Button pressed","dot");
                if(!isDotFound(tv))
                    makeEqHelper(".", tv);
                break;
            case R.id.tv_calculate:
                calculateEq();
                break;
        }
    }
    public boolean isDotFound(TextView tv){
        tv = findViewById(R.id.tv_equation);
        String eq = tv.getText().toString();
        if(eq.length()>0){
            int i = eq.length()-1;
            char[] tokens = eq.toCharArray();
            boolean isDot = false , isArthOp = false; // isArthOp : isArithmeticOperator
            while(i>=0 && !isArthOp && !isDot){
                if(tokens[i] == '.')
                    isDot = true;
                else if(tokens[i] == '+' || tokens[i] == '/' ||tokens[i] == 'x' || tokens[i] == '-' || tokens[i] == '%')
                    isArthOp = true;
                i--;
            }
            return isDot;
        }
        return false;
    }
    public void calculateEq(){
        TextView tv;
        String eq = null;
        //Log.i("Id of Button pressed", "equal");
        tv = findViewById(R.id.tv_equation);
        eq = tv.getText().toString();
        //Log.i("equation", eq);
        // replacing "x" with "*"
        eq = eq.replaceAll("x", "\\*");
        if(eq.charAt(eq.length()-1) == '%')
            eq = eq.replace("%", "/100");
        else
            eq = eq.replace("%", "/100*");

        //Log.i("correct equation", eq);
        Expression exp = new Expression(eq);
        String result = String.valueOf(exp.calculate());
        // removing ".0" form last of the answer if answer is an integer
        if (result.length() > 2) {
            String lastTwoDigits = result.substring(result.length() - 2);
            if(lastTwoDigits.equals(".0")) {
                result = result.substring(0, result.length() - 2);
            }
        }
        //Log.i("result", result);
        if(result.equals("NaN"))
            result = "Error";
        TextView res = findViewById(R.id.tv_result);
        res.setText(result);
        eq = null;
    }
}