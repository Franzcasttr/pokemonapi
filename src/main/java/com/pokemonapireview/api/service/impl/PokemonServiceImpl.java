package com.pokemonapireview.api.service.impl;

import com.pokemonapireview.api.dto.PokemonDto;
import com.pokemonapireview.api.models.Pokemon;
import com.pokemonapireview.api.repository.PokemonRepository;
import com.pokemonapireview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemon.setId(newPokemon.getId());
        pokemon.setName(newPokemon.getName());
        pokemon.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public List<PokemonDto> getAllPokemons() {
    List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemon(int id) {

//        PokemonDto pokemon = pokemonRepository.findById(id);

        return null;
    }


    private  PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return  pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return  pokemon;
    }
}
