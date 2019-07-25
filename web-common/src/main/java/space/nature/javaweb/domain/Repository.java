package space.nature.javaweb.domain;

/**
 * Repo接口，只处理聚合根，禁止直接获取聚合内的对象，保证面向业务而非面向数据，不会将业务处理细节暴露到其他代码或者SQL逻辑中
 * <p style="color:red;">如果通过聚合根获取数据的负面影响过大，如：从聚合根查聚合内的其他实体时SQL开销不合理，一般需要考虑聚合设计的是
 * 否合理</p>
 *
 * @param <I>    标识类型
 * @param <Root> 聚合根子类
 */
public interface Repository<I, Root extends AggregateRoot<I, Root>> {

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
    int delete(I id);

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
    Root getById(I id);

}
