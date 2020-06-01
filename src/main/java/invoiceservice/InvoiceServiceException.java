package invoiceservice;

public class InvoiceServiceException extends RuntimeException {

    enum ExceptionType {
        ENTERED_NULL,ENTERED_EMPTY
    }

    ExceptionType type;

    public InvoiceServiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
