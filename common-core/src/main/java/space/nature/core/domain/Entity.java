/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.domain;

/**
 * 实体，生命周期内具有不变标识的类型
 *
 * @param <E>  实体类型
 * @param <ID> 标识类型
 */
public interface Entity<E, ID> {

    /**
     * 比较两个实体的identity
     *
     * @param other 其它实体
     * @return <code>true</code>如果它们的identities相同
     */
    boolean sameIdentityAs(E other);

}
