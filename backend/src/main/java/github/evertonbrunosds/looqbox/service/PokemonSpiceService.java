package github.evertonbrunosds.looqbox.service;

import static github.evertonbrunosds.looqbox.util.Cache.TimeMeasure.HOUR;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.repository.PokemonSpiceRepository;
import github.evertonbrunosds.looqbox.util.Cache;
import github.evertonbrunosds.looqbox.util.Cache.TimeMeasure;

@Service
public class PokemonSpiceService {

    private final PokemonSpiceRepository repository;

    public PokemonSpiceService(final PokemonSpiceRepository repository, final Environment environment) {
        final int timeInterval = environment.getProperty("cache.time.interval.service", Integer.class,5);
        final TimeMeasure timeMeasure = environment.getProperty("cache.time.measure.service", TimeMeasure.class, HOUR);
        final Cache cache = new Cache(timeInterval, timeMeasure, repository::findAll);
        this.repository = () -> cache.<List<PokemonSpice>>getData().orGet(emptyList()).stream().collect(toList());
    }

    public List<PokemonSpice> findAll() {
        return repository.findAll();
    }

    public List<PokemonSpice> findByNameIgnoreCase(final String name) {
        return hasText(name)
                ? repository.findAll().stream().filter(pokemonSpice -> {
                    return pokemonSpice.getName().toLowerCase().contains(name.toLowerCase());
                }).collect(toList())
                : emptyList();
    }

}
