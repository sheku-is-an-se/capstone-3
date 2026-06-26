package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.service.CategoryService;
import org.yearup.service.ProductService;

import java.util.List;

@RestController
@RequestMapping({"/categories"})
@CrossOrigin
public class CategoriesController {
    private CategoryService cs;
    private ProductService ps;

    public CategoriesController(CategoryService cs, ProductService ps) {
        this.cs = cs;
        this.ps = ps;
    }


    // Returns all categories
    @GetMapping
    public List<Category> getAll() {
        // find and return all categories
        return cs.getAllCategories();
    }

    // Returns a single category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Category category = cs.getById(id);
        // get the category by id
        //If empty return the correct error code
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // Returns all products in a category
    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return ps.listByCategoryId(categoryId);
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // Creates a new category, admin only
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addCategory = cs.create(category);

        // insert the category and return it with status 201 Created
        return ResponseEntity.status(201).body(addCategory);
    }

    // Updates an existing category by ID, admin only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category updated = cs.update(id, category);
        return ResponseEntity.ok(updated);
    }


    // Deletes a category by ID, admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        // delete the category by id and return status 204 No Content
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }
}
