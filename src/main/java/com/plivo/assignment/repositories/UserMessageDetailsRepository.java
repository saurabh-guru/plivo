package com.plivo.assignment.repositories;


import com.plivo.assignment.models.UserMessageDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageDetailsRepository extends JpaRepository<UserMessageDetails,Long> {
    //UserMessageDetails searchByName(String name);
    Page<UserMessageDetails> findAll(Pageable pageable);

    Page<UserMessageDetails> findByName(String name, Pageable pageable);
}
