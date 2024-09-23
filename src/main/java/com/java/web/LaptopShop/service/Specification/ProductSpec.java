package com.java.web.LaptopShop.service.Specification;

import java.util.List;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.jpa.domain.Specification;

import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.domain.Product_;

public class ProductSpec {
    public static Specification<Product> factoryIn(List<String> factory) {
        return (root, query, criteria) -> criteria.in(root.get(Product_.FACTORY)).value(factory);
    }

    public static Specification<Product> targetIn(List<String> target) {
        return (root, query, criteria) -> criteria.in(root.get(Product_.TARGET)).value(target);
    }

    public static Specification<Product> priceBetween(int min, int max) {
        return (root, query, criteria) -> criteria.between(root.get(Product_.PRICE), min, max);
    }

    public static Specification<Product> priceIn(List<String> prices) {
        Specification<Product> spec = (root, query, criteria) -> criteria.or(null);
        int min = 0, max = 0;
        for (String price : prices) {
            switch (price) {
                case "duoi-10-trieu":
                    max = 10000000;
                    min = 0;
                    break;

                case "10-15-trieu":
                    max = 15000000;
                    min = 10000000;
                    break;

                case "15-20-trieu":
                    max = 20000000;
                    min = 15000000;
                    break;

                case "tren-20-trieu":
                    max = Integer.MAX_VALUE;
                    min = 20000000;

                    break;
            }
            if (min != 0 || max != 0) {
                spec = spec.or(priceBetween(min, max));
            }
        }
        return spec;
    }

}