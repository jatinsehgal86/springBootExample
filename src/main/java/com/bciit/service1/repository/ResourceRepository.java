/**
 * 
 */
package com.bciit.service1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bciit.service1.domain.Resource;

/**
 * @author user
 *
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>{
	
	public Resource findByName(String name);
}
