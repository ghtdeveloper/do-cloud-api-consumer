package com.neptune.repository;

import com.neptune.domain.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity,Long> {
}
