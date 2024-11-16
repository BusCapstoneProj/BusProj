package com.capstoneproj.admin_service.repository;

import com.capstoneproj.admin_service.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
}
