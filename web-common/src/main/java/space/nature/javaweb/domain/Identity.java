package space.nature.javaweb.domain;

public interface Identity<T> {

    /**
     * 获取实体ID
     * @return
     */
    T getIdentity();

}
