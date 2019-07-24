package space.nature.javaweb.domain;

/**
 * 实体，生命周期内具有不变标识的类型
 *
 * @param <I> 标识类型
 * @param <E> 实体类型
 */
public interface Entity<I, E extends Identity<I>> extends Identity<I> {

    /**
     * 比较两个实体的identity
     *
     * @param other 其它实体
     * @return <code>true</code>如果它们的identities相同
     */
    default boolean sameIdentityAs(E other) {
        return this.getIdentity().equals(other.getIdentity());
    }

}
