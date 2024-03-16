package hu.pindur.backend.exceptionhandling;

public class ProductNotFoundByIdException extends RuntimeException {
    private final Long productId;

    public ProductNotFoundByIdException(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
