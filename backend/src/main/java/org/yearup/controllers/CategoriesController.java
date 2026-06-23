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
public class CategoriesController
{
    private CategoryService cs;
    private ProductService ps;

    public CategoriesController(CategoryService cs, ProductService ps) {
        this.cs = cs;
        this.ps = ps;
    }




    // create an Autowired constructor to inject the categoryService and productService

    // add the appropriate annotation for a get action
    @GetMapping
    public List<Category> getAll(){

        // find and return all categories
        return cs.getAllCategories();
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Category category = cs.getById(id);
        // get the category by id

        if(category == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId

        return ps.listByCategoryId(categoryId);
    }

    // add annotation to call this method for a POST action
    @PostMapping
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category)
    {
        Category addCategory = cs.create(category);

        // insert the category and return it with status 201 Created
        return ResponseEntity.status(201).body(addCategory);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        Category updated = cs.update(id, category);
        return ResponseEntity.ok(updated);
    }



    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id)
    {
        // delete the category by id and return status 204 No Content
        cs.delete(id);

        return ResponseEntity.noContent().build();
    }
}
