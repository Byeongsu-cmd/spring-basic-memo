package org.example.springbasicmemo.repository;

import org.example.springbasicmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
