package hu.pindur.backend.domain;

public enum PaymentMethod {
    CARD("Card"),
    CASH("Cash");

    private String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
