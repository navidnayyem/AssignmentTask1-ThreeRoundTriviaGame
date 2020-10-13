package com.example.assignment_task1_threeroundtriviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtView, txtView2;
    private ListView listView;
    private Button btn;
    private EditText edTxt;
    private ArrayList<String> countries;
    private ArrayAdapter<String> CL;
    private ArrayList<String> temp;
    private HashMap<String, String> countries_and_capital;
    private String countryclick;
    private Timer t1;
    private Random random;
    private int range;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.txtView);
        txtView2 = findViewById(R.id.txtView2);
        listView = findViewById(R.id.listView);
        btn = findViewById(R.id.btn);
        edTxt = findViewById(R.id.edTxt);
        btn.setOnClickListener(this);

        countries_and_capital = new HashMap<String, String>();

        //Add Keys and Values (Country Name, City)
        countries_and_capital.put("Australia", "Canberra");
        countries_and_capital.put("Bangladesh", "Dhaka");
        countries_and_capital.put("Canada", "Ottawa");
        countries_and_capital.put("Denmark", "Copenhagen");
        countries_and_capital.put("Egypt", "Cairo");
        countries_and_capital.put("France", "Paris");
        countries_and_capital.put("Germany", "Berlin");
        countries_and_capital.put("Hungary", "Budapest");
        countries_and_capital.put("Italy", "Rome");
        countries_and_capital.put("Japan", "Tokyo");
        countries_and_capital.put("Kenya", "Nairobi");
        countries_and_capital.put("Lebanon", "Beirut");
        countries_and_capital.put("Malaysia", "Kuala Lumpur");
        countries_and_capital.put("Nigeria", "Abuja");
        countries_and_capital.put("Oman", "Muscat");
        countries_and_capital.put("Portugal", "Lisbon");
        countries_and_capital.put("Qatar", "Doha");
        countries_and_capital.put("Russia", "Moscow");
        countries_and_capital.put("Spain", "Madrid");
        countries_and_capital.put("Thailand", "Bangkok");
        countries_and_capital.put("United Kingdom", "London");
        countries_and_capital.put("Vietnam", "Hanoi");
        countries_and_capital.put("Zimbabwe", "Harare");

        countries = new ArrayList<String>();
        countries.add("Australia");
        countries.add("Bangladesh");
        countries.add("Canada");
        countries.add("Denmark");
        countries.add("Egypt");
        countries.add("France");
        countries.add("Germany");
        countries.add("Hungary");
        countries.add("Italy");
        countries.add("Japan");
        countries.add("Kenya");
        countries.add("Lebanon");
        countries.add("Malaysia");
        countries.add("Nigeria");
        countries.add("Oman");
        countries.add("Portugal");
        countries.add("Qatar");
        countries.add("Russia");
        countries.add("Spain");
        countries.add("Thailand");
        countries.add("United Kingdom");
        countries.add("Vietnam");
        countries.add("Zimbabwe");

        temp = new ArrayList<String>();

        for(int i=0;i<5;i++){
            random = new Random();
            range = countries.size() - 1;
            int x = random.nextInt((range - 0) + 0);
            temp.add(countries.get(x));
            countries.remove(x);
        }

        CL = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        listView.setAdapter(CL);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, temp.get(i) + " is selected.",Toast.LENGTH_LONG).show();
                countryclick = temp.get(i);
            }
        });

        t1 = new Timer();
        TimerTask tt1 = new TimerTask() {
            public int i = 0;

            @Override
            public void run() {
                i = i + 1;
                txtView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtView2.setText(i + "");
                    }
                }, 0);
                if (i == 20) {
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtView.setText("Round 2");
                            temp.clear();
                            for(int i=0;i<5;i++){
                                CL.notifyDataSetChanged();
                                random = new Random();
                                range = countries.size() - 1;
                                int x = random.nextInt((range - 0) + 0);
                                temp.add(countries.get(x));
                                countries.remove(x);
                            }
                        }
                    },0);
                }
                if (i == 40) {
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtView.setText("Round 3");
                            temp.clear();
                            for(int i=0;i<5;i++){
                                CL.notifyDataSetChanged();
                                random = new Random();
                                range = countries.size() - 1;
                                int x = random.nextInt((range - 0) + 0);
                                temp.add(countries.get(x));
                                countries.remove(x);
                            }
                        }
                    },0);
                }
                if (i == 60) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("scores", score);
                    startActivity(intent);
                    finish();
                }
            }
        };
        t1.schedule(tt1, 0, 1000);
    }

    @Override
    public void onClick(View view) {
        String country_capital = edTxt.getText().toString();

        if(country_capital.equalsIgnoreCase(countries_and_capital.get(countryclick))){
            Toast.makeText(MainActivity.this,"Country Capital Matched",Toast.LENGTH_LONG).show();
            temp.remove(countryclick);
            score = score + 2;
            CL.notifyDataSetChanged();
        } else {
            Toast.makeText(MainActivity.this,"Country Capital MisMatched",Toast.LENGTH_LONG).show();
        }
    }
}