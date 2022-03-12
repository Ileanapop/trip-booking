package Util.ExceptionHandler;

public class OperationError extends OperationStatus{

    private String repositoryOperation;

    public OperationError(String repositoryOperation) {
        super("Error");
        this.repositoryOperation = repositoryOperation;
    }
}
