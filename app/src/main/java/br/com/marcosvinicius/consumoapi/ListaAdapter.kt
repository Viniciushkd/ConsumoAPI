package br.com.marcosvinicius.consumoapi

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import br.com.marcosvinicius.consumoapi.model.Pokemon
import kotlinx.android.synthetic.main.activity_search.view.*

class ListaAdapter(
        val pokemons: List<Pokemon>,
        val context: Context,
        val listener: (Pokemon) -> Unit): RecyclerView.Adapter<ListaAdapter.ViewRolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewRolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_item, parent, false)
        return ViewRolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewRolder, position: Int) {
        val pokemon = pokemons[position]
        holder?.let {
            it.bindView(pokemon, listener)
        }
    }

    class ViewRolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: Pokemon,
                     listener: (Pokemon) -> Unit) = with(itemView){
            itemView.txPokemon.text = pokemon.name
            setOnClickListener {
                listener(pokemon)
            }
        }
    }
}