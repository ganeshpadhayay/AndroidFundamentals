package com.ganesh.androidfundamentals.multithreading.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_sample_async_task_activity.*
import java.lang.ref.WeakReference

class SampleAsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_async_task_activity)
    }

    fun startAsyncTask(view: View) {
        val exampleAsyncTask = ExampleAsyncTask(this)
        exampleAsyncTask.execute(10)
    }


    //if this class is inner class then it will have the reference to activity and when activity gets destroyed it will still have the reference
    //of this activity and it will leak memory
    //so we can make it an static inner class but in that case we won't be able to access the UI element(outer elements), comes weak referencing
    //we can also use callback mechanism(pass the listener via AsyncTask constructor and get callbacks) here but weak reference is also a fine solution
    private class ExampleAsyncTask(sampleAsyncTaskActivity: SampleAsyncTaskActivity) :
        AsyncTask<Int, Int, String>() {

        //main point is to keep the weak reference in the class scope but stroing reference in the local scope
        private var activityWeakReference: WeakReference<SampleAsyncTaskActivity> =
            WeakReference<SampleAsyncTaskActivity>(sampleAsyncTaskActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            //this is a strong reference but since the scope is limited to the local function only, it should be fine
            val sampleAsyncTaskActivity: SampleAsyncTaskActivity? = activityWeakReference.get()
            if (sampleAsyncTaskActivity == null || sampleAsyncTaskActivity.isFinishing) {
                return
            }
            sampleAsyncTaskActivity.progress_bar?.visibility = View.VISIBLE
        }

        //only this method runs in background
        override fun doInBackground(vararg params: Int?): String {
            params[0]?.let {
                for (i in 1..it) {
                    publishProgress((i * 100) / it)
                    Thread.sleep(1000)      //or SystemClock.sleep(1000)
                }
            }
            return "Finished!"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            //this is a strong reference but since the scope is limited to the local function only, it should be fine
            val sampleAsyncTaskActivity: SampleAsyncTaskActivity? = activityWeakReference.get()
            if (sampleAsyncTaskActivity == null || sampleAsyncTaskActivity.isFinishing) {
                return
            }
            values[0]?.let { sampleAsyncTaskActivity.progress_bar?.progress = it }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            //this is a strong reference but since the scope is limited to the local function only, it should be fine
            val sampleAsyncTaskActivity: SampleAsyncTaskActivity? = activityWeakReference.get()
            if (sampleAsyncTaskActivity == null || sampleAsyncTaskActivity.isFinishing) {
                return
            }
            sampleAsyncTaskActivity.progress_bar?.progress = 0
            sampleAsyncTaskActivity.progress_bar?.visibility = View.INVISIBLE
            Toast.makeText(sampleAsyncTaskActivity, "" + result, Toast.LENGTH_SHORT).show()
        }
    }
}