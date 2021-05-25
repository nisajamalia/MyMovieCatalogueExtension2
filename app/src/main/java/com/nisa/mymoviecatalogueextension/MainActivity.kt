package com.nisa.mymoviecatalogueextension

import android.app.Activity
import android.content.Intent
import barissaglam.client.movieapp.presentation.home.HomeViewModel
import com.nisa.mymoviecatalogueextension.base.view.BaseActivity
import com.nisa.mymoviecatalogueextension.databinding.ActivityMainBinding
import com.nisa.mymoviecatalogueextension.presentation.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_main
    override val classTypeOfViewModel: Class<HomeViewModel> = HomeViewModel::class.java


    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}