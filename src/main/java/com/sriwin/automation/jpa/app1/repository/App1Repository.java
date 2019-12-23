package com.sriwin.automation.jpa.app1.repository;

import com.sriwin.automation.jpa.app1.entity.App1Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface App1Repository extends CrudRepository<App1Entity, Long> {
}