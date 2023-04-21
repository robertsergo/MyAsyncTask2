package com.example.myasynctask2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView output;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        output = findViewById(R.id.textView);
        output.setMovementMethod( new ScrollingMovementMethod());

        pb = findViewById(R.id.haythem);
        pb.setVisibility(View.INVISIBLE);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_do_task) {
            //Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show();
            MyTask task = new MyTask();
            //task.execute("Param 1", "Param 2", "Param 3","Param 4", "Param 5", "Param 6");
            // task.executeOnExecutor();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"Param 1", "Param 2",
                    "Param 3","Param 4", "Param 5", "Param 6");

        }

        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_do_task) {
//            //Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show();
//            MyTask task = new MyTask();
//            //task.execute("Param 1", "Param 2", "Param 3","Param 4", "Param 5", "Param 6");
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Param 1", "Param 2", "Param 3","Param 4", "Param 5", "Param 6");
//        }
//
//        return true;
//    }



//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_do_task) {
//            Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show();
//        }
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_do_task) {
//            Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show();
//        }
//        return true;
//    }


    protected void updateDisplay(String message) {
        output.append(message + "\n");
    }

        private class MyTask extends AsyncTask<String,String,String> {
            @Override
            protected void onPreExecute() {
                updateDisplay("Starting Task");
                pb.setVisibility(View.VISIBLE);
            }


//            @Override
//            protected String doInBackground(String... strings) {
//                return null;
//            }


            @Override
            protected void onPostExecute(String s) {
                updateDisplay("Ending Task");
                pb.setVisibility(View.INVISIBLE);
            }



            @Override
            protected String doInBackground(String... strings) {
                for (int i=0;i<strings.length;i++)
                {
                    publishProgress("Working with"+strings[i]);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Task complete";
            }

            @Override
            protected void onProgressUpdate(String... values) {
                updateDisplay(values[0]);
            }


        }

    }