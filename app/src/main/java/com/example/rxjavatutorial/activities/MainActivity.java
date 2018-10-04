package com.example.rxjavatutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxjavatutorial.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView examplesLv = findViewById(R.id.examples_list);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.examples_list, android.R.layout.simple_list_item_1);
        examplesLv.setAdapter(typeAdapter);

        examplesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = (String) adapterView.getItemAtPosition(i);
                if (i == 0) {
                    Intent intent = new Intent(MainActivity.this, BasicObservableActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else if (i == 1) {
                    Intent intent = new Intent(MainActivity.this, DisposableActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else if (i == 2) {
                    Intent intent = new Intent(MainActivity.this, OperatorActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else if (i == 3) {
                    Intent intent = new Intent(MainActivity.this, ObserversActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else if (i == 4) {
                    Intent intent = new Intent(MainActivity.this, CustomDataActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else if (i == 5) {
                    Intent intent = new Intent(MainActivity.this, OperatorsActivity.class);
                    intent.putExtra("title", title);
                    startActivity(intent);
                }
            }
        });
    }
}
