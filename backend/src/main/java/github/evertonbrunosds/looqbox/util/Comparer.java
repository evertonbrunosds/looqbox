package github.evertonbrunosds.looqbox.util;

@FunctionalInterface
public interface Comparer<X, Y, O> {

    public O compare(final X inputParamOne, final Y inputParamTwo);

}
