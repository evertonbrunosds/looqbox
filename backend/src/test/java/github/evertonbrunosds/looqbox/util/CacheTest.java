package github.evertonbrunosds.looqbox.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import github.evertonbrunosds.looqbox.util.Cache.MeasureTime;

public class CacheTest {

    private class Counter {

        private static int value = -1;

        public int value() {
            value++;
            return value;
        }

    }

    @Test
    public void getDataTest() throws InterruptedException {
        int expected;
        final Counter counter = new Counter(); // simula um processo custoso que deve ser armazenado em cache
        final int timeInterval = 1;
        final Cache.Process<?> onUpdate = counter::value; // o determina o que deve ocorrer ao atualizar o cache
        final Cache cache = new Cache(timeInterval, MeasureTime.SECOND, onUpdate);

        // verifica mil vezes se o valor contido em cache se mantém sendo 0 (zero)
        expected = 0;
        for (int i = 0; i < 1000; i++) {
            assertEquals(expected, cache.<Integer>getData().orGet(Integer.MAX_VALUE));
        }

        Thread.sleep(1500); // aguarda 1 segundo e meio para que o cache fique inválido
        // verifica mil vezes se o valor contido em cache se mantém sendo 1 (um)
        expected = 1;
        for (int i = 0; i < 1000; i++) {
            assertEquals(expected, cache.<Integer>getData().orGet(Integer.MAX_VALUE));
        }

        Thread.sleep(1500); // aguarda 1 segundo e meio para que o cache fique inválido
        // verifica mil vezes se o valor contido em cache se mantém sendo 2 (dois)
        expected = 2;
        for (int i = 0; i < 1000; i++) {
            assertEquals(expected, cache.<Integer>getData().orGet(Integer.MAX_VALUE));
        }

        Thread.sleep(1500); // aguarda 1 segundo e meio para que o cache fique inválido
        // verifica mil vezes se o valor contido em cache se mantém sendo 3 (três)
        expected = 3;
        for (int i = 0; i < 1000; i++) {
            assertEquals(expected, cache.<Integer>getData().orGet(Integer.MAX_VALUE));
        }
    }

    @Test
    public void getDataOrGetTest() {
        final int timeInterval = 1;
        final Cache.Process<?> onUpdate = () -> {
            throw new NullPointerException();
        };
        final Cache cache = new Cache(timeInterval, MeasureTime.SECOND, onUpdate);
        int expected = 5;
        assertEquals(expected, cache.<Integer>getData().orGet(5));

    }

    @Test
    public void invalidIntervalOnConstructorTest() {
        try {
            new Cache(0, MeasureTime.SECOND, () -> 5);
            fail();
        } catch(InvalidParameterException exception) {
            final String expected = "the 'timeInterval' parameter must be greater than zero";
            assertEquals(expected, exception.getMessage());
        }
    }

}
