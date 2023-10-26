package com.example.termines.repository;

import com.example.termines.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TermRepository extends JpaRepository<Term, UUID> {
}
