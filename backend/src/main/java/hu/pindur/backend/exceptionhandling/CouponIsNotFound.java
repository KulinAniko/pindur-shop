package hu.pindur.backend.exceptionhandling;

public class CouponIsNotFound extends RuntimeException {
    private String couponCode;

    public CouponIsNotFound(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }
}