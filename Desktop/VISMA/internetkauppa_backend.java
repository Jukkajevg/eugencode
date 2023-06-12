public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructors, getters, and setters
}

// cart

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    // Other methods like getTotalPrice(), clearCart(), etc.
}

// user

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    // Constructors, getters, and setters
}

// product repository 

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
}

// user repository 

public interface UserRepository {
    User getUserByEmail(String email);
    void addUser(User user);
    // Other methods like updateUser(), deleteUser(), etc.
}


// product service 

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }
}
 // user service 
 
 public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

}

