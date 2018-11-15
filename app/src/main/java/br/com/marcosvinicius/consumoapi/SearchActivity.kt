package br.com.marcosvinicius.consumoapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.marcosvinicius.consumoapi.api.PokemonAPI
import br.com.marcosvinicius.consumoapi.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        btSearch.setOnClickListener{
            serach()
        }
    }

    private fun serach() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val pokemonAPI = retrofit.create(PokemonAPI::class.java)

        pokemonAPI.search(
                inputNumeroPokemon.text.toString()
        ).enqueue(object : Callback<Pokemon>{
            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                Toast.makeText(
                        this@SearchActivity,
                        t?.message,
                        Toast.LENGTH_LONG)
                        .show()
            }

            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                if(response?.isSuccessful == true){
                    val pokemon = response.body()
                    txPokemon.text = pokemon?.name
                    Picasso.get().load(pokemon?.sprites?.frontDefault)
                            .placeholder(R.drawable.find)
                            .error(R.drawable.notf)
                            .into(ivPokemon);
                } else {
                    txPokemon.text = "Não Encontrado"

                    Picasso.get().load(R.drawable.notf)
                            .into(ivPokemon);
                }
            }
        })
    }
}
