package com.example.rxjavatutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavatutorial.R;
import com.example.rxjavatutorial.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CustomDataActivity extends AppCompatActivity {
    /**
     * Basic Observable, Observer, Subscriber example
     * Introduced CompositeDisposable and DisposableObserver
     * The observable emits custom data type (Note) instead of primitive data types
     * ----
     * .map() operator is used to turn the note into all uppercase letters
     * ----
     * You can also notice we got rid of the below declarations
     * Observable<Note> notesObservable = getNotesObservable();
     * DisposableObserver<Note> notesObserver = getNotesObserver();
     */
    private static final String TAG = ObserversActivity.class.getSimpleName();

    private final CompositeDisposable disposable = new CompositeDisposable();
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

        resultTv = findViewById(R.id.result);
        completeTv = findViewById(R.id.complete);

        // add to Composite observable
        // .map() operator is used to turn the note into all uppercase letters
        disposable.add(getNotesObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Note, Note>() {
                    @Override
                    public Note apply(Note note) {
                        // Making the note to all uppercase
                        note.setNote(note.getNote().toUpperCase());
                        return note;
                    }
                })
                .subscribeWith(getNotesObserver()));
    }

    private DisposableObserver<Note> getNotesObserver() {
        return new DisposableObserver<Note>() {


            @Override
            public void onNext(Note note) {
                Log.d(TAG, "Note: " + note.getNote());
                stringBuilder.append("Name: ").append(note.getNote()).append("\n");
                resultTv.setText(stringBuilder.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All notes are emitted!");
                completeTv.setText(getString(R.string.all_notes_emitted));
            }
        };
    }

    private Observable<Note> getNotesObservable() {
        final List<Note> notes = prepareNotes();

        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) {
                for (Note note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "buy tooth paste!"));
        notes.add(new Note(2, "call brother!"));
        notes.add(new Note(3, "watch narcos tonight!"));
        notes.add(new Note(4, "pay power bill!"));

        return notes;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
