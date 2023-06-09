package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class SubjectActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        //asyncSubjectDemo1();
        //asyncSubjectDemo2();
        behaviourSubjectDemo2();
    }

    void behaviourSubjectDemo1(){
        Observable<String> observable = Observable.just("Java","Kotlin","Xml","JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());
    }

    void behaviourSubjectDemo2(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());
    }
    void asyncSubjectDemo2(){

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOTLIN");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("JSON");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());
    }

    void asyncSubjectDemo1(){
        Observable<String> observable = Observable.just("Java","Kotlin","Xml","JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());
    }



    private Observer<String> getFirstObserver(){
        return new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "First Observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "First Observer Received " + s );
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "First Observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "First Observer onComplete");
            }
        };
    }


    private Observer<String> getSecondObserver(){
        return new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Second Observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "Second Observer Received " + s );
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "Second Observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Second Observer onComplete");
            }
        };
    }


    private Observer<String> getThirdObserver(){
        return new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Third Observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "Third Observer Received " + s );
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "Third Observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Third Observer onComplete");
            }
        };
    }
}