package com.aleangelozi.aws_amplify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Todo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("Tutorial", "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", failure)
        }

        Amplify.DataStore.observe(Todo::class.java,
            { Log.i("Tutorial", "Observation began.") },
            { Log.i("Tutorial", it.item().toString()) },
            { Log.e("Tutorial", "Observation failed.", it) },
            { Log.i("Tutorial", "Observation complete.") }
        )
    }
}