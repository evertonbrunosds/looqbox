package github.evertonbrunosds.looqbox.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration;
import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration.SimpleHttpResponse;
import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration.SimpleRequestMaker;

public class PokemonSpiceRepositoryTest {

    private static final String successBody() {
        return """
                {
                    "count": 2,
                    "next": "null",
                    "previous": null,
                    "results": [
                        {
                            "name": "bulbasaur",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/1/"
                        },
                        {
                            "name": "ivysaur",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/2/"
                        }
                    ]
                }
                """;
    }

    @Test
    public void findAllOnStatusCode200() throws Exception {
        final int statusCode = 200; // SUCCESS
        final SimpleRequestMaker simpleRequestMaker = (url) -> new SimpleHttpResponse(statusCode, successBody());
        final PokemonSpiceRepositoryConfiguration configuration = new PokemonSpiceRepositoryConfiguration(simpleRequestMaker);
        final PokemonSpiceRepository repository = configuration.pokemonSpiceRepository();
        assertEquals(2, repository.findAll().size());
        assertEquals("bulbasaur", repository.findAll().get(0).getName());
        assertEquals("ivysaur", repository.findAll().get(1).getName());
        assertEquals("https://pokeapi.co/api/v2/pokemon-species/1/", repository.findAll().get(0).getUrl());
        assertEquals("https://pokeapi.co/api/v2/pokemon-species/2/", repository.findAll().get(1).getUrl());
    }

    @Test
    public void findAllOnStatusCode500() throws Exception {
        final int statusCode = 500; // INTERNAL_SERVER_ERROR
        final SimpleRequestMaker simpleRequestMaker = (url) -> new SimpleHttpResponse(statusCode, successBody());
        final PokemonSpiceRepositoryConfiguration configuration = new PokemonSpiceRepositoryConfiguration(simpleRequestMaker);
        final PokemonSpiceRepository repository = configuration.pokemonSpiceRepository();
        try {
            repository.findAll();
            fail("most throw 'InvalidParameterException'");
        } catch (final InvalidParameterException exception) {
            assertEquals("source of query unavailable for 'PokemonSpice'", exception.getMessage());
        } catch (final Exception exception) {
            fail("most can not throw other exception");
        }
    }

    @Test
    public void findAllOnStatusCode200WithBlankBody() throws Exception {
        final int statusCode = 200; // SUCCESS
        final SimpleRequestMaker simpleRequestMaker = (url) -> new SimpleHttpResponse(statusCode, blankSuccessBody());
        final PokemonSpiceRepositoryConfiguration configuration = new PokemonSpiceRepositoryConfiguration(simpleRequestMaker);
        final PokemonSpiceRepository repository = configuration.pokemonSpiceRepository();
        try {
            repository.findAll();
            fail("most throw 'InvalidParameterException'");
        } catch (final InvalidParameterException exception) {
            assertEquals("source of query unavailable for 'PokemonSpice'", exception.getMessage());
        } catch (final Exception exception) {
            fail("most can not throw other exception");
        }
    }

    private static final String blankSuccessBody() {
        return "";
    }

}
