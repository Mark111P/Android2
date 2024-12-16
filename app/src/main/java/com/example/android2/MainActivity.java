package com.example.android2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.task1);
//        setContentView(task2());
        setContentView(R.layout.task3);
    }

    @SuppressLint("SetTextI18n")
    public void buttonClick(View view) {
        String text = (String) view.getTooltipText();
        TextView textView = findViewById(R.id.result);
        if("+-*/0".contains(text)) {
            if (textView.getText().length() == 0) {
                return;
            }
            Pattern pattern = Pattern.compile("[-+*/]$");
            Matcher matcher = pattern.matcher(textView.getText());
            if (matcher.find()) {
                return;
            }
        }
        textView.setText(textView.getText() + text);
    }

    public void clearClick(View view) {
        TextView textView = findViewById(R.id.result);
        int end = textView.getText().length() - 1;
        if (end >= 0) {
            textView.setText(textView.getText().subSequence(0, end));
        }
    }

    public void resultClick(View view) {
        try {
            TextView textView = findViewById(R.id.result);
            Expression expression = new ExpressionBuilder(textView.getText().toString()).build();
            String result = Integer.toString((int) expression.evaluate());
            textView.setText(result);
        }
        catch (Exception ignored) {}
    }

    @SuppressLint("SetTextI18n")
    public GridLayout task2() {
        GridLayout layout = new GridLayout(this);
        layout.setLayoutParams(new GridLayout.LayoutParams());
        layout.setRowCount(2);
        layout.setColumnCount(1);
        layout.setLayoutParams(new android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));

        for (int i : new int[]{0, 1}) {
            String color = i == 0 ? "#AAFFAA" : "#FFAAAA";
            GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
            params1.rowSpec = GridLayout.spec(i, 1f);
            params1.setGravity(Gravity.FILL);
            GridLayout l1 = new GridLayout(this);
            l1.setLayoutParams(params1);
            l1.setBackgroundColor(Color.parseColor(color));

            GridLayout.LayoutParams params2 = new GridLayout.LayoutParams();
            params2.setGravity(Gravity.CENTER);
            GridLayout l2 = new GridLayout(this);
            l2.setLayoutParams(params2);
            l2.setRowCount(2);
            l2.setColumnCount(2);

            for (int j = 1; j <= 4; j++) {
                int x = (j - 1) % 2;
                int y = (j - 1) / 2;
                Button button = new Button(this);
                GridLayout.LayoutParams buttonParams = new GridLayout.LayoutParams();
                buttonParams.rowSpec = GridLayout.spec(x, 1f);
                buttonParams.columnSpec = GridLayout.spec(y, 1f);
                button.setLayoutParams(buttonParams);
                button.setText(Integer.toString(j));
                l2.addView(button);
            }
            l1.addView(l2);
            layout.addView(l1);
        }
        return layout;
    }

    public void checkColor(View view) {
        StringBuilder color1 = new StringBuilder("#");
        StringBuilder color2 = new StringBuilder("#");
        for (var x : new CheckBox[]{findViewById(R.id.colorsCheckRed), findViewById(R.id.colorsCheckGreen), findViewById(R.id.colorsCheckBlue)}) {
            if (x.isChecked()) {
                color1.append("FF");
                color2.append("00");
            }
            else {
                color1.append("00");
                color2.append("FF");
            }
        }
        findViewById(R.id.task3).setBackgroundColor(Color.parseColor(color1.toString()));
        ((TextView)findViewById(R.id.colorsTextMain)).setTextColor(Color.parseColor(color2.toString()));
        ((TextView)findViewById(R.id.colorsTextRed)).setTextColor(Color.parseColor(color2.toString()));
        ((TextView)findViewById(R.id.colorsTextGreen)).setTextColor(Color.parseColor(color2.toString()));
        ((TextView)findViewById(R.id.colorsTextBlue)).setTextColor(Color.parseColor(color2.toString()));
    }
}