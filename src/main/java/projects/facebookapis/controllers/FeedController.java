package projects.facebookapis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projects.facebookapis.models.Feed;
import projects.facebookapis.services.FeedService;

import java.util.Optional;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("/{username}")
    public ResponseEntity getFeed(@PathVariable String username) {
        try{
            Optional<Feed> feedOptional = feedService.getFeed(username);
            if(feedOptional.isPresent()) {
                return ResponseEntity.status(200).body(feedOptional.get());
            }
            else{
                return ResponseEntity.status(200).body("There are no posts by anyone you follow");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/paginated/{username}")
    public ResponseEntity getFeedPaginated(@PathVariable String username, @RequestParam int page, @RequestParam int size) {
        Feed feed = feedService.getFeedPaginated(username, page, size);
        if(feed != null) {
            return ResponseEntity.status(200).body(feed);
        }else{
            return ResponseEntity.status(200).body("There are no posts by anyone you follow");
        }
    }
}
