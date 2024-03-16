package hu.pindur.backend.exceptionhandling;

public class CartItemNotExistsException extends RuntimeException {

    private final Long cartItemId;

    public CartItemNotExistsException(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }
}
