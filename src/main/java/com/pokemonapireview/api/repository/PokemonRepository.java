package com.pokemonapireview.api.repository;

import com.pokemonapireview.api.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {


}
