package com.blogme.post.repository;

import com.blogme.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByIdIn(List<Long> postIds);

    List<Post> findAllByAuthorId(Long id);

    @Transactional
    @Modifying
    void deleteAllByAuthorId(Long id);
}
