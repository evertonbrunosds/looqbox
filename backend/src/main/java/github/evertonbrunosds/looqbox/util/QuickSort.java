package github.evertonbrunosds.looqbox.util;

import static java.lang.Integer.compare;

import java.util.List;

public class QuickSort<T> {

    private final Comparer<T, T, Integer> comparer;

    public QuickSort() {
        comparer = (final T o1, final T o2) -> compare(o1.hashCode(), o2.hashCode());
    }

    public QuickSort(final Comparer<T, T, Integer> comparer) {
        this.comparer = comparer;
    }

    public void sort(final List<T> list, final boolean reverse) {
        final int low = 0;
        final int high = list.size() - 1;
        perform(list, low, high, reverse);
    }

    private void perform(final List<T> list, final int low, final int high, final boolean reverse) {
        if (low < high) {
            final int pivotIndex = partition(list, low, high, reverse);
            perform(list, low, pivotIndex - 1, reverse);
            perform(list, pivotIndex + 1, high, reverse);
        }
    }

    private int partition(final List<T> list, final int low, final int high, final boolean reverse) {
        final T pivot = list.get(high);
        int i = low - 1;
        final Processor<Integer, Boolean> compareMode = reverse
                ? (index) -> comparer.compare(list.get(index), pivot) > 0
                : (index) -> comparer.compare(list.get(index), pivot) < 0;
        for (int j = low; j < high; j++) {
            if (compareMode.perform(j)) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(final List<T> list, final int i, final int j) {
        final T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
