package projects.facebookapis.builders;

import projects.facebookapis.models.Post;
import projects.facebookapis.models.PostType;
import projects.facebookapis.models.User;

import java.util.Date;

public class PostBuilder {
     User createdBy;
     String content;
     String title;
     PostType postType;
     Date createdDate;

     public PostBuilder withCreatedBy(User createdBy){
         this.createdBy = createdBy;
         return this;
     }

     public PostBuilder withContent(String content){
         this.content = content;
         return this;
     }
     public PostBuilder withTitle(String title){
         this.title = title;
         return this;
     }
     public PostBuilder withPostType(PostType postType){
         this.postType = postType;
         return this;
     }
     public PostBuilder withCreatedDate(Date createdDate){
         this.createdDate = createdDate;
         return this;
     }

     public Post build(){
         Post post = new Post();
         post.setCreatedBy(createdBy);
         post.setContent(content);
         post.setTitle(title);
         post.setPostType(postType);
         post.setCreatedDate(createdDate);
         return post;
     }


}
