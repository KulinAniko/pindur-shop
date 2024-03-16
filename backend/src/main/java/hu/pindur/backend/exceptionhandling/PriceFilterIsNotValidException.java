package hu.pindur.backend.exceptionhandling;


public class PriceFilterIsNotValidException extends RuntimeException {
    Double minPrice;
    Double maxPrice;

    public PriceFilterIsNotValidException(Double minPrice, Double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }
}