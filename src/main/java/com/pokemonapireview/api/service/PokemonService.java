package com.pokemonapireview.api.service;

import com.pokemonapireview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemons();

    PokemonDto getPokemon(int id);
}
