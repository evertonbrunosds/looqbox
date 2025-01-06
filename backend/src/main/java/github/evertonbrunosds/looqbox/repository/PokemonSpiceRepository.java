package github.evertonbrunosds.looqbox.repository;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import github.evertonbrunosds.looqbox.model.PokemonSpice;

@FunctionalInterface
public interface PokemonSpiceRepository {

    public List<PokemonSpice> findAll();

    default List<PokemonSpice> findByNameIgnoreCase(final String name) {
        return hasText(name)
                ? findAll().stream().filter(pokemonSpice -> {
                    return pokemonSpice.getName().toLowerCase().contains(name.toLowerCase());
                }).collect(toList())
                : emptyList();
    }

}
