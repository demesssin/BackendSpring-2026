package com.social.feed.repository;

import com.social.feed.entity.SomeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SomeItemRepository extends JpaRepository<SomeItem, Long> {

    List<SomeItem> findByUserIdOrderByReceivedAtDesc(String userId);
}
