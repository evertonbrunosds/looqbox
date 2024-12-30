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
        if (list.size() > 1) {
            final int middle = list.size() / 2;
            final List<T> leftList = new ArrayList<>(list.subList(0, middle));
            final List<T> rightList = new ArrayList<>(list.subList(middle, list.size()));
            sort(leftList, reverse);
            sort(rightList, reverse);
            perform(list, leftList, rightList, reverse);
        }
    }

    private void perform(final List<T> list, final List<T> leftList, final List<T> rightList, final boolean reverse) {
        final Comparer<T, T, Boolean> comparerMode = reverse
                ? (paramOne, paramTwo) -> comparer.compare(paramOne, paramTwo) > 0
                : (paramOne, paramTwo) -> comparer.compare(paramOne, paramTwo) < 0;
        int leftIndex = 0, rightIndex = 0, index = 0;
        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (comparerMode.compare(leftList.get(leftIndex), rightList.get(rightIndex))) {
                list.set(index++, leftList.get(leftIndex++));
            } else {
                list.set(index++, rightList.get(rightIndex++));
            }
        }
        while (leftIndex < leftList.size()) {
            list.set(index++, leftList.get(leftIndex++));
        }
        while (rightIndex < rightList.size()) {
            list.set(index++, rightList.get(rightIndex++));
        }
    }

}
