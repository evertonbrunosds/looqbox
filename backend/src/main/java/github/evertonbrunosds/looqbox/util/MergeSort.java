package github.evertonbrunosds.looqbox.util;

import static java.lang.Integer.compare;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T> {

    private final Comparer<T, T, Integer> comparer;

    public MergeSort() {
        comparer = (final T o1, final T o2) -> compare(o1.hashCode(), o2.hashCode());
    }

    public MergeSort(final Comparer<T, T, Integer> comparer) {
        this.comparer = comparer;
    }

    public void sort(final List<T> list, final boolean reverse) {
        if (list.size() <= 1) {
            return;
        }
        final int middle = list.size() / 2;
        final List<T> left = new ArrayList<>(list.subList(0, middle));
        final List<T> right = new ArrayList<>(list.subList(middle, list.size()));
        sort(left, reverse);
        sort(right, reverse);
        perform(list, left, right, reverse);
    }

    private void perform(final List<T> list, final List<T> left, final List<T> right, final boolean reverse) {
        final Comparer<T, T, Boolean> comparerMode = reverse
                ? (paramOne, paramTwo) -> comparer.compare(paramOne, paramTwo) > 0
                : (paramOne, paramTwo) -> comparer.compare(paramOne, paramTwo) < 0;
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparerMode.compare(left.get(i), right.get(j))) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

}
