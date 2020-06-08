package com.ganesh.androidfundamentals.coroutines.job

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_coroutine_as_a_job.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CoroutineAsAJobActivity : AppCompatActivity() {

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000

    //it is a subclass of job and has some extra methods like complete() and completeExceptionally(), these mark the job as complete
    //using this we can handle when we want to mark the job complete and not let the coroutine mark it complete to handle cancellation and
    //timeout scenarios.
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_as_a_job)

        job_button?.setOnClickListener {
            if (!::job.isInitialized) {
                initJob()
            }
            //usage of extension function
            job_progress_bar?.startJobOrCancel(job)
        }
    }

    /***
     * this is an extension function meaning when called it will execute on the progress bar
     */
    private fun ProgressBar.startJobOrCancel(job: Job) {
        if (this.progress > 0) {
            println("$job is already active. Cancelling...")
            resetJob()
        } else {
            job_button?.text = "Cancel Job #1"

//            val scope =  CoroutineScope(IO).launch {
//                //...
//            }
//            scope.cancel()          //this will cancel all the jobs started on IO so we need to specify the jobs also

            //this creates an independent scope in the background for this job only, on cancellation of this scope only this job will be cancelled
            //so whenever these is any need to cancellation we should use IO + job combination otherwise it would kill all the jobs
            //cancel this particular job using job.cancel()
            CoroutineScope(IO + job).launch {
                println("Coroutine $this is activated with this job $job")

                for (i in PROGRESS_START..PROGRESS_MAX) {
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    this@startJobOrCancel.progress = i
                }
                updateJobCompleteTextView("Job is complete")
            }

        }
    }

    //doesn't matter from where it gets called, it will update the text on UI thread only
    private fun updateJobCompleteTextView(text: String) {
        GlobalScope.launch(Main) {
            job_complete_text?.text = text
        }
    }

    private fun resetJob() {
        if (job.isActive || job.isCompleted) {
            job.cancel(CancellationException("Resetting Job"))      //this cancellable exception will be caught in job.invokeOnCompletion()
        }

        //we need to initialise it again as we can't use the same job after we cancel it so we will be creating a new job
        initJob()
    }

    private fun initJob() {
        job_button?.text = "Start Job #1"
        updateJobCompleteTextView("")

        //setup the job
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if (msg.isNullOrBlank()) {
                    msg = "Unknown Cancellation Error"
                }
                println("$job was cancelled. Reason is $msg")
                showToast(msg)
            }
        }

        //setup the progress bar parameters
        job_progress_bar?.max = PROGRESS_MAX
        job_progress_bar?.progress = PROGRESS_START
    }

    //we need to make sure that the code inside this method executed on the main thread even if this method gets called from a coroutine or job
    private fun showToast(text: String) {
        GlobalScope.launch(Main) {
            Toast.makeText(this@CoroutineAsAJobActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}