/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.domain;

import java.util.List;
import java.util.Optional;

/**
 * Repo接口，只处理聚合根，禁止直接获取聚合内的对象，保证面向业务而非面向数据，不会将业务处理细节暴露到其他代码或者SQL逻辑中
 * <p style="color:red;">如果通过聚合根获取数据的负面影响过大，如：从聚合根查聚合内的其他实体时SQL开销不合理，一般需要考虑聚合设计的是
 * 否合理</p>
 *
 * @param <Root> 聚合根子类
 * @param <ID>   标识类型
 */
public interface Repository<Root extends AggregateRoot<Root, ID>, ID> {

    /**
     * 插入一个聚合根
     *
     * @param root 聚合根对象
     * @return
     */
    int insert(Root root);

    /**
     * 删除一个聚合根
     *
     * @param id 实体标识
     * @return
     */
    int delete(ID id);

    /**
     * 更新一个聚合根
     *
     * @param root 聚合根对象
     * @return
     */
    int update(Root root);

    /**
     * 获取一个聚合根
     *
     * @param id 实体标识
     * @return
     */
    Optional<Root> findById(ID id);

    /**
     * 查找该聚合根的所有实例
     *
     * @return
     */
    List<Root> findAll();

    /**
     * 查找该聚合根的某些指定ID的实例
     *
     * @param ids ID的Iterate对象
     * @return
     */
    List<Root> findAllById(Iterable<ID> ids);

}
