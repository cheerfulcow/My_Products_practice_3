package com.example.my_products.controllers;

import com.example.my_products.Services.ProductService;
import com.example.my_products.models.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //даннный метод позволяет получить список всех продуктов и вернуть html страницу
    @GetMapping("/product")
    public String index(Model model) {
        model.addAttribute("product", productService.getAllProduct());
        return "product";
    }

    // Данный метод позволяет получить объект из листа по id
    @GetMapping("/product/{id}")
    public String infoProduct (@PathVariable("id") int id, Model model)  {
        model.addAttribute("product", productService.getProductById(id));
        return "product_info";
    }

    //Данный метод позволяет отобразить представление с формой добавление товара
    @GetMapping("/product/add")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    // Данный метод позволяет принять данные с формы и сохранить товар в лист
    @PostMapping("/product/add")
    public String newProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult ) {
        // пишем условие: если у проверяемого объекта не прошли валидацию поля, то в возвращаем страницу с добавлением продукта для корректировки
        if(bindingResult.hasErrors()){
            return "add_product";
        }
        productService.productAdd(product);
        return "redirect:/product";
    }

    // Данный метод позволяет получить редактируемый продукт по id и вернуть форму редактирования продукта
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit_product", productService.getProductById(id));
        return "edit_product";
    }

    // данный метод позволяет принять редактируемый объект с формы и обновить информацию о редактируемом товаре
    @PostMapping("/product/edit/{id}")
    public String edit_Product(@ModelAttribute("edit_product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "edit_product";
        }
        productService.productEdit(id, product);
        return "redirect:/product";
    }

    //Метод для удаления продукта
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.productDelete(id);
        return "redirect:/product";
    }
}


