package hu.pindur.backend.exceptionhandling;

public class CartisEmptyException extends RuntimeException {
    private Long cartId;

    public CartisEmptyException(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }
}