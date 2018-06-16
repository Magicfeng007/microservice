package com.magic.microservice.spring_provider_user.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magic.microservice.spring_provider_user.entity.MobilePhone;

/**
 * @Author: Magicfeng007
 * @Description:
 * @Date: Created in 2018-06-10---下午1:16
 */
@Repository
public interface MobileRepository extends PagingAndSortingRepository<MobilePhone,Integer> {


}
