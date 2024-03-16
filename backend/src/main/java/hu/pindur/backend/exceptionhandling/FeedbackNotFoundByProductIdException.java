package hu.pindur.backend.exceptionhandling;

public class FeedbackNotFoundByProductIdException extends RuntimeException {
    private Long productId;

    public FeedbackNotFoundByProductIdException(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
