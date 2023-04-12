package com.example.my_products.models;

import com.example.my_products.enumm.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;

@Entity (name = "product") // помечаем класс как модель для связи с БД
public class Product {

    @Id // помечаем, что это поле являтся Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автогенерация (автоинкремент) ID
    private int id;
    @NotBlank(message = "Поле не должно быть пустым") //удаляет пробелы перед первым символом и смотрит, чтобы остаток был не пустой
    @Size (min = 3, max = 50, message = "Наименование должно содержать от 3 до 50 символов")
    @Pattern(regexp = "(\\D{2,})+([a-zA-Zа-яА-Я0-9 ]*)", message = "Введите корректное наименование товара")  //первые две буквы, далее любое количество буквы/цифры и "_"
    @Column(name="Наименование товара", length = 50, nullable = false, columnDefinition = "text")
    private String name;

    //@Digits: принимаем только цифры: целая часть - не более 7 цифр, дробная часть - не более 2 значащих цифр (12.500 - проходит, 12.501 -нет).
    //также не принимает пустое значение; и перед проверкой использует удаление пробелов
    @Digits(integer = 7,fraction = 2, message = "Введите цену товара с дробной частью в формате 0000.00")
    @Positive(message = "Цена не должна быть нулевой")
    @Column(name ="Цена, руб.", length = 10, nullable = false, columnDefinition = "float")
    private float price;

    @Digits(integer = 7,fraction = 3, message = "Укажите вес товара в килограммах, с дробной частью в формате 00.000")
    @Positive(message = "Вес не должен быть нулевой")
    @Column(name="Вес товара, кг", length = 11, nullable = false, columnDefinition = "float")
    private float weight;

    //@NotNull(message = "Поле не должно быть пустым") // в данном проекте бесполезно, т.к. все провайдеры константы и
    // обязательно кто-то по-умолчанию выбран. Но для масштабирования оставим :))
    //   @Column(name = "Поставщик", length = 50, nullable = false, columnDefinition = "text")
// провайдеры будут отдельно в таблице
    private Provider provider;

    // дополнительно создаем пустой конструктор для метода newProduct контроллера ProductController
    public Product() {
    }

    public Product(int id, String name, float price, float weight, Provider provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight='" + weight + '\'' +
                ", provider=" + provider +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
