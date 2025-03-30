package tech.gdev.springbasicexplore.support.exception.runtimeexception;

/**
 * @author gdev
 * @date 2025/3/30 17:42
 */
public class DebugRuntimeException extends RuntimeException {
    public DebugRuntimeException() {
        super();
    }

    public DebugRuntimeException(String message) {
        super(message);
    }

    public DebugRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DebugRuntimeException(Throwable cause) {
        super(cause);
    }

    protected DebugRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
