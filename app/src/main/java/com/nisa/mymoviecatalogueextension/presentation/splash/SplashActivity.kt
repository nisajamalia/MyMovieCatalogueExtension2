package com.nisa.mymoviecatalogueextension.presentation.splash

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import com.nisa.mymoviecatalogueextension.MainActivity
import com.nisa.mymoviecatalogueextension.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupAnimationOfViews()
        handler.postDelayed({
            MainActivity.start(this)
            supportFinishAfterTransition()
        }, 5000)
    }

    private fun setupAnimationOfViews() {
        imageViewForeground.post{
            ObjectAnimator.ofFloat(imageViewForeground, "y", imageViewForeground.height.toFloat(), 0f).apply {
                duration = 1000
                interpolator = OvershootInterpolator()
                start()
            }
        }

        img_logo.post{
            val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.3f, 1.0f)
            val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.3f, 1.0f)
            ObjectAnimator.ofPropertyValuesHolder(img_logo, scaleX,scaleY).apply {
                duration = 1000
                startDelay = 750L
                start()
                doOnEnd { (img_logo.drawable as AnimationDrawable).start() }

            }
        }

        text.post{
            ObjectAnimator.ofFloat(text, "alpha", 0f,1f).apply {
                duration = 1250
                startDelay = 1750L
                interpolator = DecelerateInterpolator(1.2f)
                start()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
        (img_logo.drawable as AnimationDrawable).stop()
    }


}