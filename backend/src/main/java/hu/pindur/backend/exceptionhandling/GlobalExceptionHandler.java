package hu.pindur.backend.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        validationErrors.forEach(validationError -> {
            log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handleProductNotFoundException(ProductNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("productId",
                "Product not found with id: " + exception.getProductId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleUserNotFoundByIdException(UserNotFoundException exception) {
        ValidationError validationError = new ValidationError("userId",
                "User not found with id: " + exception.getUserId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleEmailNotFoundException(EmailNotFoundException exception) {
        ValidationError validationError = new ValidationError("errorMessage",
                exception.getErrorMessage());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThereIsNoRoleAssignedToUserException.class)
    public ResponseEntity<List<ValidationError>> handleThereIsNoRoleAssignedToUserException(ThereIsNoRoleAssignedToUserException exception) {
        ValidationError validationError = new ValidationError("User",
                "User has no role with name: " + exception.getUsername());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderItemNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handleOrderItemNotFoundByIdException(OrderItemNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("orderItemId",
                "Order item not found with id: " + exception.getOrderItemId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeedbackNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handleFeedbackNotFoundByIdException(FeedbackNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("feedbackId",
                "Feedback not found with id: " + exception.getFeedbackId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartItemNotExistsException.class)
    public ResponseEntity<List<ValidationError>> handleCartItemNotExistsException(CartItemNotExistsException exception) {
        ValidationError validationError = new ValidationError("cartItemId",
                "Item not found with id: " + exception.getCartItemId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutOfThisProductException.class)
    public ResponseEntity<List<ValidationError>> handleOutOfThisProductException(OutOfThisProductException exception) {
        ValidationError validationError = new ValidationError("stock",
                "From " + exception.getName() + " are only " + exception.getStock() + " available.");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartIsNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleCartIsNotFoundException(CartIsNotFoundException exception) {
        ValidationError validationError = new ValidationError("userName",
                "Cart is not found for user: " + exception.getUsername());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFeedbackAllowedException.class)
    public ResponseEntity<List<ValidationError>> handleNoFeedbackAllowedException(NoFeedbackAllowedException exception) {
        ValidationError validationError = new ValidationError("exceptionMessage",
                exception.getExceptionMessage());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<List<ValidationError>> handleEmailAlreadyExistException(EmailAlreadyExistException exception) {
        ValidationError validationError = new ValidationError("email",
                "Email already exists in the database: " + exception.getEmail());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderIsNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleOrderIsNotFoundException(OrderIsNotFoundException exception) {
        ValidationError validationError = new ValidationError("orderId",
                "Order is not found with orderId: " + exception.getOrderId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeedbackNotFoundByProductIdException.class)
    public ResponseEntity<List<ValidationError>> handleFeedbackNotFoundByProductIdException(FeedbackNotFoundByProductIdException exception) {
        ValidationError validationError = new ValidationError("productId",
                "There are no reviews for this product with productId: " + exception.getProductId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceFilterIsNotValidException.class)
    public ResponseEntity<List<ValidationError>> handlePriceFilterIsNotValidException(PriceFilterIsNotValidException exception) {
        ValidationError validationError = new ValidationError("minPrice",
                "minPrice is greater than maxPrice");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeedbackNotFoundByAppUserException.class)
    public ResponseEntity<List<ValidationError>> handleFeedbackNotFoundByAppUserException(FeedbackNotFoundByAppUserException exception) {
        ValidationError validationError = new ValidationError("username",
                "There are no reviews for this user with username: " + exception.getUsername());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderCannotBeDeletedNow.class)
    public ResponseEntity<List<ValidationError>> handleOrderCannotBeDeletedNow(OrderCannotBeDeletedNow exception) {
        ValidationError validationError = new ValidationError("orderId",
                "Order with id: " + exception.getOrderId() + " can be deleted within 1 day after it has been placed.");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartisEmptyException.class)
    public ResponseEntity<List<ValidationError>> handleCartIsEmptyException(CartisEmptyException exception) {
        ValidationError validationError = new ValidationError("cartId",
                "Cart is empty with id: " + exception.getCartId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponIsNotFound.class)
    public ResponseEntity<List<ValidationError>> handleCouponIsNotFound(CouponIsNotFound exception) {
        ValidationError validationError = new ValidationError("couponCode",
                "Coupon is not found with code: " + exception.getCouponCode());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
