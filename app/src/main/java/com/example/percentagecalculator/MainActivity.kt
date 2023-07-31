package com.example.percentagecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadBannerAd()
        loadInterAd()


        val interAdBtn : Button = findViewById(R.id.calculatebtn)

        interAdBtn.setOnClickListener {
            showInterAd()
        }



        val En1:EditText = findViewById(R.id.En1)
        val En2:EditText = findViewById(R.id.En2)
        val En3:EditText = findViewById(R.id.En3)
        val En4:EditText = findViewById(R.id.En4)
        val En5:EditText = findViewById(R.id.En5)

        val calculatebtn:Button = findViewById(R.id.calculatebtn)
        val showresult:TextView = findViewById(R.id.showresult)


        calculatebtn.setOnClickListener {
            if (!En1.text.toString().equals("") && !En2.text.toString().equals("") && !En3.text.toString().equals("")
                && !En4.text.toString().equals("") && !En5.text.toString().equals("")) {

                val total = En1.text.toString().toDouble() + En2.text.toString()
                    .toDouble() + En3.text.toString().toDouble() +
                        En4.text.toString().toDouble() + En5.text.toString().toDouble()

                val result = (total/500)*100
                showresult.text = "Your total marks is : $total \n Your percentage is : $result"



            }else{
                     Toast.makeText(this,"Please enter value",Toast.LENGTH_SHORT).show()
            }
            
        }

    }

    private fun showInterAd() {
        if (mInterstitialAd!=null){

            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    startActivity(Intent(this@MainActivity,SecondActivity::class.java))
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }

            }

            mInterstitialAd?.show(this)

        }else{

            startActivity(Intent(this,SecondActivity::class.java))

        }
    }

    private fun loadInterAd() {

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })

    }



    private fun loadBannerAd() {

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        mAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Toast.makeText(this@MainActivity,"Ad Loader",Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.

                Toast.makeText(this@MainActivity,"Returned to the app",Toast.LENGTH_SHORT).show()
            }
        }



    }

    }


