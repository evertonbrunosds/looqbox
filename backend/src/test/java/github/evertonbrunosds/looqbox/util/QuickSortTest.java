package github.evertonbrunosds.looqbox.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class QuickSortTest {

    @Test
    public void ascendingSortForIntegerWithNoArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(1, 2, 3, 4, 5);
        final QuickSort<Integer> quickSort = new QuickSort<>(); // sem argumento no construtor
        quickSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithNoArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(5, 4, 3, 2, 1);
        final QuickSort<Integer> quickSort = new QuickSort<>(); // sem argumento no construtor
        quickSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithNoArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("A", "B", "C", "D", "E");
        final QuickSort<String> quickSort = new QuickSort<>(); // sem argumento no construtor
        quickSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithNoArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("E", "D", "C", "B", "A");
        final QuickSort<String> quickSort = new QuickSort<>(); // sem argumento no construtor
        quickSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForIntegerWithArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(1, 2, 3, 4, 5);
        final QuickSort<Integer> quickSort = new QuickSort<>(Integer::compareTo); // com argumento no construtor
        quickSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(5, 4, 3, 2, 1);
        final QuickSort<Integer> quickSort = new QuickSort<>(Integer::compareTo); // com argumento no construtor
        quickSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("A", "B", "C", "D", "E");
        final QuickSort<String> quickSort = new QuickSort<>(String::compareTo); // com argumento no construtor
        quickSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("E", "D", "C", "B", "A");
        final QuickSort<String> quickSort = new QuickSort<>(String::compareTo); // com argumento no construtor
        quickSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }


    @Test
    public void ascendingSortForStringLength() {
        final List<String> target = new ArrayList<>(List.of("AA", "BBBB", "C", "DDD", "EEEEE"));
        final List<String> expected = List.of("C", "AA", "DDD", "BBBB", "EEEEE");
        final QuickSort<String> quickSort = new QuickSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        quickSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringLength() {
        final List<String> target = new ArrayList<>(List.of("AA", "BBBB", "C", "DDD", "EEEEE"));
        final List<String> expected = List.of("EEEEE", "BBBB", "DDD", "AA", "C");
        final QuickSort<String> quickSort = new QuickSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        quickSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

}
