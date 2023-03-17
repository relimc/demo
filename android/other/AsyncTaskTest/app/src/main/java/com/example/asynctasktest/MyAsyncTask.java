package com.example.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;

public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
    private final static String TAG = "MyAsyncTask";
    private TextView txt;
    private ProgressBar pgbar;

    public MyAsyncTask(TextView txt, ProgressBar pgbar) {
        super();
        this.txt = txt;
        this.pgbar = pgbar;
    }

    //该方法不运行在UI线程中,主要用于异步操作,通过调用publishProgress()方法
    //触发onProgressUpdate对UI进行操作
    @Override
    protected String doInBackground(Integer... params) {
        Log.d(TAG, Arrays.toString(params));
        DelayOperator dop = new DelayOperator();
        int i = 0;
        for (i = 10; i <= 100; i += 10) {
            dop.delay();
            publishProgress(i);
        }
        return i + params[0] + "";
    }

    // 该方法运行在UI线程中,可对UI控件进行设置
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        pgbar.setProgress(value);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, s);
    }
}
