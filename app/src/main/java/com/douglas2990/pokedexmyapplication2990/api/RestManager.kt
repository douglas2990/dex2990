package com.douglas2990.pokedexmyapplication2990.api

import androidx.viewbinding.BuildConfig

import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolutionChain
import com.douglas2990.pokedexmyapplication2990.model.modelSpecies.PokemonSpecies
import com.douglas2990.pokedexmyapplication2990.model.moves.Moves
import com.douglas2990.pokedexmyapplication2990.model.moves.MovesDetail
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon
import com.douglas2990.pokedexmyapplication2990.model.responses.PokemonList
import com.douglas2990.pokedexmyapplication2990.model.responses.Result
import com.douglas2990.pokedexmyapplication2990.model.type.AllTypes
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.DetailType
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class RestManager {
    companion object {

        private fun createHttpClient(): OkHttpClient.Builder{

            val client = OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG){
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logInterceptor)
            }
            return client
        }

        fun getEndpoints(): IEndpoints {
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createHttpClient().build())
                .build()
            return retrofit.create(IEndpoints::class.java)
        }


        interface IEndpoints{

            @GET("evolution-chain")
            fun evolution_chain(
                @Query("limit")limit: Int = 468,
                @Query("offset")offset: Int = 0
            ): Call<Result>

            @GET("generation")
            fun generation(
            ): Call<Result>

            @GET("item")
            fun item(
                @Query("limit")limit: Int = 955,
                @Query("offset")offset: Int = 0
            ): Call<Result>


            @GET("language")
            fun language(
            ): Call<Result>


            @GET("move")
            fun move(
                @Query("limit")limit: Int = 844,
                @Query("offset")offset: Int = 0
            ): Call<Result>

            @GET("pokemon")
            fun pokemon(
                @Query("limit")limit: Int = 1300,
                @Query("offset")offset: Int = 0
            ): Call<Result>


            @GET("region")
            fun region(
                //@Path("id") id : String
            ): Call<Result>

            @GET("type")
            fun type(
                @Query("limit")limit: Int = 25,
                @Query("offset")offset: Int = 0
            ): Call<Result>

            @GET("version")
            fun version(
                @Query("limit")limit: Int = 40,
                @Query("offset")offset: Int = 0
            ): Call<Result>

            @GET("pokemon/{id}")
            fun pokemonDittoDetail(@Path("id") id : String): Call<Pokemon>

            @GET("pokemon")
            fun getPokemon(
                @Query("limit")limit: Int,
                @Query("offset")offset: Int)
                    : Call<PokemonList>

            @GET("pokemon-species/{id}")
            fun getPokemonSpecies(@Path("id") id : String)
            :Call<PokemonSpecies>

            @GET("evolution-chain/{id}")
            fun getPokemonEvolution(@Path("id") id : String)
            :Call<EvolutionChain>

            @GET("evolution-chain/{id}")
            fun getPokemonEstagioEvolution(@Path("id") id : String)
                    :Call<EvolutionChain>

            @GET("move")
            fun getAllMoves(
                @Query("limit")limit: Int,
                @Query("offset")offset: Int)
                    : Call<Moves>

            @GET("move/{id}")
            fun getAllMovesDetail(@Path("id") id : String): Call<MovesDetail>

            @GET("type")
            fun getAllType(
                @Query("limit")limit: Int,
                @Query("offset")offset: Int
            ): Call<AllTypes>

            @GET("type/{id}")
            fun getTypeDetail(
                @Path("id") id : String
            ):Call<DetailType>

            @GET("type/fire")
            fun getTypeFire():Call<DetailType>

            @GET("type/normal")
            fun getTypeNormal():Call<DetailType>

            @GET("type/fighting")
            fun getTypeFighting():Call<DetailType>

            @GET("type/flying")
            fun getTypeflying():Call<DetailType>

            @GET("type/poison")
            fun getTypePoison():Call<DetailType>

            @GET("type/ground")
            fun getTypeGround():Call<DetailType>

            @GET("type/rock")
            fun getTypeRock():Call<DetailType>

            @GET("type/bug")
            fun getTypeBug():Call<DetailType>

            @GET("type/ghost")
            fun getTypeGhost():Call<DetailType>

            @GET("type/steel")
            fun getTypeSteel():Call<DetailType>

            @GET("type/water")
            fun getTypeWater():Call<DetailType>

            @GET("type/grass")
            fun getTypeGrass():Call<DetailType>

            @GET("type/electric")
            fun getTypeElectric():Call<DetailType>

            @GET("type/psychic")
            fun getTypePsychic():Call<DetailType>

            @GET("type/ice")
            fun getTypeIce():Call<DetailType>

            @GET("type/dragon")
            fun getTypeDragon():Call<DetailType>

            @GET("type/dark")
            fun getTypeDark():Call<DetailType>

            @GET("type/fairy")
            fun getTypeFary():Call<DetailType>

        }
    }
}