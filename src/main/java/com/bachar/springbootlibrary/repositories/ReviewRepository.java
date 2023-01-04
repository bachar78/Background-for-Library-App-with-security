package com.bachar.springbootlibrary.repositories;

import com.bachar.springbootlibrary.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBookId(@RequestParam("book_Id") Long bookId, Pageable pageable);

    Review findByEmailAndBookId(String userEmail, Long bookId);
}
