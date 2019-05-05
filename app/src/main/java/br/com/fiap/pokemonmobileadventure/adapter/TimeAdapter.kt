package br.com.fiap.pokemonmobileadventure.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.model.Time

class TimeAdapter(val timeList: ArrayList<Time>) : RecyclerView.Adapter<TimeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.row_time, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val time: Time = timeList[p1]
        p0.txtTeamName?.text = time.timeNome
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTeamName = itemView.findViewById(R.id.row_time_nome) as TextView
    }
}