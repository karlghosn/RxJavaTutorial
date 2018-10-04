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

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import rx.Subscriber;
import rx.observables.MathObservable;

public class FragmentOperatorDetails extends Fragment {
    private static final String TAG = FragmentOperatorDetails.class.getSimpleName();
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_operators_details, container, false);
        TextView inputTv = rootView.findViewById(R.id.input);
        TextView outputTv = rootView.findViewById(R.id.output);
        StringBuilder inputBuilder = new StringBuilder();
        StringBuilder outputBuilder = new StringBuilder();

        if (position == 1)
            skip(inputTv, outputTv, inputBuilder, outputBuilder);
        else if (position == 2)
            skipLast(inputTv, outputTv, inputBuilder, outputBuilder);
        else if (position == 3)
            take(inputTv, outputTv, inputBuilder, outputBuilder);
        else if (position == 4)
            takeLast(inputTv, outputTv, inputBuilder, outputBuilder);
        else if (position == 5)
            distinct(inputTv, outputTv, outputBuilder);
        else if (position == 6)
            just(inputTv, outputTv, outputBuilder);
        else if (position == 7)
            from(inputTv, outputTv, outputBuilder);
        else if (position == 8)
            range(inputTv, outputTv, outputBuilder);
        else if (position == 9)
            repeat(inputTv, outputTv, outputBuilder);
        else if (position == 10)
            max(inputTv, outputTv, outputBuilder);
        else if (position == 11)
            min(inputTv, outputTv, outputBuilder);
        else if (position == 12)
            sum(inputTv, outputTv, outputBuilder);
        else if (position == 13)
            average(inputTv, outputTv, outputBuilder);
        else if (position == 14)
            count(inputTv, outputTv, outputBuilder);
        else if (position == 15)
            reduce(inputTv, outputTv, outputBuilder);
        else if (position == 16)
            buffer(inputTv, outputTv, outputBuilder);
        else if (position == 18)
            concat(inputTv, outputTv, outputBuilder);
        else if (position == 19)
            merge(inputTv, outputTv, outputBuilder);

        return rootView;
    }

    private void skip(TextView inputTv, final TextView outputTv, StringBuilder inputBuilder, final StringBuilder outputBuilder) {
        for (int i = 1; i <= 10; i++) {
            inputBuilder.append(String.valueOf(i)).append(", ");
        }
        inputTv.setText(inputBuilder.toString());
        Observable
                .range(1, 10)
                .skip(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        outputBuilder.append(String.valueOf(integer)).append(", ");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }

    private void skipLast(TextView inputTv, final TextView outputTv, StringBuilder inputBuilder, final StringBuilder outputBuilder) {
        for (int i = 1; i <= 10; i++) {
            inputBuilder.append(String.valueOf(i)).append(", ");
        }
        inputTv.setText(inputBuilder.toString());
        Observable
                .range(1, 10)
                .skipLast(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        outputBuilder.append(String.valueOf(integer)).append(", ");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }

    private void take(TextView inputTv, final TextView outputTv, StringBuilder inputBuilder, final StringBuilder outputBuilder) {
        for (int i = 1; i <= 10; i++) {
            inputBuilder.append(String.valueOf(i)).append(", ");
        }
        inputTv.setText(inputBuilder.toString());
        Observable
                .range(1, 10)
                .take(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        outputBuilder.append(String.valueOf(integer)).append(", ");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }

    private void takeLast(TextView inputTv, final TextView outputTv, StringBuilder inputBuilder, final StringBuilder outputBuilder) {
        for (int i = 1; i <= 10; i++) {
            inputBuilder.append(String.valueOf(i)).append(", ");
        }
        inputTv.setText(inputBuilder.toString());
        Observable
                .range(1, 10)
                .takeLast(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        outputBuilder.append(String.valueOf(integer)).append(", ");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }

    private void distinct(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("10, 10, 15, 20, 100, 200, 100, 300, 20, 100");
        Observable<Integer> numbersObservable = Observable.just(10, 10, 15, 20, 100, 200, 100, 300, 20, 100);
        numbersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
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
    }

    private void just(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
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
    }

    private void from(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        Observable.fromArray(numbers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
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
    }

    private void setOutputText(Integer integer, StringBuilder outputBuilder, TextView outputTv) {
        outputBuilder.append(String.valueOf(integer)).append(", ");
        outputTv.setText(outputBuilder.toString());
    }

    private void range(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Range from 1 to 10");
        Observable.range(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void repeat(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Repeat 3 times, range from 1 to 4");
        Observable
                .range(1, 4)
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }

    private void max(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("5, 101, 404, 22, 3, 1024, 65");
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};
        rx.Observable<Integer> observable = rx.Observable.from(numbers);

        MathObservable
                .max(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Max value: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }
                });
    }

    private void min(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("5, 101, 404, 22, 3, 1024, 65");
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};
        rx.Observable<Integer> observable = rx.Observable.from(numbers);

        MathObservable
                .min(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Min value: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }
                });
    }

    private void sum(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("5, 101, 404, 22, 3, 1024, 65");
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};
        rx.Observable<Integer> observable = rx.Observable.from(numbers);

        MathObservable
                .sumInteger(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Sum value: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }
                });
    }

    private void average(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("5, 101, 404, 22, 3, 1024, 65");
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};
        rx.Observable<Integer> observable = rx.Observable.from(numbers);

        MathObservable
                .averageInteger(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Average value: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
                    }
                });
    }

    private void count(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Mark, John, Trump, Obama, Lucy, Scarlett, April");
        getUsersObservable()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return user.getGender().equalsIgnoreCase("male");
                    }
                })
                .count()
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long count) {
                        Log.d(TAG, "Male users count: " + count);
                        setOutputText(count.intValue(), outputBuilder, outputTv);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private Observable<User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};
        String[] femaleUsers = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();

        for (String name : maleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }

        for (String name : femaleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

            users.add(user);
        }
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

    private void reduce(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Range 1 to 10");
        Observable
                .range(1, 10)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer number, Integer sum) {
                        return sum + number;
                    }
                })
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(TAG, "Sum of numbers from 1 - 10 is: " + integer);
                        setOutputText(integer, outputBuilder, outputTv);
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

    private void buffer(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("1, 2, 3, 4, 5, 6, 7, 8, 9");
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);

        integerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onNext");
                        outputBuilder.append("onNext").append(", ");
                        for (Integer integer : integers) {
                            Log.d(TAG, "Item: " + integer);
                            outputBuilder.append(String.valueOf(integer)).append(", ");
                        }
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All items emitted!");
                    }
                });
    }

    private void concat(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Concat both male and female names");
        Observable
                .concat(getMaleObservable(), getFemaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
                        outputBuilder.append(user.getName()).append(", ").append(user.getGender()).append("\n");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void merge(TextView inputTv, final TextView outputTv, final StringBuilder outputBuilder) {
        inputTv.setText("Merge both male and female names");
        Observable
                .merge(getMaleObservable(), getFemaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
                        outputBuilder.append(user.getName()).append(", ").append(user.getGender()).append("\n");
                        outputTv.setText(outputBuilder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<User> getFemaleObservable() {
        String[] names = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(1000);
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    private Observable<User> getMaleObservable() {
        String[] names = new String[]{"Mark", "John", "Trump", "Obama"};

        final List<User> users = new ArrayList<>();

        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(500);
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
