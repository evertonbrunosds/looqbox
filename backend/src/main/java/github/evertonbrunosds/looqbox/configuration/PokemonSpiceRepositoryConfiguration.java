package github.evertonbrunosds.looqbox.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.repository.PokemonSpiceRepository;
import github.evertonbrunosds.looqbox.util.Pagination;

@Configuration
@Profile("main")
public class PokemonSpiceRepositoryConfiguration {

    private static final String path = "https://pokeapi.co/api/v2/pokemon-species";

    private final SimpleRequestMaker simpleRequestMaker;

    public PokemonSpiceRepositoryConfiguration(final SimpleRequestMaker simpleRequestMaker) {
        this.simpleRequestMaker = simpleRequestMaker;
    }

    public PokemonSpiceRepositoryConfiguration() {
        simpleRequestMaker = (url) -> {
            final HttpClient httpClient = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder().GET().uri(new URI(url)).build();
            final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            return new SimpleHttpResponse(response.statusCode(), response.body());
        };
    }

    @Bean
    public PokemonSpiceRepository pokemonSpiceRepository() throws URISyntaxException {
        return () -> {
            try {
                final int maxLimit = pokemonSpicePagination(path).getCount();
                return pokemonSpicePagination(path + "?offset=0&limit=" + maxLimit).getResults();
            } catch (final Exception exception) {
                throw new InvalidParameterException("source of query unavailable for 'PokemonSpice'");
            }
        };
    }

    private PokemonSpicePagination pokemonSpicePagination(final String url) throws Exception {
        final SimpleHttpResponse response = simpleRequestMaker.request(url);
        if (response.getStatusCode() != HttpStatus.OK.value()) {
            throw new InvalidParameterException();
        }
        final Gson gson = new Gson();
        return gson.fromJson(response.getBody(), PokemonSpicePagination.class);
    }

    @FunctionalInterface
    public static interface SimpleRequestMaker {

        public SimpleHttpResponse request(final String url) throws Exception;

    }

    public static final class SimpleHttpResponse {

        private final int statusCode;
        private final String body;

        public SimpleHttpResponse(final int statusCode, final String body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getBody() {
            return body;
        }

    }

    public static class PokemonSpicePagination extends Pagination {

        private List<PokemonSpice> results;

        public List<PokemonSpice> getResults() {
            return results;
        }

        public void setResults(final List<PokemonSpice> results) {
            this.results = results;
        }

    }

}
