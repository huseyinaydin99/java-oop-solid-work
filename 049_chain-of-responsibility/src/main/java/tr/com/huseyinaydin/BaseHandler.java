package tr.com.huseyinaydin;

abstract class BaseHandler {
    protected BaseHandler nextHandler;

    public void setNext(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(Request request);

    protected boolean checkNext(Request request) {
        if (nextHandler == null) {
            return true;
        }
        return nextHandler.handle(request);
    }
}