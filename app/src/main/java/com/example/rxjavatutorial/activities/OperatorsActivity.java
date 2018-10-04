package com.example.rxjavatutorial.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rxjavatutorial.R;
import com.example.rxjavatutorial.fragments.FragmentOperators;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.operators_container, new FragmentOperators())
                .commit();
    }
}
