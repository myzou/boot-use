package com.dyz.service;

import com.dyz.bean.Cat;
import com.dyz.dao.CatDao;
import com.dyz.repository.Cat2Repository;
import com.dyz.repository.CatRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author dyz
 */
@Service
public class CatService {

    @Resource
    private CatRepository catRepository;

    @Resource
    private Cat2Repository cat2Repository;

    @Resource
    private CatDao catDao;

    /**
     * save,update ,delete 方法需要绑定事务.
     * <p>
     * 使用@Transactional进行事务的绑定.
     *
     * @param cat
     */
    @Transactional(rollbackOn = Exception.class)
    public void save(Cat cat) {
        catRepository.save(cat);
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(int id) {
        catRepository.deleteById(id);
    }

    public Iterable<Cat> getAll() {
        return catRepository.findAll();
    }

    public Cat findByCatName(String catName) {
        return cat2Repository.findByCatName(catName);
    }

    public Cat findByCatName2(String catName) {
        return cat2Repository.findMyCatName(catName);
    }

    public Cat selectByCatName(String catName) {
        return catDao.selectByCatName(catName);
    }
}
