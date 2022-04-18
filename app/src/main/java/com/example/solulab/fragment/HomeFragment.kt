package com.example.solulab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solulab.R
import com.example.solulab.adapter.CoinListAdapter
import com.example.solulab.data.RetrofitService
import com.example.solulab.model.CoinListResponse
import com.example.solulab.repository.CoinListRepository
import com.example.solulab.viewmodel.CoinViewModel
import com.example.solulab.viewmodelfactory.CoinViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinListResponse: CoinListResponse
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var factory: CoinViewModelFactory

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

      /*  val binding  = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root*/
       val view =  inflater.inflate(R.layout.home_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_coin_list)
        progressBar = view.findViewById(R.id.progress_home)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = RetrofitService.getInstance()
        val repository = CoinListRepository(api)

        factory = CoinViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(CoinViewModel::class.java)

        viewModel.coinList.observe(viewLifecycleOwner, Observer { coinList ->
            recyclerView.also {
                it.layoutManager = GridLayoutManager(context,3)
                it.setHasFixedSize(true)
                it.adapter = CoinListAdapter(coinList.data.list)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it,Toast.LENGTH_LONG).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
               progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.getAllCoinsData()
    }
}