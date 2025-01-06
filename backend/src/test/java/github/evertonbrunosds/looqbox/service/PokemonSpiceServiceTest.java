package github.evertonbrunosds.looqbox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration;
import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration.SimpleHttpResponse;
import github.evertonbrunosds.looqbox.configuration.PokemonSpiceRepositoryConfiguration.SimpleRequestMaker;
import github.evertonbrunosds.looqbox.model.PokemonSpice;
import github.evertonbrunosds.looqbox.repository.PokemonSpiceRepository;

public class PokemonSpiceServiceTest {

    @Test
    public void findAll() throws Exception {
        final PokemonSpiceService service = pokemonSpiceService();
        assertEquals(210, service.findAll().size());
    }

    @Test
    public void findByNameFound() throws Exception {
        final PokemonSpiceService service = pokemonSpiceService();
        final List<PokemonSpice> result = service.findByNameIgnoreCase("bul");
        assertEquals(3, result.size());
        assertEquals("bulbasaur", result.get(0).getName());
        assertEquals("snubbull", result.get(1).getName());
        assertEquals("granbull", result.get(2).getName());
    }

    @Test
    public void findByNameNotFound() throws Exception {
        final PokemonSpiceService service = pokemonSpiceService();
        final List<PokemonSpice> result = service.findByNameIgnoreCase("bruno");
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    public void findByNameNullName() throws Exception {
        final PokemonSpiceService service = pokemonSpiceService();
        final List<PokemonSpice> result = service.findByNameIgnoreCase(null);
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    public void findByNameBlankName() throws Exception {
        final PokemonSpiceService service = pokemonSpiceService();
        final List<PokemonSpice> result = service.findByNameIgnoreCase("    ");
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    private static final PokemonSpiceService pokemonSpiceService() throws Exception {
        final int statusCode = 200; // SUCCESS
        final SimpleRequestMaker simpleRequestMaker = (url) -> new SimpleHttpResponse(statusCode, successBody());
        final PokemonSpiceRepositoryConfiguration configuration = new PokemonSpiceRepositoryConfiguration(simpleRequestMaker);
        final PokemonSpiceRepository repository = configuration.pokemonSpiceRepository();
        final PokemonSpiceService service = new PokemonSpiceService(repository, environment());
        return service;
    }

    private static final String successBody() {
        return """
                {
                    "count": 210,
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
                        },
                        {
                            "name": "caterpie",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/10/"
                        },
                        {
                            "name": "metapod",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/11/"
                        },
                        {
                            "name": "butterfree",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/12/"
                        },
                        {
                            "name": "weedle",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/13/"
                        },
                        {
                            "name": "kakuna",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/14/"
                        },
                        {
                            "name": "beedrill",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/15/"
                        },
                        {
                            "name": "pidgey",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/16/"
                        },
                        {
                            "name": "pidgeotto",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/17/"
                        },
                        {
                            "name": "pidgeot",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/18/"
                        },
                        {
                            "name": "rattata",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/19/"
                        },
                        {
                            "name": "raticate",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/20/"
                        },
                        {
                            "name": "spearow",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/21/"
                        },
                        {
                            "name": "fearow",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/22/"
                        },
                        {
                            "name": "ekans",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/23/"
                        },
                        {
                            "name": "arbok",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/24/"
                        },
                        {
                            "name": "pikachu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/25/"
                        },
                        {
                            "name": "raichu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/26/"
                        },
                        {
                            "name": "sandshrew",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/27/"
                        },
                        {
                            "name": "sandslash",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/28/"
                        },
                        {
                            "name": "nidoran-f",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/29/"
                        },
                        {
                            "name": "nidorina",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/30/"
                        },
                        {
                            "name": "nidoqueen",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/31/"
                        },
                        {
                            "name": "nidoran-m",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/32/"
                        },
                        {
                            "name": "nidorino",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/33/"
                        },
                        {
                            "name": "nidoking",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/34/"
                        },
                        {
                            "name": "clefairy",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/35/"
                        },
                        {
                            "name": "clefable",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/36/"
                        },
                        {
                            "name": "vulpix",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/37/"
                        },
                        {
                            "name": "ninetales",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/38/"
                        },
                        {
                            "name": "jigglypuff",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/39/"
                        },
                        {
                            "name": "wigglytuff",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/40/"
                        },
                        {
                            "name": "zubat",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/41/"
                        },
                        {
                            "name": "golbat",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/42/"
                        },
                        {
                            "name": "oddish",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/43/"
                        },
                        {
                            "name": "gloom",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/44/"
                        },
                        {
                            "name": "vileplume",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/45/"
                        },
                        {
                            "name": "paras",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/46/"
                        },
                        {
                            "name": "parasect",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/47/"
                        },
                        {
                            "name": "venonat",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/48/"
                        },
                        {
                            "name": "venomoth",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/49/"
                        },
                        {
                            "name": "diglett",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/50/"
                        },
                        {
                            "name": "dugtrio",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/51/"
                        },
                        {
                            "name": "meowth",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/52/"
                        },
                        {
                            "name": "persian",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/53/"
                        },
                        {
                            "name": "psyduck",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/54/"
                        },
                        {
                            "name": "golduck",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/55/"
                        },
                        {
                            "name": "mankey",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/56/"
                        },
                        {
                            "name": "primeape",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/57/"
                        },
                        {
                            "name": "growlithe",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/58/"
                        },
                        {
                            "name": "arcanine",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/59/"
                        },
                        {
                            "name": "poliwag",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/60/"
                        },
                        {
                            "name": "poliwhirl",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/61/"
                        },
                        {
                            "name": "poliwrath",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/62/"
                        },
                        {
                            "name": "abra",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/63/"
                        },
                        {
                            "name": "kadabra",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/64/"
                        },
                        {
                            "name": "alakazam",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/65/"
                        },
                        {
                            "name": "machop",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/66/"
                        },
                        {
                            "name": "machoke",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/67/"
                        },
                        {
                            "name": "machamp",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/68/"
                        },
                        {
                            "name": "bellsprout",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/69/"
                        },
                        {
                            "name": "weepinbell",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/70/"
                        },
                        {
                            "name": "victreebel",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/71/"
                        },
                        {
                            "name": "tentacool",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/72/"
                        },
                        {
                            "name": "tentacruel",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/73/"
                        },
                        {
                            "name": "geodude",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/74/"
                        },
                        {
                            "name": "graveler",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/75/"
                        },
                        {
                            "name": "golem",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/76/"
                        },
                        {
                            "name": "ponyta",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/77/"
                        },
                        {
                            "name": "rapidash",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/78/"
                        },
                        {
                            "name": "slowpoke",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/79/"
                        },
                        {
                            "name": "slowbro",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/80/"
                        },
                        {
                            "name": "magnemite",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/81/"
                        },
                        {
                            "name": "magneton",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/82/"
                        },
                        {
                            "name": "farfetchd",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/83/"
                        },
                        {
                            "name": "doduo",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/84/"
                        },
                        {
                            "name": "dodrio",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/85/"
                        },
                        {
                            "name": "seel",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/86/"
                        },
                        {
                            "name": "dewgong",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/87/"
                        },
                        {
                            "name": "grimer",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/88/"
                        },
                        {
                            "name": "muk",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/89/"
                        },
                        {
                            "name": "shellder",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/90/"
                        },
                        {
                            "name": "cloyster",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/91/"
                        },
                        {
                            "name": "gastly",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/92/"
                        },
                        {
                            "name": "haunter",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/93/"
                        },
                        {
                            "name": "gengar",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/94/"
                        },
                        {
                            "name": "onix",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/95/"
                        },
                        {
                            "name": "drowzee",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/96/"
                        },
                        {
                            "name": "hypno",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/97/"
                        },
                        {
                            "name": "krabby",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/98/"
                        },
                        {
                            "name": "kingler",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/99/"
                        },
                        {
                            "name": "voltorb",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/100/"
                        },
                        {
                            "name": "electrode",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/101/"
                        },
                        {
                            "name": "exeggcute",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/102/"
                        },
                        {
                            "name": "exeggutor",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/103/"
                        },
                        {
                            "name": "cubone",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/104/"
                        },
                        {
                            "name": "marowak",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/105/"
                        },
                        {
                            "name": "hitmonlee",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/106/"
                        },
                        {
                            "name": "hitmonchan",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/107/"
                        },
                        {
                            "name": "lickitung",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/108/"
                        },
                        {
                            "name": "koffing",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/109/"
                        },
                        {
                            "name": "weezing",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/110/"
                        },
                        {
                            "name": "rhyhorn",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/111/"
                        },
                        {
                            "name": "rhydon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/112/"
                        },
                        {
                            "name": "chansey",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/113/"
                        },
                        {
                            "name": "tangela",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/114/"
                        },
                        {
                            "name": "kangaskhan",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/115/"
                        },
                        {
                            "name": "horsea",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/116/"
                        },
                        {
                            "name": "seadra",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/117/"
                        },
                        {
                            "name": "goldeen",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/118/"
                        },
                        {
                            "name": "seaking",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/119/"
                        },
                        {
                            "name": "staryu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/120/"
                        },
                        {
                            "name": "starmie",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/121/"
                        },
                        {
                            "name": "mr-mime",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/122/"
                        },
                        {
                            "name": "scyther",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/123/"
                        },
                        {
                            "name": "jynx",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/124/"
                        },
                        {
                            "name": "electabuzz",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/125/"
                        },
                        {
                            "name": "magmar",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/126/"
                        },
                        {
                            "name": "pinsir",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/127/"
                        },
                        {
                            "name": "tauros",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/128/"
                        },
                        {
                            "name": "magikarp",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/129/"
                        },
                        {
                            "name": "gyarados",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/130/"
                        },
                        {
                            "name": "lapras",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/131/"
                        },
                        {
                            "name": "ditto",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/132/"
                        },
                        {
                            "name": "eevee",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/133/"
                        },
                        {
                            "name": "vaporeon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/134/"
                        },
                        {
                            "name": "jolteon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/135/"
                        },
                        {
                            "name": "flareon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/136/"
                        },
                        {
                            "name": "porygon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/137/"
                        },
                        {
                            "name": "omanyte",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/138/"
                        },
                        {
                            "name": "omastar",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/139/"
                        },
                        {
                            "name": "kabuto",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/140/"
                        },
                        {
                            "name": "kabutops",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/141/"
                        },
                        {
                            "name": "aerodactyl",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/142/"
                        },
                        {
                            "name": "snorlax",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/143/"
                        },
                        {
                            "name": "articuno",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/144/"
                        },
                        {
                            "name": "zapdos",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/145/"
                        },
                        {
                            "name": "moltres",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/146/"
                        },
                        {
                            "name": "dratini",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/147/"
                        },
                        {
                            "name": "dragonair",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/148/"
                        },
                        {
                            "name": "dragonite",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/149/"
                        },
                        {
                            "name": "mewtwo",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/150/"
                        },
                        {
                            "name": "mew",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/151/"
                        },
                        {
                            "name": "chikorita",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/152/"
                        },
                        {
                            "name": "bayleef",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/153/"
                        },
                        {
                            "name": "meganium",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/154/"
                        },
                        {
                            "name": "cyndaquil",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/155/"
                        },
                        {
                            "name": "quilava",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/156/"
                        },
                        {
                            "name": "typhlosion",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/157/"
                        },
                        {
                            "name": "totodile",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/158/"
                        },
                        {
                            "name": "croconaw",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/159/"
                        },
                        {
                            "name": "feraligatr",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/160/"
                        },
                        {
                            "name": "sentret",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/161/"
                        },
                        {
                            "name": "furret",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/162/"
                        },
                        {
                            "name": "hoothoot",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/163/"
                        },
                        {
                            "name": "noctowl",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/164/"
                        },
                        {
                            "name": "ledyba",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/165/"
                        },
                        {
                            "name": "ledian",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/166/"
                        },
                        {
                            "name": "spinarak",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/167/"
                        },
                        {
                            "name": "ariados",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/168/"
                        },
                        {
                            "name": "crobat",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/169/"
                        },
                        {
                            "name": "chinchou",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/170/"
                        },
                        {
                            "name": "lanturn",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/171/"
                        },
                        {
                            "name": "pichu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/172/"
                        },
                        {
                            "name": "cleffa",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/173/"
                        },
                        {
                            "name": "igglybuff",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/174/"
                        },
                        {
                            "name": "togepi",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/175/"
                        },
                        {
                            "name": "togetic",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/176/"
                        },
                        {
                            "name": "natu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/177/"
                        },
                        {
                            "name": "xatu",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/178/"
                        },
                        {
                            "name": "mareep",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/179/"
                        },
                        {
                            "name": "flaaffy",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/180/"
                        },
                        {
                            "name": "ampharos",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/181/"
                        },
                        {
                            "name": "bellossom",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/182/"
                        },
                        {
                            "name": "marill",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/183/"
                        },
                        {
                            "name": "azumarill",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/184/"
                        },
                        {
                            "name": "sudowoodo",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/185/"
                        },
                        {
                            "name": "politoed",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/186/"
                        },
                        {
                            "name": "hoppip",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/187/"
                        },
                        {
                            "name": "skiploom",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/188/"
                        },
                        {
                            "name": "jumpluff",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/189/"
                        },
                        {
                            "name": "aipom",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/190/"
                        },
                        {
                            "name": "sunkern",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/191/"
                        },
                        {
                            "name": "sunflora",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/192/"
                        },
                        {
                            "name": "yanma",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/193/"
                        },
                        {
                            "name": "wooper",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/194/"
                        },
                        {
                            "name": "quagsire",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/195/"
                        },
                        {
                            "name": "espeon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/196/"
                        },
                        {
                            "name": "umbreon",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/197/"
                        },
                        {
                            "name": "murkrow",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/198/"
                        },
                        {
                            "name": "slowking",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/199/"
                        },
                        {
                            "name": "misdreavus",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/200/"
                        },
                        {
                            "name": "unown",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/201/"
                        },
                        {
                            "name": "wobbuffet",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/202/"
                        },
                        {
                            "name": "girafarig",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/203/"
                        },
                        {
                            "name": "pineco",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/204/"
                        },
                        {
                            "name": "forretress",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/205/"
                        },
                        {
                            "name": "dunsparce",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/206/"
                        },
                        {
                            "name": "gligar",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/207/"
                        },
                        {
                            "name": "steelix",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/208/"
                        },
                        {
                            "name": "snubbull",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/209/"
                        },
                        {
                            "name": "granbull",
                            "url": "https://pokeapi.co/api/v2/pokemon-species/210/"
                        }
                    ]
                }
                """;
    }

    private static final Environment environment() {
        return new Environment() {

            @Override
            public boolean containsProperty(final @Nullable String key) {
                throw new UnsupportedOperationException("Unimplemented method 'containsProperty'");
            }

            @Override
            @Nullable
            public String getProperty(final @Nullable String key) {
                throw new UnsupportedOperationException("Unimplemented method 'getProperty'");
            }

            @NonNull
            @Override
            public String getProperty(final @Nullable String key, final @Nullable String defaultValue) {
                throw new UnsupportedOperationException("Unimplemented method 'getProperty'");
            }

            @Override
            @Nullable
            public <T> T getProperty(final @Nullable String key, final @Nullable Class<T> targetType) {
                throw new UnsupportedOperationException("Unimplemented method 'getProperty'");
            }

            @NonNull
            @Override
            public <T> T getProperty(final @Nullable String key, final @Nullable Class<T> targetType, final @NonNull T defaultValue) {
                return defaultValue;
            }

            @NonNull
            @Override
            public String getRequiredProperty(final @Nullable String key) throws IllegalStateException {
                throw new UnsupportedOperationException("Unimplemented method 'getRequiredProperty'");
            }

            @NonNull
            @Override
            public <T> T getRequiredProperty(final @Nullable String key, final @Nullable Class<T> targetType) throws IllegalStateException {
                throw new UnsupportedOperationException("Unimplemented method 'getRequiredProperty'");
            }

            @NonNull
            @Override
            public String resolvePlaceholders(final @Nullable String text) {
                throw new UnsupportedOperationException("Unimplemented method 'resolvePlaceholders'");
            }

            @NonNull
            @Override
            public String resolveRequiredPlaceholders(final @Nullable String text) throws IllegalArgumentException {
                throw new UnsupportedOperationException("Unimplemented method 'resolveRequiredPlaceholders'");
            }

            @NonNull
            @Override
            public String[] getActiveProfiles() {
                throw new UnsupportedOperationException("Unimplemented method 'getActiveProfiles'");
            }

            @NonNull
            @Override
            public String[] getDefaultProfiles() {
                throw new UnsupportedOperationException("Unimplemented method 'getDefaultProfiles'");
            }

            @Override
            public boolean acceptsProfiles(final @Nullable String... profiles) {
                throw new UnsupportedOperationException("Unimplemented method 'acceptsProfiles'");
            }

            @Override
            public boolean acceptsProfiles(final @Nullable Profiles profiles) {
                throw new UnsupportedOperationException("Unimplemented method 'acceptsProfiles'");
            }

        };
    }

}
