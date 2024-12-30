package github.evertonbrunosds.looqbox.util;

@FunctionalInterface
public interface Processor<I, O> {

    public O perform(final I inputParam);

}
