package cn.yht.simpleOA.base;

import cn.yht.simpleOA.model.PageBean;
import cn.yht.simpleOA.util.QueryHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2015/5/7.
 */
@Transactional
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {
    @Resource
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public DaoSupportImpl(){
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void delete(Long id) {
        Object object = getById(id);
        if(object != null){
            getSession().delete(object);
        }
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public T getById(Long id) {
        if(id == null){
            return null;
        }else {
            return (T) getSession().get(clazz,id);
        }
    }

    @Override
    public List<T> getByIds(Long[] ids) {
        if(ids == null ||  ids.length == 0){
            return Collections.emptyList();
        }else {
            return (List<T>)getSession().createQuery(
                    "FROM "+clazz.getSimpleName()+ " WHERE id IN (:ids) ")
                    .setParameterList("ids",ids).list();
        }
    }

    @Override
    public List<T> findAll() {
        return (List<T>)getSession().createQuery(
                "FROM " + clazz.getSimpleName())
                .list();
    }

//    @Override
//    public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> params) {
//        Query listQuery = getSession().createQuery(hql);
//        if(params != null){
//            for(int i = 0; i < params.size(); i++){
//                listQuery.setParameter(i, params.get(i));
//            }
//        }
//        List list = listQuery.setFirstResult((pageNum - 1) * pageSize)
//                .setMaxResults(pageSize)
//                .list();
//
//        Query countQuery = getSession().createQuery("SELECT COUNT(*) " + hql);
//        if(params != null){
//            for(int i = 0; i < params.size(); i++){
//                countQuery.setParameter(i, params.get(i));
//            }
//        }
//        Long count = (Long) countQuery.uniqueResult();
//
//        return new PageBean(pageNum, pageSize, list, count.intValue());
//    }

    @Override
    public PageBean getPageBean(Integer pageNum, int pageSize, QueryHelper queryHelper, List<String> parametersKey) {
        Query listQuery = getSession().createQuery(queryHelper.getListQueryHql());
        List<Object> params = queryHelper.getParameters();
        if(params != null){
            for(int i = 0; i < params.size(); i++){
                listQuery.setParameter(parametersKey.get(i), params.get(i));
            }
        }
        List list = listQuery.setFirstResult((pageNum - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();

        Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
        if(params != null){
            for(int i = 0; i < params.size(); i++){
                countQuery.setParameter(parametersKey.get(i), params.get(i));
            }
        }
        Long count = (Long) countQuery.uniqueResult();

        return new PageBean(pageNum, pageSize, list, count.intValue());
    }
}
