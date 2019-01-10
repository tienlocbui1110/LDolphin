package vn.edu.hcmus.ldolphin.utils;

import android.os.Handler;
import android.os.Looper;

public abstract class SimpleThreading {
    protected abstract void init();

    protected abstract void doInBackground();

    protected abstract void done();

    public static void run(SimpleThreading simpleThreading) {
        simpleThreading.init();
        Thread background = new Thread(simpleThreading.backgroundThread);
        background.start();
    }

    private Runnable backgroundThread = new Runnable() {
        @Override
        public void run() {
            doInBackground();
            runCallbackOnMainThread();
        }
    };

    private Runnable mainThread = new Runnable() {
        @Override
        public void run() {
            done();
        }
    };

    private void runCallbackOnMainThread() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(mainThread);
    }
}
