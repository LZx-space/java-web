/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.domain;

/**
 * 聚合根，业务角度的操作对象，实体类的特例
 *
 * @param <Root> 实体的类型
 * @param <ID>   ID的类型
 */
public interface AggregateRoot<Root extends AggregateRoot<Root, ID>, ID> extends Entity<Root, ID> {

}
