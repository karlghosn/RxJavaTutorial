package com.example.rxjavatutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavatutorial.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DisposableActivity extends AppCompatActivity {
    /**
     * Basic Observable, Observer, Subscriber example
     * Observable emits list of animal names
     * You can see Disposable introduced in this example
     */
    private static final String TAG = DisposableActivity.class.getSimpleName();

    private Disposable disposable;
    private TextView subscribeTv;
    private TextView resultTv;
    private TextView completeTv;
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        getSupportActionBar().setTitle(title);

        subscribeTv = findViewById(R.id.subscribe);
        resultTv = findViewById(R.id.result);
        completeTv = findViewById(R.id.complete);

        // observable
        Observable<String> animalsObservable = getAnimalsObservable();

        // observer
        Observer<String> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable
        animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);
    }

    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
                subscribeTv.setText(getString(R.string.on_subscribe));
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
                stringBuilder.append("Name: ").append(s).append("\n");
                resultTv.setText(stringBuilder.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
                completeTv.setText(getString(R.string.all_items_emitted));
            }
        };
    }

    private Observable<String> getAnimalsObservable() {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // don't send events once the activity is destroyed
        disposable.dispose();

    }
}
