package com.profegera.dogapi.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.profegera.dogapi.R
import com.profegera.dogapi.databinding.ActivityMainBinding
import com.profegera.dogapi.model.DogResponse
import com.profegera.dogapi.restclient.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDogImage()

        binding.imageView.setOnClickListener {
            loadDogImage()
        }
    }

    private fun loadDogImage() {
        RetrofitClient.instance.getRandomDogImage().enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val dogImageUrl = response.body()?.message
                    if (dogImageUrl != null) {
                        Picasso.get()
                            .load(dogImageUrl)
                            .into(binding.imageView)
                    }
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Log.e("API_ERROR", t.message.toString())
            }
        })
    }

}