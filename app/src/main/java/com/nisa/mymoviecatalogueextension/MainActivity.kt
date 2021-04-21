package com.nisa.mymoviecatalogueextension

import android.app.Activity
import android.content.Intent
import com.nisa.mymoviecatalogueextension.databinding.ActivityMainBinding
import com.nisa.mymoviecatalogueextension.presentation.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_main
    override val classTypeOfViewModel: Class<MainViewModel> = MainViewModel::class.java


    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}