package com.test.customerorder.repository;

import com.test.customerorder.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {
}
