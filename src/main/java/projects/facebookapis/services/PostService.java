package projects.facebookapis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.facebookapis.factories.PostFactory;
import projects.facebookapis.models.Post;
import projects.facebookapis.models.User;
import projects.facebookapis.repositories.PostRepository;
import projects.facebookapis.repositories.UserRepository;

import java.util.Optional;

@Service
public class PostService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private PostFactory postFactory;

    @Autowired
    public PostService(PostRepository postRepository, PostFactory postFactory, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postFactory = postFactory;
        this.userRepository = userRepository;
    }

    public Post createPost(Long userId, String postType, String title, String content){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("user not found");
        }
        User user = userOptional.get();
        try{
            Post post = postFactory.createPost(user, postType, content, title);
            return postRepository.save(post);
        } catch (RuntimeException e) {
            throw new RuntimeException("Post type is not correct");
        }
    }

    public void deletePost(Long postId){
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()){
            throw new RuntimeException("post not found");
        }
        Post post = postOptional.get();
        postRepository.delete(post);
        System.out.println("Post successfully deleted");
    }
}
