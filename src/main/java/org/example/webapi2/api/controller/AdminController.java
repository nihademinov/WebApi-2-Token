package org.example.webapi2.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.CategoryDto;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.service.AdminService;
import org.example.webapi2.service.CategoryService;
import org.example.webapi2.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
public class AdminController {

    //fixme yazilan servisin sirf authorization ferqi yaratmaq ucun dizayn olundugunu nezere alaraq
    // adlandirma ve servis bolgusunu gormezden gele bilerik ancaq asagidaki commentleri nezerden kecirmek lazimdir

    //fixme servisler ancaq userlerin management ile baglidir, bu UserController olmalidir
    // ancaq role check servislerin uzerinde ferqlene biler,
    // eyni servis     @PreAuthorize("hasAnyRole('ADMIN','USER')") bu formada authorize ola biler

    private final AdminService adminService;
    private final ProductService productService;
    private final CategoryService categoryService;

    //fixme constructoru annotation ile create ede bilerik, daha dinamic ve clean olacaq
    public AdminController(AdminService adminService, ProductService productService, CategoryService categoryService) {
        this.adminService = adminService;
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    //fixme endpoint bu cur adlandirilmamalidir,
    // User servisinde @RequestMapping deye naming bu cur olmalidir
    // api/users
    // servislerde ise @GetMapping de user listi gelecekse elave endpoint qeyd olunmur yeni:
    // api/users endpointi Users listini qaytarmalidir, api/users/allUsers -e ehtiyac yoxdur
    // specific user ise /api/users/{userId} olaraq dizayn oluna biler
    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    // specific user ise /api/users/{userId} olaraq dizayn oluna biler
    @GetMapping("/getUserById/{id}")
    //fixme ResponseEntity ye ehtiyac yoxdur
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.getUsersById(id), HttpStatus.OK);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateUser/{id}")
    //fixme http method tipi duzgun secildiyi muddetce /updateUser/{id} adlandirmasina ehtiyac yoxdur
    // @PutMapping("/{userId}") kifayetdir -> /api/users/{userId} PUT
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(adminService.updateUser(id, userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme http method tipi duzgun secildiyi muddetce /deleteUser/{id} adlandirmasina ehtiyac yoxdur
    // @DeleteMapping("/{userId}") kifayetdir -> /api/users/{userId} DEL
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUserById(id);
        return new ResponseEntity<>(id + "User is deleted ", HttpStatus.OK);
    }


    //fixme ProductController yaradib /api/products kimi adlandirmaq lazimdir
    //Product Crud operations.


    @PreAuthorize("hasRole('ADMIN')")

    //fixme GET /api/products GET bize list of product vermelidir
    @GetMapping("/allProducts")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme GET /api/products/{productId} bize specific product return etmelidir
    @GetMapping("/getProductById/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme POST /api/products yeni product yaratmage kifayet etmelidir
    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>("Product successfully created", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme PUT /api/products/{productId} bize movcud product u update etmeye kifayet etmelidir
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme DEL /api/products/{productId} bize movcud productu silmeye kifayet etmelidir
    @DeleteMapping("/deletePoduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
    }

    //fixme Category servisi yaradilib /api/categories kimi adlandirilsin,
    // yuxarida qeyd edilen naming convention lar nezere alinsin

    //Category Crud operations.

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allCategory")
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createCategory")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return new ResponseEntity<>("Category successfully created", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.updateCategoy(id, categoryDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Category successfully deleted", HttpStatus.OK);
    }


}
