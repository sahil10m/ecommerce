package com.example.ecomerce;

import com.example.ecomerce.domain.Product;
import com.example.ecomerce.domain.ProductCategory;
import com.example.ecomerce.domain.Roles;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.repository.ProductCategoryRepository;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.repository.RolesRepository;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class EcommerceApplication{

//	@Autowired
//	RolesRepository rolesRepository;
//
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Autowired
//	ProductCategoryRepository productCategoryRepository;
//
//	@Autowired
//	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Roles customer = Roles.builder()
//				.name("ROLE_CUSTOMER")
//				.build();
//		Roles admin = Roles.builder()
//				.name("ROLE_ADMIN")
//				.build();
//
//		rolesRepository.save(customer);
//		rolesRepository.save(admin
//		);
//
//		ProductCategory shampoo = ProductCategory.builder()
//				.categoryName("shampoo")
//				.createdAt(LocalDateTime.now())
//				.status(true)
//				.build();
//		productCategoryRepository.save(shampoo);
//
//		Product headAndShoulders = Product.builder()
//				.category(shampoo)
//				.productName("head&shoulders")
//				.price(2.14)
//				.status(true)
//				.build();
//		productRepository.save(headAndShoulders);
//
//		User adminShezy = User.builder()
//				.username("shezy@gmail.com")
//				.user("shezy")
//				.password(bCryptPasswordEncoder.encode("12345"))
//				.roles(rolesRepository.getRolesByName("ROLE_ADMIN"))
//				.build();
//
//		userRepository.save(adminShezy);
//	}
}
