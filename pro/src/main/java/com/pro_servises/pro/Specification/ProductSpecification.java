package com.pro_servises.pro.Specification;

import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> filterByCategoryAndPriceAndName(Category category, Double minPrice, Double maxPrice, String name) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction(); // Starting with true (no filters applied yet)

            // Filter by category
            if (category != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
            }

            // Filter by price range
            if (minPrice != null && maxPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
            }

            // Search by name (case insensitive)
            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return predicate;
        };
    }
}
