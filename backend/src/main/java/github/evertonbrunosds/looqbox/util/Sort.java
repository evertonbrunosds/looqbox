package github.evertonbrunosds.looqbox.util;

public enum Sort {

    ASC_ALPHABET(false, Type.ALPHABET),
    DSC_ALPHABET(true, Type.ALPHABET),
    ASC_LENGTH(false, Type.LENGTH),
    DSC_LENGTH(true, Type.LENGTH);

    public static enum Type {
        LENGTH, ALPHABET
    }

    private final boolean reverse;
    private final Type type;

    private Sort(final boolean reverse, final Type type) {
        this.reverse = reverse;
        this.type = type;
    }

    public boolean isReverse() {
        return reverse;
    }

    public Type getType() {
        return type;
    }

}
