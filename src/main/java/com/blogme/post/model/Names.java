package com.blogme.post.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Names {
    @NotBlank
    private String firstName="";
    private String middleName="";
    @NotBlank
    private String lastName="";
}
