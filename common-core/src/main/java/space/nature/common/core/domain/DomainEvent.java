/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.domain;

public abstract class DomainEvent<T> {

    private String id;

    private String source;

    private String type;

    private T data;

    public DomainEvent(String source, String type, T data) {
        this.source = source;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }

}
