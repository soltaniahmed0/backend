package com.example.Backend.Services;

import com.example.Backend.Entity.Category;
import com.example.Backend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

    public void addcat(Category cat ){
        categoryRepository.save(cat);
        }
    public void deleteCat(int id){
        categoryRepository.deleteById(id);
    }
    public Category getCat(int id) {
        return  categoryRepository.findById(id).orElse(null);
    }

    public Category update(Category cat) {
        Category existingCat=getCat(cat.getCat_id());
        existingCat.setName(cat.getName());
        existingCat.setImg(cat.getImg());
        return categoryRepository.save(existingCat);


    }


}
