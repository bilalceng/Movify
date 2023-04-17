package com.raywenderlich.movify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raywenderlich.movify.fragments.FavouriteFragment
import com.raywenderlich.movify.fragments.GenerateFragment
import com.raywenderlich.movify.fragments.WatchedFragment

class IntentActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var generateFragment: GenerateFragment
    private lateinit var watchedFragment: WatchedFragment
    private lateinit var favouriteFragment: FavouriteFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        generateFragment = GenerateFragment.newInstance()
        watchedFragment = WatchedFragment.newInstance()
        favouriteFragment = FavouriteFragment.newInstance()

        beginTransactionForFirstView()
        navigationControl()



    }

    private fun beginTransactionForFirstView(){
        supportFragmentManager.beginTransaction().replace(R.id.container, generateFragment).commitNow()
    }

    private fun navigationControl(){
        bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.generate -> { supportFragmentManager.beginTransaction()
                    .replace(R.id.container,generateFragment).commitNow()
                    true}

                R.id.favourites -> { supportFragmentManager.beginTransaction()
                    .replace(R.id.container, favouriteFragment).commitNow()
                true}

                R.id.recordings -> { supportFragmentManager.beginTransaction()
                    .replace(R.id.container, watchedFragment).commitNow()
                true}

                else -> false
            }
        }
    }



}