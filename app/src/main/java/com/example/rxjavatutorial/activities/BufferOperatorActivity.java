package com.example.rxjavatutorial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.rxjavatutorial.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class BufferOperatorActivity extends AppCompatActivity {
    private static final String TAG = BufferOperatorActivity.class.getSimpleName();

    private TextView txtTapResult;
    private TextView txtTapResultMax;

    private Disposable disposable;
    private int maxTaps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_operator);

        Button btnTapArea = findViewById(R.id.layout_tap_area);
        txtTapResult = findViewById(R.id.tap_result);
        txtTapResultMax = findViewById(R.id.tap_result_max_count);


        RxView.clicks(btnTapArea)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) {
                        return 1;
                    }
                })
                .buffer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.e(TAG, "onNext: " + integers.size() + " taps received!");
                        if (integers.size() > 0) {
                            maxTaps = integers.size() > maxTaps ? integers.size() : maxTaps;
                            txtTapResult.setText(getString(R.string.tap_number, integers.size()));
                            txtTapResultMax.setText(getString(R.string.tap_number_sessions, maxTaps));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
