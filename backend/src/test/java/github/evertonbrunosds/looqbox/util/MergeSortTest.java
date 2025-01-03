package github.evertonbrunosds.looqbox.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MergeSortTest {

    @Test
    public void ascendingSortForIntegerWithNullElementAndNoArgumentsInTheConstructorTest() {
        final List<Integer> target = Arrays.asList(new Integer[]{null, 1, 4, 2, 5});
        final List<Integer> expected = Arrays.asList(new Integer[]{null, 1, 2, 4, 5});
        final MergeSort<Integer> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForIntegerWithNoArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(1, 2, 3, 4, 5);
        final MergeSort<Integer> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithNullElementAndNoArgumentsInTheConstructorTest() {
        final List<Integer> target = Arrays.asList(new Integer[]{null, 1, 4, 2, 5});
        final List<Integer> expected = Arrays.asList(new Integer[]{5, 4, 2, 1, null});
        final MergeSort<Integer> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithNoArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(5, 4, 3, 2, 1);
        final MergeSort<Integer> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithNullElementNoArgumentsInTheConstructorTest() {
        final List<String> target = Arrays.asList(new String[]{"C", "A", "D", null, "E"});
        final List<String> expected = Arrays.asList(new String[]{null, "A", "C", "D", "E"});
        final MergeSort<String> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithNoArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("A", "B", "C", "D", "E");
        final MergeSort<String> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithNullElementNoArgumentsInTheConstructorTest() {
        final List<String> target = Arrays.asList(new String[]{"C", "A", "D", null, "E"});
        final List<String> expected = Arrays.asList(new String[]{"E", "D", "C", "A", null});
        final MergeSort<String> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithNoArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("E", "D", "C", "B", "A");
        final MergeSort<String> mergeSort = new MergeSort<>(); // sem argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForIntegerWithNullElementArgumentsInTheConstructorTest() {
        final List<Integer> target = Arrays.asList(new Integer[]{3, null, null, 2, 5});
        final List<Integer> expected = Arrays.asList(new Integer[]{null,null, 2, 3, 5});
        final MergeSort<Integer> mergeSort = new MergeSort<>(Integer::compareTo); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForIntegerWithArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(1, 2, 3, 4, 5);
        final MergeSort<Integer> mergeSort = new MergeSort<>(Integer::compareTo); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithNullElementArgumentsInTheConstructorTest() {
        final List<Integer> target = Arrays.asList(new Integer[]{3, null, null, 2, 5});
        final List<Integer> expected = Arrays.asList(new Integer[]{5, 3, 2, null,null});
        final MergeSort<Integer> mergeSort = new MergeSort<>(Integer::compareTo); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForIntegerWithArgumentsInTheConstructorTest() {
        final List<Integer> target = new ArrayList<>(List.of(3, 1, 4, 2, 5));
        final List<Integer> expected = List.of(5, 4, 3, 2, 1);
        final MergeSort<Integer> mergeSort = new MergeSort<>(Integer::compareTo); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithNullElementArgumentsInTheConstructorTest() {
        final List<String> target = Arrays.asList(new String[]{"C", null, "D", null, "E"});
        final List<String> expected = Arrays.asList(new String[]{null, null, "C", "D", "E"});
        final MergeSort<String> mergeSort = new MergeSort<>(String::compareTo); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringWithArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("A", "B", "C", "D", "E");
        final MergeSort<String> mergeSort = new MergeSort<>(String::compareTo); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithNullElementArgumentsInTheConstructorTest() {
        final List<String> target = Arrays.asList(new String[]{"C", null, "D", null, "E"});
        final List<String> expected = Arrays.asList(new String[]{"E", "D", "C", null, null});
        final MergeSort<String> mergeSort = new MergeSort<>(String::compareTo); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringWithArgumentsInTheConstructorTest() {
        final List<String> target = new ArrayList<>(List.of("C", "A", "D", "B", "E"));
        final List<String> expected = List.of("E", "D", "C", "B", "A");
        final MergeSort<String> mergeSort = new MergeSort<>(String::compareTo); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringLengthWithNullElement() {
        final List<String> target = Arrays.asList(new String[]{"AA", "BBBB", "C", null, null});
        final List<String> expected = Arrays.asList(new String[]{null, null, "C", "AA", "BBBB"});
        final MergeSort<String> mergeSort = new MergeSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void ascendingSortForStringLength() {
        final List<String> target = new ArrayList<>(List.of("AA", "BBBB", "C", "DDD", "EEEEE"));
        final List<String> expected = List.of("C", "AA", "DDD", "BBBB", "EEEEE");
        final MergeSort<String> mergeSort = new MergeSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        mergeSort.sort(target, false); // ordenação ascendente (não reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringLengthNullElement() {
        final List<String> target = Arrays.asList(new String[]{"AA", "BBBB", "C", null, null});
        final List<String> expected = Arrays.asList(new String[]{"BBBB", "AA", "C", null, null});
        final MergeSort<String> mergeSort = new MergeSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

    @Test
    public void descendingSortForStringLength() {
        final List<String> target = new ArrayList<>(List.of("AA", "BBBB", "C", "DDD", "EEEEE"));
        final List<String> expected = List.of("EEEEE", "BBBB", "DDD", "AA", "C");
        final MergeSort<String> mergeSort = new MergeSort<>((a, b) -> Integer.compare(a.length(), b.length())); // com argumento no construtor
        mergeSort.sort(target, true); // ordenação descendente (reversa)
        assertEquals(expected, target);
    }

}
