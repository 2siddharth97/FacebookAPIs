package projects.facebookapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.facebookapis.models.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
