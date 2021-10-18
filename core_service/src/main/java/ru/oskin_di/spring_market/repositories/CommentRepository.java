package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oskin_di.spring_market.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
