/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.domain;

public interface ValueObject<T> {

    /**
     * 与其它值对象比较属性
     *
     * @param other 其它的值对象
     * @return <code>true</code> 如果两者的属性相同
     */
    boolean sameValueAs(T other);

}
