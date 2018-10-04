package com.example.rxjavatutorial.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxjavatutorial.R;
import com.example.rxjavatutorial.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FragmentFilter extends Fragment {
    private static final String TAG = FragmentFilter.class.getSimpleName();
    private TextView outputTv;
    private TextView userOutputTv;
    private TextView userInputTv;
    private final StringBuilder inputBuilder = new StringBuilder();
    private final StringBuilder userInputBuilder = new StringBuilder();
    private final StringBuilder outputBuilder = new StringBuilder();
    private final StringBuilder userOutputBuilder = new StringBuilder();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        TextView inputTv = rootView.findViewById(R.id.input);
        outputTv = rootView.findViewById(R.id.output);
        userInputTv = rootView.findViewById(R.id.user_input);
        userOutputTv = rootView.findViewById(R.id.user_output);
        for (int i = 1; i <= 9; i++) {
            inputBuilder.append(String.valueOf(i)).append(", ");
        }
        inputTv.setText(inputBuilder.toString());
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "Even: " + integer);
                        outputBuilder.append(String.valueOf(integer)).append(", ");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //User
        Observable<User> userObservable = getUsersObservable();

        userObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return user.getGender().equalsIgnoreCase("female");
                    }
                })
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
                        userOutputBuilder.append(user.getName()).append(", ").append(user.getGender()).append("\n");
                        userOutputTv.setText(userOutputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return rootView;
    }

    private Observable<User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};
        String[] femaleUsers = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();

        for (String name : maleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("male");
            userInputBuilder.append(name).append(", ");
            users.add(user);
        }

        for (String name : femaleUsers) {
            User user = new User();
            user.setName(name);
            userInputBuilder.append(name).append(", ");
            user.setGender("female");
            users.add(user);
        }

        userInputTv.setText(userInputBuilder.toString());
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

}
