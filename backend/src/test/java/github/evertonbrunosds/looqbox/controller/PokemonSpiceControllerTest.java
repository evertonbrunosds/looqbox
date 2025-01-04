package github.evertonbrunosds.looqbox.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PokemonSpiceControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(final WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void pokemonsEndpointNoArgumentsTest() throws Exception {
        final String response = mockMvc.perform(get("/pokemons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"blastoise\",\"bulbasaur\",\"charizard\",\"charmander\",\"charmeleon\",\"ivysaur\",\"squirtle\",\"venusaur\",\"wartortle\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithSortASC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?sort=ASC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"blastoise\",\"bulbasaur\",\"charizard\",\"charmander\",\"charmeleon\",\"ivysaur\",\"squirtle\",\"venusaur\",\"wartortle\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithSortDSC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?sort=DSC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"wartortle\",\"venusaur\",\"squirtle\",\"ivysaur\",\"charmeleon\",\"charmander\",\"charizard\",\"bulbasaur\",\"blastoise\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithSortASC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?sort=ASC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"ivysaur\",\"squirtle\",\"venusaur\",\"blastoise\",\"wartortle\",\"charizard\",\"bulbasaur\",\"charmeleon\",\"charmander\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithSortDSC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?sort=DSC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"charmeleon\",\"charmander\",\"blastoise\",\"wartortle\",\"charizard\",\"bulbasaur\",\"squirtle\",\"venusaur\",\"ivysaur\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithName() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?name=aur")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"bulbasaur\",\"ivysaur\",\"venusaur\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithNameAndASC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?name=aur&sort=ASC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"bulbasaur\",\"ivysaur\",\"venusaur\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithNameAndDSC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?name=aur&sort=DSC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"venusaur\",\"ivysaur\",\"bulbasaur\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithNameAndASC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?name=aur&sort=ASC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"ivysaur\",\"venusaur\",\"bulbasaur\"]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsEndpointWithNameAndDSC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons?name=aur&sort=DSC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[\"bulbasaur\",\"venusaur\",\"ivysaur\"]}";
        assertEquals(expected, response);
    }

    // -------

    @Test
    void pokemonsHighlightEndpointNoArgumentsTest() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"blastoise\",\"highlight\":null},{\"name\":\"bulbasaur\",\"highlight\":null},{\"name\":\"charizard\",\"highlight\":null},{\"name\":\"charmander\",\"highlight\":null},{\"name\":\"charmeleon\",\"highlight\":null},{\"name\":\"ivysaur\",\"highlight\":null},{\"name\":\"squirtle\",\"highlight\":null},{\"name\":\"venusaur\",\"highlight\":null},{\"name\":\"wartortle\",\"highlight\":null}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithSortASC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?sort=ASC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"blastoise\",\"highlight\":null},{\"name\":\"bulbasaur\",\"highlight\":null},{\"name\":\"charizard\",\"highlight\":null},{\"name\":\"charmander\",\"highlight\":null},{\"name\":\"charmeleon\",\"highlight\":null},{\"name\":\"ivysaur\",\"highlight\":null},{\"name\":\"squirtle\",\"highlight\":null},{\"name\":\"venusaur\",\"highlight\":null},{\"name\":\"wartortle\",\"highlight\":null}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithSortDSC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?sort=DSC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"wartortle\",\"highlight\":null},{\"name\":\"venusaur\",\"highlight\":null},{\"name\":\"squirtle\",\"highlight\":null},{\"name\":\"ivysaur\",\"highlight\":null},{\"name\":\"charmeleon\",\"highlight\":null},{\"name\":\"charmander\",\"highlight\":null},{\"name\":\"charizard\",\"highlight\":null},{\"name\":\"bulbasaur\",\"highlight\":null},{\"name\":\"blastoise\",\"highlight\":null}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithSortASC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?sort=ASC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"ivysaur\",\"highlight\":null},{\"name\":\"squirtle\",\"highlight\":null},{\"name\":\"venusaur\",\"highlight\":null},{\"name\":\"blastoise\",\"highlight\":null},{\"name\":\"wartortle\",\"highlight\":null},{\"name\":\"charizard\",\"highlight\":null},{\"name\":\"bulbasaur\",\"highlight\":null},{\"name\":\"charmeleon\",\"highlight\":null},{\"name\":\"charmander\",\"highlight\":null}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithSortDSC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?sort=DSC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"charmeleon\",\"highlight\":null},{\"name\":\"charmander\",\"highlight\":null},{\"name\":\"blastoise\",\"highlight\":null},{\"name\":\"wartortle\",\"highlight\":null},{\"name\":\"charizard\",\"highlight\":null},{\"name\":\"bulbasaur\",\"highlight\":null},{\"name\":\"squirtle\",\"highlight\":null},{\"name\":\"venusaur\",\"highlight\":null},{\"name\":\"ivysaur\",\"highlight\":null}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithName() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?name=aur")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"bulbasaur\",\"highlight\":\"bulbas<pre>aur<pre>\"},{\"name\":\"ivysaur\",\"highlight\":\"ivys<pre>aur<pre>\"},{\"name\":\"venusaur\",\"highlight\":\"venus<pre>aur<pre>\"}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithNameAndASC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?name=aur&sort=ASC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"bulbasaur\",\"highlight\":\"bulbas<pre>aur<pre>\"},{\"name\":\"ivysaur\",\"highlight\":\"ivys<pre>aur<pre>\"},{\"name\":\"venusaur\",\"highlight\":\"venus<pre>aur<pre>\"}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithNameAndDSC_ALPHABET() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?name=aur&sort=DSC_ALPHABET")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"venusaur\",\"highlight\":\"venus<pre>aur<pre>\"},{\"name\":\"ivysaur\",\"highlight\":\"ivys<pre>aur<pre>\"},{\"name\":\"bulbasaur\",\"highlight\":\"bulbas<pre>aur<pre>\"}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithNameAndASC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?name=aur&sort=ASC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"ivysaur\",\"highlight\":\"ivys<pre>aur<pre>\"},{\"name\":\"venusaur\",\"highlight\":\"venus<pre>aur<pre>\"},{\"name\":\"bulbasaur\",\"highlight\":\"bulbas<pre>aur<pre>\"}]}";
        assertEquals(expected, response);
    }

    @Test
    void pokemonsHighlightEndpointWithNameAndDSC_LENGTH() throws Exception {
        final String response = mockMvc.perform(get("/pokemons/highlight?name=aur&sort=DSC_LENGTH")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final String expected = "{\"result\":[{\"name\":\"bulbasaur\",\"highlight\":\"bulbas<pre>aur<pre>\"},{\"name\":\"venusaur\",\"highlight\":\"venus<pre>aur<pre>\"},{\"name\":\"ivysaur\",\"highlight\":\"ivys<pre>aur<pre>\"}]}";
        assertEquals(expected, response);
    }

}
