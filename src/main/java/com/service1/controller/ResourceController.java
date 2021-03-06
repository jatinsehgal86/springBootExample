/**
 * 
 */
package com.service1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service1.domain.Resource;
import com.service1.repository.ResourceRepository;

/**
 * @author user
 *
 */
@RestController
public class ResourceController {

	@Autowired
	private ResourceRepository resourceRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);
	@GetMapping("/getResource1")
	public ResponseEntity<Resource> getResource(@RequestParam(name = "id") Long id) {
		
		Resource resource = null;
		Optional<Resource> optionalResource = resourceRepository.findById(id);
		if(optionalResource.isPresent()) {
			resource = optionalResource.get();
		}
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping("/insertResource")
	public ResponseEntity<Resource> createResource(@RequestBody @Valid Resource resource) {
		Resource insertedResource = resourceRepository.saveAndFlush(resource);
		return new ResponseEntity<>(insertedResource, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateResource/{id}")
	public ResponseEntity<Resource> updateResource(@PathVariable(name = "id") Long id, @RequestBody Resource newResource) {
		
		LOGGER.info("Inside the update resource method for id " + id);
		LOGGER.info("Resource object : " + newResource);
		
		Resource existingResource = null;
		Optional<Resource> optionalResource = resourceRepository.findById(id);
		if(optionalResource.isPresent()) {
			existingResource = optionalResource.get();
		}
		
		if(null != newResource.getAddress()) {
			existingResource.setAddress(newResource.getAddress());
		}
		
		if(null != newResource.getName()) {
			existingResource.setName(newResource.getName());
		}
		
		if(null != newResource.getAge()) {
			existingResource.setAge(newResource.getAge());
		}
		Resource insertedResource = resourceRepository.saveAndFlush(existingResource);
		LOGGER.info("Updated the resource successfully for id " + id);
		return new ResponseEntity<>(insertedResource, HttpStatus.OK);
	}
	
}
