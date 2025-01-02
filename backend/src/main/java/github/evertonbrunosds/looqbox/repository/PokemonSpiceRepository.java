package github.evertonbrunosds.looqbox.repository;

import java.util.List;

import github.evertonbrunosds.looqbox.model.PokemonSpice;

@FunctionalInterface
public interface PokemonSpiceRepository {

    public List<PokemonSpice> findAll();

}
