package github.evertonbrunosds.looqbox.controller;

import static github.evertonbrunosds.looqbox.util.Cache.MeasureTime.MINUTE;
import static github.evertonbrunosds.looqbox.util.Sort.Type.LENGTH;
import static java.lang.Integer.compare;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.service.PokemonSpiceService;
import github.evertonbrunosds.looqbox.util.Cache;
import github.evertonbrunosds.looqbox.util.Cache.MeasureTime;
import github.evertonbrunosds.looqbox.util.MergeSort;
import github.evertonbrunosds.looqbox.util.Sort;

@RestController
@RequestMapping("/pokemons")
public class PokemonSpiceController {

    private static final int TIME_INTERVAL = 1;

    private static final MeasureTime MEASURE_TIME = MINUTE;

    private final PokemonSpiceService service;

    private final Map<String, Cache> pokemonsCache;

    public PokemonSpiceController(final PokemonSpiceService service) {
        this.service = service;
        pokemonsCache = new HashMap<>();
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
        final List<PokemonSpiceResponse> result;
        result = getPokemonsCached(name, sort.getType(), sort.isReverse()).stream().map(pokemonSpice -> {
            final String highlight = hasText(name) ? pokemonSpice.replaceAll(name, "<pre>" + name + "<pre>") : null;
            return new PokemonSpiceResponse(pokemonSpice, highlight);
        }).collect(toList());
        return ResponseEntity.ok(new Response<>(result));
    }

    private List<String> getPokemonsCached(final String name, final Sort.Type sortType, final Boolean sortReverse) {
        final String newKey = name.toLowerCase().trim() + ";" + sortType.toString() + ";" + sortReverse.toString();
        return pokemonsCache.computeIfAbsent(newKey, key -> new Cache(TIME_INTERVAL, MEASURE_TIME, () -> {
            final List<String> result = hasText(name)
                    ? service.findByNameIgnoreCase(name).stream().map(PokemonSpice::getName).collect(toList())
                    : service.findAll().stream().map(PokemonSpice::getName).collect(toList());
            final MergeSort<String> mergeSort = new MergeSort<>(sortType.equals(LENGTH)
                    ? (paramOne, paramTwo) -> compare(paramOne.length(), paramTwo.length())
                    : String::compareTo);
            mergeSort.sort(result, sortReverse);
            return result;
        })).<List<String>>getData().orGet(emptyList()).stream().collect(toList());
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
