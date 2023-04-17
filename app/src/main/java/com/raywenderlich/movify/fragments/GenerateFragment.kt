package com.raywenderlich.movify.fragments

import android.os.Bundle
import kotlin.random.Random
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raywenderlich.movify.adaptor.MovieAdaptor
import com.raywenderlich.movify.api.ApiClient
import com.raywenderlich.movify.api.ApiService
import com.raywenderlich.movify.databinding.FragmentGenerateBinding
import com.raywenderlich.movify.response.MovieListResponse
import retrofit2.Call
import retrofit2.Response

const val TAG = "thisActivity"
class GenerateFragment : Fragment() {
    lateinit var binding: FragmentGenerateBinding
    private val movieAdaptor by lazy {
        MovieAdaptor()
    }
    private val api by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding =  FragmentGenerateBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            progressBar.visibility = View.VISIBLE
            val pagNumber =   Random.nextInt(0, 10)
            val callApiMovie = api.getPopularMovie(pagNumber )
            callApiMovie.enqueue(object :retrofit2.Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {


                    when(response.code()){

                        in 200..299 -> {
                            progressBar.visibility = View.GONE
                            response.body().let { body ->

                                body?.results.let { itData ->

                                    if (itData!!.isNotEmpty()){
                                        Log.d(TAG,"${itData}")

                                        movieAdaptor.differ.submitList(itData)
                                        recyclerView.apply {
                                            adapter = movieAdaptor
                                        }
                                    }

                                }
                            }
                        }
                        in 300..399 -> {}
                        in 400..499 -> {}
                        in 500..599 -> {}
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    Log.d(TAG, "you failed")
                }

            })
        }


    }



    companion object {
        fun newInstance(): GenerateFragment {
            return GenerateFragment()
        }
    }
}


