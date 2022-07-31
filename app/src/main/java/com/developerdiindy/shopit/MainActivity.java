package com.developerdiindy.shopit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.developerdiindy.shopit.business.models.Rate;
import com.developerdiindy.shopit.database.RatesDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_main)
    public TextView tv;

    private RatesDataSource mDataSource;
    private List<Rate> mValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValues = new ArrayList<>();

        ButterKnife.bind(this);

        mDataSource = new RatesDataSource(this);
        mDataSource.open();
    }

    @OnClick(R.id.btn_add)
    public void add(){
        String[] rates = new String[]{"JPY", "AUD", "BGN"};
        int nextInt = new Random().nextInt(3);
        Random randomNo = new Random();
        // save the new Rate to the database
        Rate rate = mDataSource.createRate(rates[nextInt], randomNo.nextDouble());
        System.out.println("Coin On Main = " + rate.getCoin());
        mValues.add(rate);
    }

    @OnClick(R.id.btn_show)
    public void show(){
        String text = "";
        for(Rate r: mValues){
            text = text + r.getCoin() + "\n";
        }
        tv.setText(text);

    }

    @Override
    protected void onResume() {
        mDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mDataSource.close();
        super.onPause();
    }
}