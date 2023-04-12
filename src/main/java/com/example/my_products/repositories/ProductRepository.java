package com.example.my_products.repositories;

import com.example.my_products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> { //<Модель, тип данных поля id у модели>
}
