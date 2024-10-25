package projects.facebookapis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import projects.facebookapis.models.Feed;
import projects.facebookapis.models.Follow;
import projects.facebookapis.models.Post;
import projects.facebookapis.models.User;
import projects.facebookapis.repositories.FeedRepository;
import projects.facebookapis.repositories.FollowRepository;
import projects.facebookapis.repositories.PostRepository;
import projects.facebookapis.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.min;

@Service
public class FeedService {
    private final FollowRepository followRepository;
    private FeedRepository feedRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, UserRepository userRepository,
                       FollowRepository followRepository, PostRepository postRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.postRepository = postRepository;
    }

    public Optional<Feed> getFeed(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Feed feed = new Feed();
            feed.setUser(user);
            Optional<List<Follow>> followOptionalList = followRepository.findByFollower(user);
            if (followOptionalList.isPresent()) {
                List<Post> posts = new ArrayList<>();
                List<Follow> follows = followOptionalList.get();
                for (Follow follow : follows) {
                    User followedUser = follow.getFollowed();
                    Optional<List<Post>> followedUserPostsOptional = postRepository.findAllByCreatedBy(followedUser);
                    if (followedUserPostsOptional.isPresent()) {
                        posts.addAll(followedUserPostsOptional.get());
                    }
                }
                feed.setPosts(posts);
                return Optional.of(feed);
            } else {
                return Optional.empty();
            }

        } else {
            throw new RuntimeException("This user for which feed was requested does not exist");
        }
    }

    public Feed getFeedPaginated(String username, int page, int size) {
        //Optional<Feed> feedOptional = getFeed(username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        List<User> followedUsers = new ArrayList<>();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<List<Follow>> followsOptional = followRepository.findByFollower(user);
            if (followsOptional.isPresent()) {
                List<Follow> follows = followsOptional.get();
                for(Follow follow : follows) {
                    User followedUser = follow.getFollowed();
                    followedUsers.add(followedUser);
                }
            }
        }
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> postPage= postRepository.findAllByCreatedByIsIn(followedUsers, pageRequest);
        /*
        if (feedOptional.isPresent()) {
            Feed feed = feedOptional.get();
            Feed f = new Feed();
            f.setUser(userRepository.findByUsername(username).get());
            List<Post> posts = new ArrayList<>();
            for (int i = size * (page); i < min((size * (page + 1)), feed.getPosts().size()); i++) {
                posts.add(feed.getPosts().get(i));
            }
            f.setPosts(posts);
            return f;
        }*/
        List<Post> paginatedPostList = postPage.getContent();
        Feed feedPaginated = new Feed();
        feedPaginated.setUser(userOptional.get());
        feedPaginated.setPosts(paginatedPostList);
        return feedPaginated;
    }
}
