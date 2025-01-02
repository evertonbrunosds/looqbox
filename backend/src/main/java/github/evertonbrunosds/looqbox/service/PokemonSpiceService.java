package github.evertonbrunosds.looqbox.service;

import static github.evertonbrunosds.looqbox.util.Cache.MeasureTime.MINUTE;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.repository.PokemonSpiceRepository;
import github.evertonbrunosds.looqbox.util.Cache;
import github.evertonbrunosds.looqbox.util.Cache.MeasureTime;

@Service
public class PokemonSpiceService {

    private static final int TIME_INTERVAL = 1;

    private static final MeasureTime MEASURE_TIME = MINUTE;

    private final Cache findAll;

    private final Map<String, Cache> findByNameIgnoreCase;

    public PokemonSpiceService(final PokemonSpiceRepository repository) {
        findAll =  new Cache(TIME_INTERVAL, MEASURE_TIME, repository::findAll);
        findByNameIgnoreCase = new HashMap<>();
    }

    public List<PokemonSpice> findAll() {
        return findAll.<List<PokemonSpice>>getData().orGet(emptyList()).stream().collect(toList());
    }

    public List<PokemonSpice> findByNameIgnoreCase(final String name) {
        return hasText(name)
                ? findByNameIgnoreCase.computeIfAbsent(name.toLowerCase(), key -> {
                    return new Cache(TIME_INTERVAL, MEASURE_TIME, () -> {
                        return findAll().stream().filter(pokemonSpice -> {
                            return pokemonSpice.getName().toLowerCase().contains(name.toLowerCase());
                        }).collect(toList());
                    });
                }).<List<PokemonSpice>>getData().orGet(emptyList())
                : emptyList();
    }

}
