package com.dyz.repository;

import com.dyz.bean.Cat;
import org.springframework.data.repository.CrudRepository;

/**
 * @author dyz
 */
public interface CatRepository extends CrudRepository<Cat, Integer> {

}
