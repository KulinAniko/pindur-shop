package hu.pindur.backend.exceptionhandling;

public class OrderIsNotFoundException extends RuntimeException {
    private Long orderId;

    public OrderIsNotFoundException(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
