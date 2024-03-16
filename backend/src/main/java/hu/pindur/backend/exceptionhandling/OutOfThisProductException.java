package hu.pindur.backend.exceptionhandling;

public class OutOfThisProductException extends RuntimeException {
    private final String name;
    private final Integer stock;

    public OutOfThisProductException(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }
}
