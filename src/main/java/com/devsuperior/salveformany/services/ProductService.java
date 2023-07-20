package com.devsuperior.salveformany.services;

import com.devsuperior.salveformany.dto.CategoryDTO;
import com.devsuperior.salveformany.dto.ProductDTO;
import com.devsuperior.salveformany.entities.Category;
import com.devsuperior.salveformany.entities.Product;
import com.devsuperior.salveformany.repositories.CategoryRepository;
import com.devsuperior.salveformany.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDTO categoryDTO: dto.getCategories()) {
            Category cat = categoryRepository.getReferenceById(categoryDTO.getId());

            //Category cat = new Category();
            //cat.setId(categoryDTO.getId());
            entity.getCategories().add(cat);
        }
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
}
