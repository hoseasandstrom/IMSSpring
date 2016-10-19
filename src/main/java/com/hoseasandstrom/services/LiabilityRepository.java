package com.hoseasandstrom.services;

import com.hoseasandstrom.entities.Liability;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 10/19/16.
 */
public interface LiabilityRepository extends CrudRepository<Liability, Integer>{
}
