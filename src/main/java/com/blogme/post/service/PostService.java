package com.blogme.post.service;

import com.blogme.post.model.Names;
import com.blogme.post.model.Post;
import com.blogme.post.dto.BlogResponseDto;
import com.blogme.post.dto.PostDto;
import com.blogme.post.dto.PostUpdateDto;
import com.blogme.post.exceptionhandler.dto.ResponseMessage;
import com.blogme.post.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }
    public Post getPostById(Long id) {
        Optional<Post> post = findById(id);
        return post.orElse(null);
    }

    public ResponseMessage add(PostDto postDto){
        Post p = mapper.map(postDto, Post.class);
        p.setId(null);
        Names names = new Names(postDto.getFirstName(), postDto.getMiddleName(), postDto.getLastName());
        p.setNames(names);
        p.setCreatedAt(new Date());
        postRepository.save(p);
        return new ResponseMessage("Added successfully!", HttpStatus.CREATED);
    }

    public ResponseMessage update(PostUpdateDto req){
        Optional<Post> postOpt = findById(req.getId());
        if(postOpt.isPresent()){
            Post post = postOpt.get();
            if(!req.getFirstName().isEmpty()) post.getNames().setFirstName(req.getFirstName());
            if(!req.getMiddleName().isEmpty()) post.getNames().setMiddleName(req.getMiddleName());
            if(!req.getLastName().isEmpty()) post.getNames().setLastName(req.getLastName());
            if(!req.getTitle().isEmpty()) post.setTitle(req.getTitle());
            if(!req.getContent().isEmpty()) post.setContent(req.getContent());
            if(!req.getStatus().isEmpty()) post.setStatus(req.getStatus());
            post.setUpdatedAt(new Date());

            postRepository.save(post);
            return new ResponseMessage("Updated successfully!",HttpStatus.OK);

        }
        return new ResponseMessage("Post not found.", HttpStatus.NOT_FOUND);
    }

    public ResponseMessage delete(Long id){
        postRepository.deleteById(id);
        return new ResponseMessage("Deleted successfully!",HttpStatus.OK);
    }

    public List<Post> findAllByIdIn(BlogResponseDto res){
        return postRepository.findAllByIdIn(res.getIds());
    }

    public List<Post> findAllByAuthorId(Long authorId){
        return postRepository.findAllByAuthorId(authorId);
    }

    public void deleteAllByAuthorId(Long authorId){
        postRepository.deleteAllByAuthorId(authorId);
    }
}
