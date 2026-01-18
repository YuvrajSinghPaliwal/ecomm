package com.app.ecomm.config;

import com.app.ecomm.entity.*;
import com.app.ecomm.enums.*;
import com.app.ecomm.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    // ✅ PasswordEncoder for secure passwords
    @Autowired private PasswordEncoder passwordEncoder;

    // ✅ 18 REPOSITORIES
    @Autowired private UserRepo userRepo;
    @Autowired private SellerRepo sellerRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private CategoryRepo categoryRepo;
    @Autowired private OrderRepo orderRepo;
    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired private CartRepo cartRepo;
    @Autowired private CartItemsRepo cartItemsRepo;
    @Autowired private WishListRepo wishListRepo;
    @Autowired private CouponRepo couponRepo;
    @Autowired private VerificationCodeRepo verificationCodeRepo;
    @Autowired private PaymentOrderRepo paymentOrderRepo;
    @Autowired private ReviewRepo reviewRepo;
    @Autowired private AddressRepo addressRepo;
    @Autowired private DealRepo dealRepo;
    @Autowired private HomeCategoryRepo homeCategoryRepo;
    @Autowired private SellerReportRepo sellerReportRepo;
    @Autowired private TransactionRepo transactionRepo;

    private final Random random = new Random();

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepo.count() == 0) {
            System.out.println("🚀 Loading seed data...");
            loadBasicEntities();
            loadComplexEntities();
            System.out.println("✅ DataLoader completed successfully! 🎉");
            System.out.println("📊 Summary:");
            System.out.println("   Users: " + userRepo.count());
            System.out.println("   Sellers: " + sellerRepo.count());
            System.out.println("   Products: " + productRepo.count());
            System.out.println("   Orders: " + orderRepo.count());
            System.out.println("🔐 All passwords securely encoded!");
        } else {
            System.out.println("✅ Seed data already exists. Skipping...");
        }
    }

    private void loadBasicEntities() {
        // 1. CATEGORIES
        if (categoryRepo.count() == 0) {
            List<Category> categories = List.of(
                createCategory("Electronics"),
                createCategory("Fashion"),
                createCategory("Home & Kitchen"),
                createCategory("Books"),
                createCategory("Sports")
            );
            categoryRepo.saveAllAndFlush(categories);
            System.out.println("✅ Loaded " + categories.size() + " categories");
        }

        // 2. SELLERS (SECURE PASSWORD)
        if (sellerRepo.count() == 0) {
            String[] sellerNames = {"TechMart", "FashionHub", "HomeStore", "BookWorld", "SportZone", 
                                   "GadgetPro", "StyleHub", "KitchenKing", "FitLife", "ReadMore"};
            List<Seller> sellers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                sellers.add(createSeller(sellerNames[i], "seller" + (i+1) + "@ecomm.com"));
            }
            sellerRepo.saveAllAndFlush(sellers);
            System.out.println("✅ Loaded " + sellers.size() + " sellers");
        }

        // 3. USERS (SECURE PASSWORD)
        if (userRepo.count() == 0) {
            List<Users> users = new ArrayList<>();
            // 10 Customers
            for (int i = 1; i <= 10; i++) {
                users.add(createUser("customer" + i, "cust" + i + "@ecomm.com", USER_ROLE.CUSTOMER));
            }
            // 10 Sellers 
            for (int i = 1; i <= 10; i++) {
                users.add(createUser("seller" + i, "seller" + i + "@ecomm.com", USER_ROLE.SELLER));
            }
            userRepo.saveAllAndFlush(users);
            System.out.println("✅ Loaded " + users.size() + " users");
        }

        // 4. PRODUCTS
        if (productRepo.count() == 0) {
            List<Category> categories = categoryRepo.findAll();
            List<Seller> sellers = sellerRepo.findAll();
            List<Product> products = new ArrayList<>();
            
            String[] productNames = {"iPhone 15 Pro", "Samsung Galaxy S24", "MacBook Air", "Dell XPS", 
                                   "Nike Air Max", "Levi's Jeans", "Ray-Ban Sunglasses", "Adidas Jacket",
                                   "IKEA Sofa", "Philips Mixer", "Penguin Classics", "Sports Bike"};
            
            for (int i = 0; i < 20; i++) {
                products.add(createProduct(productNames[i % productNames.length] + " #" + (i+1), 
                                        categories.get(i % 5), sellers.get(i % 10)));
            }
            productRepo.saveAllAndFlush(products);
            System.out.println("✅ Loaded " + products.size() + " products");
        }

        // 5. COUPONS
        if (couponRepo.count() == 0) {
            String[] couponCodes = {"WELCOME10", "FIRST20", "SAVE30", "FLASH50", "NEWUSER15", 
                                   "SHOP100", "DEAL25", "BIGSAVE40", "ORDER50", "GET10OFF"};
            List<Coupon> coupons = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                coupons.add(createCoupon(couponCodes[i], 10 + i * 4, 300 + i * 100));
            }
            couponRepo.saveAllAndFlush(coupons);
            System.out.println("✅ Loaded " + coupons.size() + " coupons");
        }
    }

    private void loadComplexEntities() {
        List<Users> users = userRepo.findAll();
        List<Product> products = productRepo.findAll();
        List<Seller> sellers = sellerRepo.findAll();

        createOrders(users.subList(0, 10), products, sellers);
        createCarts(users.subList(0, 10), products);
        createWishLists(users.subList(0, 10), products);
        createVerificationCodes(users, sellers);
        createReviews(products, users.subList(0, 10));
        createHomeCategories(products);
        createSellerReports(sellers);
        System.out.println("✅ Loaded complex entities (orders, carts, reviews...)");
    }

    // 🔐 SECURE USER PASSWORD
    private Users createUser(String username, String email, USER_ROLE role) {
        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode("ecomm123"));  // ✅ SECURELY ENCODED
        user.setMobile("98765" + random.nextInt(10000));
        user.setRole(role);
        return user;
    }

    // 🔐 SECURE SELLER PASSWORD
    private Seller createSeller(String name, String email) {
        Seller seller = new Seller();
        seller.setSellerName(name);
        seller.setEmail(email);
        seller.setPassword(passwordEncoder.encode("seller123"));  // ✅ SECURELY ENCODED
        seller.setMobile("98766" + random.nextInt(10000));
        seller.setRole(USER_ROLE.SELLER);
        seller.setAccountStatus(ACCOUNT_STATUS.ACTIVE);
        seller.setEmailVerified(true);
        seller.setGSTIN("27AA" + random.nextInt(10000000) + "C1Z5");

        // Embedded objects
        BusinessDetails biz = new BusinessDetails();
        biz.setBusinessName(name + " Enterprises");
        biz.setBusinessAddress("123 " + name + " Street, Mumbai");
        seller.setBusinessDetails(biz);

        BankDetails bank = new BankDetails();
        bank.setAccountNumber("2000" + random.nextInt());
        bank.setIfscCode("HDFC000" + random.nextInt(1000));
        bank.setAccountHolderName(name + " Pvt Ltd");
        seller.setBankDetails(bank);

        Address pickup = new Address();
        pickup.setName(name + " Pickup");
        pickup.setAddress("Pickup Address");
        pickup.setCity("Mumbai");
        pickup.setState("Maharashtra");
        pickup.setPincode("400001");
        pickup.setMobile(seller.getMobile());
        seller.setPickupAddress(pickup);

        return seller;
    }

    // Rest of methods unchanged...
    private Category createCategory(String name) {
        Category cat = new Category();
        cat.setName(name);
        return cat;
    }

    private Product createProduct(String title, Category category, Seller seller) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription("Premium product by " + seller.getSellerName());
        product.setSellingPrice(1500 + random.nextInt(5000));
        product.setMrpPrice((int)(product.getSellingPrice() * 1.3));
        product.setDiscountPercentage(25);
        product.setQuantity(100 - random.nextInt(50));
        product.setColor(random.nextBoolean() ? "Black" : "White");
        product.setSizes("S,M,L,XL");
        product.setNumRating(45 + random.nextInt(56));
        product.setCategory(category);
        product.setSeller(seller);
        product.setCreatedAt(LocalDate.now().minusDays(random.nextInt(60)));
        product.setImages(new ArrayList<>(List.of("img1.jpg", "img2.jpg")));
        return product;
    }

    private Coupon createCoupon(String code, double discount, double minOrderValue) {
        Coupon coupon = new Coupon();
        coupon.setCode(code);
        coupon.setDiscount(discount);
        coupon.setMinOrderVlaue(minOrderValue);
        coupon.setValidityStartDate(LocalDate.now().minusDays(5));
        coupon.setValidityEndDate(LocalDate.now().plusMonths(2));
        coupon.setActive(true);
        return coupon;
    }

    // ... rest of complex entity methods remain the same
    private void createOrders(List<Users> customers, List<Product> products, List<Seller> sellers) {
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOrderId("ORD-" + String.format("%05d", i + 1));
            order.setUser(customers.get(i % 10));
            order.setSellerId(sellers.get(i % 10).getId());
            
            List<OrderItem> items = new ArrayList<>();
            for (int j = 0; j < 2 + random.nextInt(2); j++) {
                OrderItem item = new OrderItem();
                item.setProduct(products.get((i * 2 + j) % products.size()));
                item.setQuantity(1 + random.nextInt(2));
                item.setPrice(item.getProduct().getSellingPrice());
                item.setOrder(order);
                items.add(item);
            }
            order.setOrderItems(items);

            int totalSelling = items.stream().mapToInt(it -> it.getPrice() * it.getQuantity()).sum();
            order.setTotalSellingPrice(totalSelling);
            order.setTotalMrpPrice(totalSelling * 1.3);
            order.setDiscount((int)(order.getTotalMrpPrice() - totalSelling));
            order.setTotalItems(items.size());
            order.setOrderStatus(ORDER_STATUS.DELIVERED);
            order.setPaymentStatus(PAYMENT_STATUS.COMPLETED);
            order.setOrderDate(LocalDateTime.now().minusDays(10));

            orderRepo.saveAndFlush(order);
        }
    }

    private void createCarts(List<Users> users, List<Product> products) {
        for (Users user : users) {
            Cart cart = new Cart();
            cart.setUser(user);
            Set<CartItems> items = new HashSet<>();
            for (int j = 0; j < 2; j++) {
                CartItems item = new CartItems();
                item.setProduct(products.get(random.nextInt(products.size())));
                item.setQuantity(1 + random.nextInt(2));
                item.setCart(cart);
                items.add(item);
            }
            cart.setCartItems(items);
            cartRepo.saveAndFlush(cart);
        }
    }

    private void createWishLists(List<Users> users, List<Product> products) {
        for (Users user : users) {
            WishList wishlist = new WishList();
            wishlist.setUser(user);
            Set<Product> wishlistProducts = new HashSet<>();
            wishlistProducts.add(products.get(random.nextInt(products.size())));
            wishlistProducts.add(products.get(random.nextInt(products.size())));
            wishlist.setProducts(wishlistProducts);
            wishListRepo.saveAndFlush(wishlist);
        }
    }

    private void createVerificationCodes(List<Users> users, List<Seller> sellers) {
        List<VerificationCode> codes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VerificationCode vc = new VerificationCode();
            vc.setEmail(users.get(i).getEmail());
            vc.setOtp("1234" + (i + 1));
            vc.setUser(users.get(i));
            codes.add(vc);
        }
        verificationCodeRepo.saveAllAndFlush(codes);
    }

    private void createReviews(List<Product> products, List<Users> users) {
        for (int i = 0; i < 10; i++) {
            Review review = new Review();
            review.setRating(3 + random.nextInt(2));
            review.setReviewText("Great product! " + (4 + random.nextInt(2)) + " stars");
            review.setProduct(products.get(i % products.size()));
            review.setUser(users.get(i % users.size()));
            reviewRepo.saveAndFlush(review);
        }
    }

    private void createHomeCategories(List<Product> products) {
        List<HomeCategory> categories = List.of(
            createHomeCategory("Top Deals", List.of(products.get(0), products.get(1))),
            createHomeCategory("New Arrivals", List.of(products.get(2), products.get(3)))
        );
        homeCategoryRepo.saveAllAndFlush(categories);
    }

    private HomeCategory createHomeCategory(String name, List<Product> products) {
        HomeCategory hc = new HomeCategory();
        hc.setName(name);
        hc.setProducts(products);
        return hc;
    }

    private void createSellerReports(List<Seller> sellers) {
        for (Seller seller : sellers) {
            SellerReport report = new SellerReport();
            report.setSeller(seller);
            report.setTotalEarning(50000 + random.nextInt(50000));
            report.setTotalOrders(20 + random.nextInt(30));
            sellerReportRepo.saveAndFlush(report);
        }
    }
}
