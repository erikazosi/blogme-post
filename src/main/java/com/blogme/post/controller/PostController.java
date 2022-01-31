package com.blogme.post.controller;
import com.blogme.post.model.Post;
import com.blogme.post.dto.BlogResponseDto;
import com.blogme.post.dto.PostDto;
import com.blogme.post.dto.PostUpdateDto;
import com.blogme.post.exceptionhandler.dto.ResponseMessage;
import com.blogme.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> add(@Valid @RequestBody PostDto postDto){
        ResponseMessage responseMessage = postService.add(postDto);
        return new ResponseEntity<>(responseMessage,responseMessage.getStatus());
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> update(@Valid @RequestBody PostUpdateDto postDto)
    {
        ResponseMessage responseMessage = postService.update(postDto);
        return new ResponseEntity<>(responseMessage,responseMessage.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id){
        ResponseMessage responseMessage= postService.delete(id);
        return new ResponseEntity<>(responseMessage,responseMessage.getStatus());
    }

    @PostMapping("/tag")
    public List<Post> findAllById(@RequestBody BlogResponseDto responseDto){
        return postService.findAllByIdIn(responseDto);
    }

    @GetMapping("/author/{authorId}")
    public List<Post> findAllByAuthorId(@PathVariable Long authorId){
        return postService.findAllByAuthorId(authorId);
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity<ResponseMessage> deleteAllByAuthorId(@PathVariable Long authorId){
        postService.deleteAllByAuthorId(authorId);
        return new ResponseEntity<>(new ResponseMessage("Deleted", HttpStatus.OK), HttpStatus.OK);
    }
}
