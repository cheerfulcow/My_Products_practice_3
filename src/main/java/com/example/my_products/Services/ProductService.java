package com.example.my_products.Services;

import com.example.my_products.models.Product;
import com.example.my_products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //указываем, что по умолчанию все запросы у нас на чтение
public class ProductService {

    //первое поле = ссылка на интерфейс репозитория
    private ProductRepository productRepository;

    //внедряем конструктор
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ------ Методы для работы с продуктами ------ //

    //метод для получения списка всех продуктов
    public List<Product> getAllProduct (){
        return productRepository.findAll();
    }

    //Метод для получения продукта по id
    public Product getProductById(int id) {
        //Optional = контейнер, хранящий в себе значения. Если значение не будет найдено, то будет храниться Null
        //.findById равнятеся sql запросу: select * from user_site whee id = {id};
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    //Метод для добавления продукта в таблицу базы данных
    // переопределяем транзакцию, т.к. изначально у нас в классе она readOnly
    @Transactional
    //Тип (добавление) указывать не нужно, Spring сам поймёт что происходит в методе и выберет нужную
    public void productAdd (Product product) {
        //.save(product) равняется SQL запросу: insert into product (наименование столбцов) values (значения)
        productRepository.save(product);
    }

    //Метод для изменения продукта
    @Transactional
    public void productEdit (int id, Product product) {
        product.setId(id);
        productRepository.save(product); //Spring увидит, что ему приходит на вход id и поймёт, что нужно обновить, а не сохранить данные пользователя
    }

    //Метод для удаления продукта
    @Transactional
    public void productDelete (int id) {
        // это соответствует SQL запросу: DELETE FROM PRODUCT WHERE ID = {id}
        productRepository.deleteById(id);
    }


}
