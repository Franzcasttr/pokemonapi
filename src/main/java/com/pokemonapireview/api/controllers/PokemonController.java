package com.pokemonapireview.api.controllers;

import com.pokemonapireview.api.dto.PokemonDto;
import com.pokemonapireview.api.models.Pokemon;
import com.pokemonapireview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonDto>> getAllPokemon(){
        return new ResponseEntity<>(pokemonService.getAllPokemons(), HttpStatus.OK);

    }

    @GetMapping("pokemon/{id}")
    public Pokemon getPokemon(@PathVariable("id") int id){
        return new Pokemon(id, "pikachu", "Electric");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemon){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemon), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<String> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int id){
        return ResponseEntity.ok("Successfully updated");
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int id){

        return ResponseEntity.ok("Successfully Deleted");
    }
}
