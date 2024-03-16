package hu.pindur.backend.exceptionhandling;

public class FeedbackNotFoundByIdException extends RuntimeException {
    private final Long feedbackId;

    public FeedbackNotFoundByIdException(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }
}
