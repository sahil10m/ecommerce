package com.example.ecomerce.service;

import com.example.ecomerce.domain.*;
import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.dto.ProductReviewDTO;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.*;
import com.example.ecomerce.util.GetAuthenticationUserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final ProductReviewRepository productReviewRepository;

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductReviewRepository productReviewRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productReviewRepository = productReviewRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List<ProductDTO> getAllProducts() {
//        return productRepository.findAll();
        List<Product> productList = productRepository.findAll();
        List<Product> availableProducts = productList.stream().filter(Product::getStatus).collect(Collectors.toList());
        List<ProductDTO> availableProductsDto = productList.stream().map(e -> toProductDto(e)).collect(Collectors.toList());
        return availableProductsDto;
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> haveProduct = productRepository.findById(id);
        if(haveProduct.isPresent()){
            ProductDTO productDTO = toProductDto(haveProduct.get());
            return productDTO;
        }
        throw new RecordNotFoundException(String.format("no Record found on id ==> %d", id));
    }

    public ProductDTO createProduct(ProductDTO productDto) {
        productDto.setStatus(Boolean.TRUE);
        productDto.setReviews(new ArrayList<>());
        Long userId = GetAuthenticationUserId.getId();
        productDto.setUserId(userId);

        Product savedProduct = productRepository.save(toProductDo(productDto));
        ProductDTO createProductDto = toProductDto(savedProduct);
        return createProductDto;
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> haveProduct = productRepository.findById(id);
        if (haveProduct.isPresent()) {
            haveProduct.get().setUser(product.getUser());
            haveProduct.get().setProductName(product.getProductName());
            haveProduct.get().setCategory(product.getCategory());
            haveProduct.get().setProductName(product.getProductName());
            haveProduct.get().setPrice(product.getPrice());
            return productRepository.save(haveProduct.get());
        }
        throw new RecordNotFoundException(String.format("no Record found on id ==> %d", id));
    }

    public ProductDTO deleteProduct(Long id) {
        Optional<Product> haveProduct = productRepository.findById(id);
        if (haveProduct.isPresent()) {
            haveProduct.get().setStatus(false);
            productRepository.save(haveProduct.get());
            return toProductDto(haveProduct.get());
        }
        throw new RecordNotFoundException(String.format("no Record found on id ==> %d", id));
    }

    public List<ProductDTO> getProductsByCategory(Long id) {
        List<Product> productList = productRepository.getProductsByCategory(id);
        if(!productList.isEmpty()){
            List<ProductDTO> productDTO = productList.stream().map(e -> toProductDto(e)).collect(Collectors.toList());
            return productDTO;
        }
        throw new RecordNotFoundException(String.format("no record found on category id  ==> %d", id));
    }

    public List<ProductReview> getReviewsByProductId(Long id) {
        Optional<Product> haveProduct = productRepository.findById(id);
        if (haveProduct.isPresent()) {
            return haveProduct.get().getReview();
        }
        throw new RecordNotFoundException(String.format("record not found on id ==> %d", id));
    }

    public ProductDTO createReview(Long id, ProductReviewDTO productReviewDto) {

        productReviewDto.setCreatedAt(LocalDateTime.now());
//        Optional<User> haveUser = userRepository.findById(productReviewDto.getUserId());
        Long userId = GetAuthenticationUserId.getId();
        Optional<Product> haveProduct = productRepository.findById(id);

        if(haveProduct.isPresent()){
            productReviewDto.setProduct(id);
            Product pro = haveProduct.get();

            ProductReview pr = toProductReviewDo(productReviewDto);
            pr.setProduct(pro);

            pro.getReview().add(pr);


            Product savedProduct = productRepository.save(pro);
            ProductDTO pDTO = toProductDto(savedProduct);

            return pDTO;
        }
        throw new RecordNotFoundException(String.format("record not found on id ==> %d", haveProduct.get().getId()));
    }

    public List<ProductDTO> showCart() {
        Long id = GetAuthenticationUserId.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("user not found")));
        List<Cart> cart = cartRepository.findByCreatedBy(user);


        List<Product> productList = new ArrayList<>();

            for (Cart cart1 : cart) {
                productList.addAll(cart1.getProductList());
            }
            List<ProductDTO> productDTOList = productList.stream().map(e -> toProductDto(e)).collect(Collectors.toList());

            return productDTOList;

    }

    public Product toProductDo(ProductDTO productDTO) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productDTO.getCategory());
        Optional<User> haveUser = userRepository.findById(productDTO.getUserId());
        List<ProductReview> productReview = productDTO.getReviews().stream().map(e -> toProductReviewDo(e)).collect(Collectors.toList());

        if (productCategory.isPresent()) {
            return Product.builder()
                    .productName(productDTO.getProductName())
                    .user(haveUser.get())
                    .category(productCategory.get())
                    .price(productDTO.getPrice())
                    .status(productDTO.getStatus())
                    .review(productReview)
                    .build();
        } else {
            throw new RecordNotFoundException(String.format("record not found => %d", productDTO.getCategory()));
        }
    }

    public ProductDTO toProductDto(Product product) {
//            Long id = GetAuthenticationUserId.getId();
            List<ProductReview> productReviewList = product.getReview();
            if(product.getReview() != null) {
                List<ProductReviewDTO> productReviewDTOList = productReviewList.stream().map(e -> toProductReviewDto(e)).collect(Collectors.toList());

                return ProductDTO.builder()
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .status(product.getStatus())
                        .category(product.getCategory().getId())
                        .reviews(productReviewDTOList)
//                        .userId(id)
                        .build();
            } else {
                return ProductDTO.builder()
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .status(product.getStatus())
                        .category(product.getCategory().getId())
//                        .userId(product.getUser().getId())
                        .build();
            }
    }

    public ProductReviewDTO toProductReviewDto(ProductReview productReview) {
        ProductReviewDTO productReviewDto = ProductReviewDTO.builder()
                .id(productReview.getId())
                .review(productReview.getReview())
                .product(productReview.getProduct().getId())
                .createdAt(productReview.getCreatedAt())
                .build();
        return productReviewDto;
    }

    public ProductReview toProductReviewDo(ProductReviewDTO productReviewDTO) {

        Long id = GetAuthenticationUserId.getId();
        User haveUser = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("record not found on id ==> %d", id)));

            ProductReview productReview = ProductReview.builder()
                    .review(productReviewDTO.getReview())
                    .user(haveUser)
                    .createdAt(productReviewDTO.getCreatedAt())
                    .build();


            return productReview;
    }

}


