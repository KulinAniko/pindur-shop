package hu.pindur.backend.domain;

public enum ProductType {
    DIORAMA("diorama"),
    PAPERCRAFZ("papercraft");



    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
