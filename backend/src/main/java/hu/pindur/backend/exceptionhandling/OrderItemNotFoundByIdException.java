package hu.pindur.backend.exceptionhandling;

public class OrderItemNotFoundByIdException extends RuntimeException {
    private final Long orderItemId;

    public OrderItemNotFoundByIdException(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }
}