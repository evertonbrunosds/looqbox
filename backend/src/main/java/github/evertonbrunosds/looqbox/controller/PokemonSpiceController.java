package github.evertonbrunosds.looqbox.controller;

import static github.evertonbrunosds.looqbox.util.Cache.TimeMeasure.MINUTE;
import static github.evertonbrunosds.looqbox.util.Sort.Type.LENGTH;
import static java.lang.Integer.compare;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.service.PokemonSpiceService;
import github.evertonbrunosds.looqbox.util.Cache;
import github.evertonbrunosds.looqbox.util.Cache.TimeMeasure;
import github.evertonbrunosds.looqbox.util.MergeSort;
import github.evertonbrunosds.looqbox.util.Sort;

@RestController
@RequestMapping("/pokemons")
public class PokemonSpiceController {

    private final int timeInterval;

    private final TimeMeasure timeMeasure;

    private final PokemonSpiceService service;

    private final Map<String, Cache<List<String>>> pokemonsCache;

    private final Map<String, Cache<List<PokemonSpiceResponse>>> pokemonsHighlightCache;

    public PokemonSpiceController(final PokemonSpiceService service, final Environment environment) {
        timeInterval = environment.getProperty("cache.time.interval.controller", Integer.class,15);
        timeMeasure = environment.getProperty("cache.time.measure.controller", TimeMeasure.class, MINUTE);
        this.service = service;
        pokemonsCache = new HashMap<>();
        pokemonsHighlightCache = new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<Response<String>> getPokemons(

            @RequestParam(name = "name", defaultValue = "") final String name,
            @RequestParam(name = "sort", defaultValue = "ASC_ALPHABET") final Sort sort

    ) {
        final List<String> result = getPokemonsCached(name, sort.getType(), sort.isReverse());
        return ResponseEntity.ok(new Response<>(result));
    }

    @GetMapping("/highlight")
    public ResponseEntity<Response<PokemonSpiceResponse>> getPokemonsWithHighlight(

            @RequestParam(name = "name", defaultValue = "") final String name,
            @RequestParam(name = "sort", defaultValue = "ASC_ALPHABET") final Sort sort

    ) {
        final String newKey = name.toLowerCase().trim() + ";" + sort.toString();
        final List<PokemonSpiceResponse> result;
        result = pokemonsHighlightCache.computeIfAbsent(newKey, key -> new Cache<>(timeInterval, timeMeasure, () -> {
            return getPokemonsCached(name, sort.getType(), sort.isReverse()).stream().map(pokemonSpice -> {
                final String highlight = hasText(name) ? pokemonSpice.replaceAll(name, "<pre>" + name + "<pre>") : null;
                return new PokemonSpiceResponse(pokemonSpice, highlight);
            }).collect(toList());
        })).getData().or(emptyList());
        return ResponseEntity.ok(new Response<>(result));
    }

    private List<String> getPokemonsCached(final String name, final Sort.Type sortType, final Boolean sortReverse) {
        final String newKey = name.toLowerCase().trim() + ";" + sortType.toString() + ";" + sortReverse.toString();
        return pokemonsCache.computeIfAbsent(newKey, key -> new Cache<>(timeInterval, timeMeasure, () -> {
            final List<String> result = hasText(name)
                    ? service.findByNameIgnoreCase(name).stream().map(PokemonSpice::getName).collect(toList())
                    : service.findAll().stream().map(PokemonSpice::getName).collect(toList());
            final MergeSort<String> mergeSort = new MergeSort<>(sortType.equals(LENGTH)
                    ? (paramOne, paramTwo) -> compare(paramOne.length(), paramTwo.length())
                    : String::compareTo);
            mergeSort.sort(result, sortReverse);
            return result;
        })).getData().or(emptyList()).stream().collect(toList());
    }

    public class Response<T> {
        private final List<T> result;

        public Response(final List<T> result) {
            this.result = result;
        }

        public List<T> getResult() {
            return result;
        }

    }

    public class PokemonSpiceResponse {
        private final String name;
        private final String highlight;

        public PokemonSpiceResponse(final String name, final String highlight) {
            this.name = name;
            this.highlight = highlight;
        }

        public String getName() {
            return name;
        }

        public String getHighlight() {
            return highlight;
        }

    }

}
