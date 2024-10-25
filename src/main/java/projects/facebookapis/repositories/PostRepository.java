package projects.facebookapis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projects.facebookapis.models.Post;
import projects.facebookapis.models.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByCreatedBy(User user);

    Page<Post> findAllByCreatedBy(User user, Pageable pageable);

    Page<Post> findAllByCreatedByIsIn(List<User> users, Pageable pageable);
}
