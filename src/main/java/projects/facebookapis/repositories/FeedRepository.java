package projects.facebookapis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import projects.facebookapis.models.Feed;
import projects.facebookapis.models.Post;

import java.awt.print.Pageable;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
