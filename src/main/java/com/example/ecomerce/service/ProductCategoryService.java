package com.example.ecomerce.service;

import com.example.ecomerce.domain.ProductCategory;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.CustomUserDetails;
import com.example.ecomerce.dto.ProductCategoryDTO;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.ProductCategoryRepository;
import com.example.ecomerce.repository.UserRepository;
import com.example.ecomerce.util.GetAuthenticationUserId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRespository;

    private final UserRepository userRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRespository, UserRepository userRepository) {
        this.productCategoryRespository = productCategoryRespository;
        this.userRepository = userRepository;
    }

    public ProductCategoryDTO saveProductCategory(ProductCategoryDTO productCategoryDTO) {
//        if (productCategoryDTO.getStatus() == Boolean.FALSE) {
//            ProductCategory productCategory = toProductCategoryDO(productCategoryDTO);
//            productCategoryRespository.save(productCategory);
//            ProductCategoryDTO productCategoryDTO1 = toProductCategoryDTO(productCategory);
//            return productCategoryDTO1;
//        } else {

            Long userId = GetAuthenticationUserId.getId();
            productCategoryDTO.setUserId(userId);
            ProductCategory productCategory = toProductCategoryDO(productCategoryDTO);
            productCategoryRespository.save(productCategory);
            ProductCategoryDTO productCategoryDTO1 = toProductCategoryDTO(productCategory);
            return productCategoryDTO1;

    }

    public List<ProductCategoryDTO> getAllProductCategories(){
        List<ProductCategory> productCategory = productCategoryRespository.findAll();
        List<ProductCategoryDTO> productCategoryDTOList = productCategory.stream().filter(e -> e.getStatus()).map(e -> toProductCategoryDTO(e)).collect(Collectors.toList());
        return productCategoryDTOList;
    }

    public String deleteProductCategoryById(Long id){
        Optional<ProductCategory> productCategory = productCategoryRespository.findById(id);
        String name;
        if (productCategory.isPresent()){
            productCategory.get().setStatus(false);
            productCategoryRespository.save(productCategory.get());
            name = productCategory.get().getCategoryName();
            return String.format(name + " category successfully deleted");
        }
        throw new RecordNotFoundException(String.format("no Record found on id ==> %d" , id));
    }

    public ProductCategory updateProductCategoryById(Long id, ProductCategory productCategory){
        Long userId = GetAuthenticationUserId.getId();
        User user = userRepository.findById(userId).get();
        Optional<ProductCategory> haveCategory = productCategoryRespository.findById(id);
        if(haveCategory.isPresent()){
//            haveCategory.get().setUser(productCategory.getUser());
            haveCategory.get().setCategoryName(productCategory.getCategoryName());
            haveCategory.get().setStatus(productCategory.getStatus());
            haveCategory.get().setCreatedAt(LocalDateTime.now());
            haveCategory.get().setUser(user);

            ProductCategory updatedCategory = haveCategory.get();
            return productCategoryRespository.save(updatedCategory);
        }
        throw new RecordNotFoundException(String.format("Category not found on id ==> %d",id));
    }

    public ProductCategory deleteCategoryById(Long id){
        Optional<ProductCategory> haveCategory = productCategoryRespository.findById(id);
        if(haveCategory.isPresent()){
            haveCategory.get().setStatus(false);
        }
        throw new RecordNotFoundException(String.format("record not found ==> %d", id));
    }




    public ProductCategory toProductCategoryDO(ProductCategoryDTO productCategoryDTO){
        User user = userRepository.findById(productCategoryDTO.getUserId()).orElseThrow(() -> new RecordNotFoundException("record not found"));

        return ProductCategory.builder()
                .categoryName(productCategoryDTO.getCategory())
                .createdAt(productCategoryDTO.getCreatedAt())
                .status(productCategoryDTO.getStatus())
                .user(user)
                .build();
    }

    public ProductCategoryDTO toProductCategoryDTO(ProductCategory productCategory){
        return ProductCategoryDTO.builder()
                .category(productCategory.getCategoryName())
                .userId(productCategory.getUser().getId())
                .status(productCategory.getStatus())
                .createdAt(productCategory.getCreatedAt())
                .build();
    }


    public ProductCategoryDTO getProductCategoryByIdCustomer(Long id) {
        ProductCategory productCategory = productCategoryRespository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("Record not found on id ==> %d", id)));
        ProductCategoryDTO productCategoryDTO = toProductCategoryDTO(productCategory);
        return productCategoryDTO;
    }
}
