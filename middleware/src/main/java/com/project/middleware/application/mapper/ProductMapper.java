package com.project.middleware.application.mapper;

import com.project.middleware.adapter.in.web.dto.ProductDetailResponse;
import com.project.middleware.adapter.out.client.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDetailResponse toResponse(ProductDTO product);
}