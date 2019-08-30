package com.dyz.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyz.mybatisplus.dao.PeopleMapper;
import com.dyz.mybatisplus.entity.People;
import com.dyz.mybatisplus.service.IPeopleService;
import org.springframework.stereotype.Service;

/**
 * @author dyz
 * @create 2019-07-13 23:07
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {
}
