package com.blogme.post.service;

import com.blogme.post.model.Category;
import com.blogme.post.dto.CategoryDto;
import com.blogme.post.dto.CategoryUpdateDto;
import com.blogme.post.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    public List<CategoryDto> getAll(){
        List<CategoryDto> categoryDtos = categoryRepository.findAll()
                .stream().map(cat->mapper.map(cat,CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    public CategoryDto getById(Long id){
        return mapper.map(categoryRepository.getById(id),CategoryDto.class);
    }

    public void add(CategoryDto categoryDto){
        categoryRepository.save(mapper.map(categoryDto, Category.class));
    }

    public void update(CategoryUpdateDto categoryDto){
        categoryRepository.save(mapper.map(categoryDto,Category.class));
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}
