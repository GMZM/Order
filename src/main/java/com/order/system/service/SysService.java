package com.order.system.service;

import java.util.List;

/**
 * @Package: com.order.system.service
 * @ClassName: SysService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 22:26
 */
public interface SysService<T> {
    public List<T> listAll();

    /**
     * 根据主键查找
     * @param integer
     * @return
     */
    public List<T> listOne(Integer integer);
    public void save(T t);

    /**
     * 根据主键删除
     * @param integer
     */
    public void delete(Integer integer);

    /**
     * 根据主键更新
     * @param integer
     */
    public void update(Integer integer);
}
