package utils.http;

public class HttpResult<T> {
    private int code;
    private String message;
    private T data;
}