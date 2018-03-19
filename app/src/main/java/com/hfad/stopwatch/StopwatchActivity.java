// This stopwatch supports screen rotation
// The stopwatch stops counting when it is invisible.
// If the stopwatch was counting right before being invisible, it resumes counting when being visible again.

package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunningBeforeStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("tag", "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();
    }

    @Override
    protected void onRestart() {
        Log.i("tag", "onRestart() called");
        super.onRestart();
        if (wasRunningBeforeStop) {
            running = true;
        }
    }

    @Override
    protected void onStart() {
        Log.i("tag", "onStart() called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("tag", "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("tag", "onPause() called");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i("tag", "onSaveInstanceState() called");
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    @Override
    protected void onStop() {
        Log.i("tag", "onStop() called");
        super.onStop();
        wasRunningBeforeStop = running;
        running = false;
    }

    @Override
    protected void onDestroy() {
        Log.i("tag", "onDestroy() called");
        super.onDestroy();
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);


        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);
                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }
}
