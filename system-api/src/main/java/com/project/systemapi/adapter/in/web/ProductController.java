package com.project.systemapi.adapter.in.web;

import com.project.systemapi.adapter.in.web.dto.ProductDTO;
import com.project.systemapi.application.port.in.CreateProductUseCase;
import com.project.systemapi.application.service.ProductService;
import com.project.systemapi.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.net.URI;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createUC;
    private final ProductService service;

    public ProductController(CreateProductUseCase createUC,
                             ProductService service) {
        this.createUC = createUC;
        this.service = service;
    }

    @GetMapping
    public String getName() {
      return "Hi bro Microservices";
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest req) {
        Product p = createUC.create(req.getName(), req.getPrice());

        return ResponseEntity
                .created(URI.create("/api/v1/products/" + p.id()))
                .body(p);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}


//    @GetMapping("/{id}")
//    public ProductDTO get(@PathVariable UUID id) {
//        Product product = getProductUseCase.getProduct(id);
//        return new ProductDTO(
//                product.id(),
//                product.name(),
//                product.price(),
//                product.status()
//        );
//    }

