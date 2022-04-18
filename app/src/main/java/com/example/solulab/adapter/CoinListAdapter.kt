package com.example.solulab.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.solulab.databinding.AdapterCoinListBinding
import com.example.solulab.model.CoinDetails
import com.example.solulab.util.ValidationUtil

class CoinListAdapter(var coinList:List<CoinDetails>) : RecyclerView.Adapter<MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCoinListBinding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val coinListDetail = coinList[position]
        if (ValidationUtil.validateCoin(coinListDetail)) {
            holder.binding.textCoinName.text = coinListDetail.name
          Glide.with(holder.itemView.context).load(coinListDetail?.pictures?.front?.url).into(holder.binding.imageCoin)
        }
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}

class MainViewHolder(val binding: AdapterCoinListBinding):RecyclerView.ViewHolder(binding.root){

}