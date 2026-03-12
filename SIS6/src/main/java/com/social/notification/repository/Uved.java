package com.social.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Uved extends JpaRepository<com.social.notification.entity.Uved, Long> {
}
