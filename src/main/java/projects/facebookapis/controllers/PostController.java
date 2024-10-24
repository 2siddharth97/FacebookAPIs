package projects.facebookapis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.facebookapis.models.Post;
import projects.facebookapis.models.PostType;
import projects.facebookapis.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestParam Long userId, @RequestBody Post post) {

        String postType = post.getPostType().toString();
        String content = post.getContent();
        String title = post.getTitle();
        postService.createPost(userId, postType, title, content);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePost(@RequestParam Long postId){
        try{
            postService.deletePost(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted post");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
