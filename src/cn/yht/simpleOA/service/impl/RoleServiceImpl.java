package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Role;
import cn.yht.simpleOA.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2015/5/14.
 */
@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {
}
