package com.blogme.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "posts")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private Long authorId;

    @NotBlank
    private String title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(length = 100)),
            @AttributeOverride(name = "middleName", column = @Column(length = 100)),
            @AttributeOverride(name = "lastName", column = @Column(length = 100))
    })
    @Column(name= "author")
    private Names names;

    @NotBlank
    @Lob
    private String content;

    private Date updatedAt;

    private Date createdAt;

    private String status;

    @ManyToMany(mappedBy = "postList")
    private List<Category> categoryList;

}
