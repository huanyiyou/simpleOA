package cn.yht.simpleOA.base;

import cn.yht.simpleOA.model.PageBean;
import cn.yht.simpleOA.util.QueryHelper;

import java.util.List;

/**
 * Created by admin on 2015/5/7.
 */
public interface DaoSupport<T> {
    void save(T entity);
    void delete(Long id);
    void update(T entity);
    T getById(Long id);
    List<T> getByIds(Long[] ids);
    List<T> findAll();

    PageBean getPageBean(Integer pageNum, int pageSize, QueryHelper queryHelper, List<String> parametersKey);
}
