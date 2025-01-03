package github.evertonbrunosds.looqbox.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration.SimpleHttpResponse;
import github.evertonbrunosds.looqbox.repository.PokemonSpiceRepository;

@Configuration
@Profile("test")
public class PokemonSpiceRepositoryConfigurationOnTest {

    @Bean
    public PokemonSpiceRepository pokemonSpiceRepository() throws Exception {
        final PokemonSpiceRepositoryConfiguration configuration;
        configuration = new PokemonSpiceRepositoryConfiguration((url) -> new SimpleHttpResponse(200, successBody()));
        return configuration.pokemonSpiceRepository();
    }

    private static final String successBody() {
        return """
                {
                    "count": 1025,
                    "next": null,
                    "previous": null,
                    "results": [
                        {
                            "name": "bulbasaur",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/1/"
                        },
                        {
                            "name": "ivysaur",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/2/"
                        },
                        {
                            "name": "venusaur",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/3/"
                        },
                        {
                            "name": "charmander",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/4/"
                        },
                        {
                            "name": "charmeleon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/5/"
                        },
                        {
                            "name": "charizard",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/6/"
                        },
                        {
                            "name": "squirtle",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/7/"
                        },
                        {
                            "name": "wartortle",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/8/"
                        },
                        {
                            "name": "blastoise",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/9/"
                        }
                    ]
                }
            """;
    }

}
