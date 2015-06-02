package cn.yht.simpleOA.service.impl;

import cn.yht.simpleOA.base.DaoSupportImpl;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.service.BreaktimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YHT on 2015/5/19.
 */
@Service
@Transactional
public class BreaktimeServiceImpl extends DaoSupportImpl<Breaktime> implements BreaktimeService {
}
