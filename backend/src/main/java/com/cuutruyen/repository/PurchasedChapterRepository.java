package com.cuutruyen.repository;

import com.cuutruyen.entity.PurchasedChapter;
import com.cuutruyen.entity.PurchasedChapterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedChapterRepository extends JpaRepository<PurchasedChapter, PurchasedChapterId> {
}
