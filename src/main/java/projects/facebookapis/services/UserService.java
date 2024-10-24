package projects.facebookapis.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.facebookapis.models.Follow;
import projects.facebookapis.models.User;
import projects.facebookapis.repositories.FollowRepository;
import projects.facebookapis.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private FollowRepository followRepository;

    @Autowired
    public UserService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;

    }

    public User createUser(String username, String password) {
        User user = new User();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            throw new RuntimeException("User Already Exists with that username");
        }
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void followUser(String follower, String followed) {
        Optional<User> userOptional = userRepository.findByUsername(followed);
        if (userOptional.isPresent()) {
            User followedUser = userOptional.get();
            Follow follow = new Follow();
            follow.setFollowed(followedUser);
            follow.setFollower(userRepository.findByUsername(follower).get());
            followRepository.save(follow);
        } else{
            throw new RuntimeException("User Not Exists with that username");
        }
    }
    public void unfollowUser(String follower, String followed) {
        Optional<User> followedUserOptional = userRepository.findByUsername(followed);
        if (followedUserOptional.isPresent()) {
            User followedUser = followedUserOptional.get();
            User followerUser = userRepository.findByUsername(follower).get();
            Follow follow = followRepository.findByFollowerAndFollowed(followerUser, followedUser).get();
            followRepository.delete(follow);
        } else{
            throw new RuntimeException("User Not Exists with that username");
        }
    }
}
