package projects.facebookapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.facebookapis.models.Follow;
import projects.facebookapis.models.User;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowed(User follower, User followed);

    Optional<List<Follow>> findByFollower(User follower);


}
