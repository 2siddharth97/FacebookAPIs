package projects.facebookapis.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;


@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private User createdBy;
    private String content;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @CreatedDate
    private Date createdDate;

}
