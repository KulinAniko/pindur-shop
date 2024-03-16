package hu.pindur.backend.exceptionhandling;

public class OrderCannotBeDeletedNow extends RuntimeException {
    private Long orderId;

    public OrderCannotBeDeletedNow(Long orderId) {
    }

    public Long getOrderId() {
        return orderId;
    }
}
