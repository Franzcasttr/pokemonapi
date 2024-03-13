package com.pokemonapireview.api.service;

import com.pokemonapireview.api.dto.PokemonDto;
import com.pokemonapireview.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemons(int pageNo, int pageSize);

    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemonById(PokemonDto pokemonDto, int id);

    void deletePokemonById(int id);


}
