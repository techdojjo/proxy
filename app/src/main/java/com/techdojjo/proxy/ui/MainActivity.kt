package com.techdojjo.proxy.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.techdojjo.proxy.R
import com.techdojjo.proxy.data.RemoteGitHubSource
import com.techdojjo.proxy.data.model.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        RemoteGitHubSource().getUser("dpott197")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<User> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull user: User) {
                        updateUser(user)
                    }

                    override fun onError(@NonNull e: Throwable) {
                        Log.e(TAG, "getUsers() failed", e)
                    }

                    override fun onComplete() {

                    }
                })
    }

    fun updateUser(user: User) {
        urlTextView.setText(user.url)
    }

}
