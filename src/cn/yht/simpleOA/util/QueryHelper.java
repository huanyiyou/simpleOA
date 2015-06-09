package cn.yht.simpleOA.util;

/**
 * Created by YHT on 2015/5/27.
 */

import cn.yht.simpleOA.base.DaoSupport;
import cn.yht.simpleOA.model.PageBean;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 辅助拼接HQL语句
 */
public class QueryHelper {
    private String fromClause;
    private String whereClause = "";
    private String orderByClause = "";

    private List<Object> parameters = new ArrayList<>();
    private List<String> parametersKey = new ArrayList<>();
    /**
     * 生成FROM子句
     * @param clazz  查询类名
     * @param alias  别名
     */
    public QueryHelper(Class clazz, String alias, List<String> parametersKey){
        fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
        this.parametersKey = parametersKey;
    }


    public QueryHelper addCondition(String condition, Object... params){
        if(whereClause.length() == 0){
            whereClause = " WHERE " + condition;
        }else {
            whereClause += " AND " +condition;
        }
        if(params  != null){
            for(Object p : params){
                parameters.add(p);
            }
        }
        return  this;
    }

    public QueryHelper addCondition(boolean append, String condition, Object... params){
        if(append){
            addCondition(condition, params);
        }
        return this;
    }


    public QueryHelper addOrderProperty(String propertyName, boolean asc){
        if(orderByClause.length() == 0){
            orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
        }else {
            orderByClause += " , "+ propertyName + (asc ? " ASC":" DESC");
        }
        return this;
    }

    public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc){
        if(append){
            addOrderProperty(propertyName, asc);
        }
        return this;
    }

    /**
     * 得到参数集合
     * @return
     */
    public List<Object> getParameters(){
        return parameters;
    }


    public List<String> getParametersKey() {return  parametersKey;}

    /**
     * 查询数据列表的Hql语句
     * @return
     */
    public String getListQueryHql(){
        return fromClause + whereClause + orderByClause ;
    }



    /**
     * 查询数据量的Hql语句
     * @return
     */
    public String getCountQueryHql(){
        return "SELECT COUNT(*) " + fromClause + whereClause;
    }

    /**
     * 准备分页信息到Model
     * @param service
     * @param pageNum
     * @param model
     */

    public void preparePageBean(DaoSupport<?> service, int pageNum, Model model, int pageSize){
        PageBean pageBean = service.getPageBean(pageNum, pageSize, this, this.getParametersKey());
        model.addAttribute("pageBean", pageBean);
    }
}
