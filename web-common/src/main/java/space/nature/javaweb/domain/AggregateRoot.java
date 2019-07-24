package space.nature.javaweb.domain;

/**
 * 聚合根，业务角度的操作对象，实体类的特例
 *
 * @param <I>    ID的类型
 * @param <Root> 实体的类型
 */
public interface AggregateRoot<I, Root extends AggregateRoot<I, Root>> extends Entity<I, Root> {

}
