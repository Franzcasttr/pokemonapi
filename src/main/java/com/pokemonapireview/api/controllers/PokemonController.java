package com.pokemonapireview.api.controllers;

import com.pokemonapireview.api.dto.PokemonDto;
import com.pokemonapireview.api.dto.PokemonResponse;
import com.pokemonapireview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getAllPokemon
            (@RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
             @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(pokemonService.getAllPokemons(pageNo, pageSize), HttpStatus.OK);

    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemon){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemon), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemon, @PathVariable("id") int id){
        return new ResponseEntity<>(pokemonService.updatePokemonById(pokemon, id), HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int id){
        pokemonService.deletePokemonById(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }
}
