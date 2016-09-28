package com.mobidev.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * We are using the map operator.
         *
         * For the list of numbers we have (1-5), we multiply every odd number by 2 otherwise return
         * the number as it is.
         *
         * The expected result: 2,2,6,4,10
         */
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return (integer % 2 != 0) ? integer * 2 : integer;
                    }
                });

        subscription = observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Test", "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Test", "In onError()");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("Test", "In onNext():" + integer);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
