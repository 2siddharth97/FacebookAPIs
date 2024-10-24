package projects.facebookapis.factories;

import org.springframework.stereotype.Component;
import projects.facebookapis.builders.PostBuilder;
import projects.facebookapis.models.Post;
import projects.facebookapis.models.PostType;
import projects.facebookapis.models.User;

import java.util.Date;

@Component
public class PostFactory {
    public Post createPost(User user, String postType, String content, String title) {
        switch (postType.toUpperCase()){
            case "TEXT": return new PostBuilder().withCreatedBy(user).withContent(content).withTitle(title).withPostType(PostType.TEXT).withCreatedDate(new Date()).build();
            case "IMAGE": return new PostBuilder().withCreatedBy(user).withContent(content).withTitle(title).withPostType(PostType.IMAGE).withCreatedDate(new Date()).build();
            case "VIDEO": return new PostBuilder().withCreatedBy(user).withContent(content).withTitle(title).withPostType(PostType.VIDEO).withCreatedDate(new Date()).build();
            default:
                throw new IllegalArgumentException("Unknown Post Type");
        }
    }
}
