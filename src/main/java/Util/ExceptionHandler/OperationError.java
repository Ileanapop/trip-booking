package Util.ExceptionHandler;

public class OperationError extends OperationStatus{

    public OperationError(String repositoryOperation) {
        super("Error" + repositoryOperation);

    }
}
