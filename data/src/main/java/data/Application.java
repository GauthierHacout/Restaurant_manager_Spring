package data;


import core.model.*;
import core.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("core.config");
        final ProductService productService = context.getBean(ProductService.class);
        final RestaurantService restaurantService = context.getBean(RestaurantService.class);
        final TableService tableService = context.getBean(TableService.class);
        final OrderService orderService = context.getBean(OrderService.class);
        final OrderItemService orderItemService = context.getBean(OrderItemService.class);
        cleanDB(restaurantService, tableService, productService, orderService, orderItemService);
        final Map<String, Restaurant> restaurants = registerRestaurants(restaurantService);
        final Map<Integer, Table> tables = registerTables(restaurants, tableService);
        final Map<Integer, Order> orders = registerOrders(tables, orderService);
        final Map<String, Product> products = registerProduct(restaurants, productService);
        registerOrderItems(orders, products, orderItemService);
    }

    private static void cleanDB(final RestaurantService restaurantService, final TableService tableService,
                                final ProductService productService, final OrderService orderService,
                                final OrderItemService orderItemService) {
        orderItemService.deleteAll();
        orderService.deleteAll();
        tableService.deleteAll();
        productService.deleteAll();
        restaurantService.deleteAll();
    }

    private static Map<String, Restaurant> registerRestaurants(final RestaurantService restaurantService) {
        Map<String, Restaurant> restaurants = new HashMap<String, Restaurant>();
        restaurants.put("demo", createRestaurant("Demo", restaurantService));
        restaurants.put("Hippopotamus", createRestaurant("Hippopotamus", restaurantService));
        return restaurants;
    }

    private static Restaurant createRestaurant(String name, final RestaurantService restaurantService) {
        System.out.println("Creating Restaurant : "+name);
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurantService.save(restaurant);
        return restaurant;
    }

    private static Map<Integer, Table> registerTables(Map<String, Restaurant> restaurants, final TableService tableService) {
        Map<Integer, Table> tables = new HashMap<Integer, Table>();
        tables.put(1, createTable(true,1, 4, restaurants.get("demo"), tableService));
        tables.put(2, createTable(false,2, 6, restaurants.get("demo"), tableService));
        tables.put(3, createTable(false,3, 4, restaurants.get("demo"), tableService));
        tables.put(4, createTable(false,4, 8, restaurants.get("demo"), tableService));
        tables.put(5, createTable(false,5, 3, restaurants.get("demo"), tableService));
        return tables;
    }


    private static Table createTable(Boolean occupied, int number, int numberOfSeats, final Restaurant restaurant, final TableService tableService) {
        System.out.println("Creating Table : "+number + "in Restaurant : "+restaurant.getName());
        Table table = new Table();
        table.setNumber(number);
        table.setNumberOfSeats(numberOfSeats);
        table.setOccupied(occupied);
        table.setRestaurant(restaurant);
        tableService.save(table);
        return table;
    }

    private static Map<Integer, Order> registerOrders(Map<Integer, Table> tables, final OrderService orderService) {
        Map<Integer, Order> orders = new HashMap<Integer, Order>();
        orders.put(1, createOrder(true, tables.get(1), orderService));
        orders.put(2, createOrder(false, tables.get(1), orderService));
        return orders;
    }


    private static Order createOrder(Boolean active, final Table table, final OrderService orderService) {
        System.out.println("Creating Order in Table : "+table.getId());
        Order order = new Order();
        order.setActive(active);
        order.setTable(table);
        orderService.save(order);
        return order;
    }

    private static Map<String, Product> registerProduct(Map<String, Restaurant> restaurants, final ProductService productService) {
        Map<String, Product> products = new HashMap<String, Product>();
        products.put("Salad", createProduct("Salad", 7.5, ProductType.STARTER, restaurants.get("demo"), productService));
        products.put("Water", createProduct("Water", 3, ProductType.DRINK, restaurants.get("demo"), productService));
        products.put("Steak", createProduct("Steak", 11, ProductType.COURSE, restaurants.get("demo"), productService));
        products.put("Fish", createProduct("Fish", 10.5, ProductType.COURSE, restaurants.get("demo"), productService));
        products.put("Ice cream", createProduct("Ice cream", 4, ProductType.DESSERT, restaurants.get("demo"), productService));
        return products;
    }

    private static Product createProduct(String name, double price, ProductType type, Restaurant restaurant, final ProductService productService) {
        System.out.println("Creating Product : "+name);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setProductType(type);
        product.setRestaurant(restaurant);
        productService.save(product);
        return product;
    }

    private static void registerOrderItems(Map<Integer, Order> orders, Map<String, Product> products, final OrderItemService orderItemService) {
        createOrderItem(orders.get(1), products.get("Steak"), 2, orderItemService);
        createOrderItem(orders.get(1), products.get("Water"), 3, orderItemService);
        createOrderItem(orders.get(1), products.get("Ice cream"), 1, orderItemService);
        createOrderItem(orders.get(1), products.get("Salad"), 1, orderItemService);
    }

    private static void createOrderItem(Order order, Product product, Integer amount, final OrderItemService orderItemService){
        System.out.println("Creating OrderItem in Order : "+order.getId());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setAmount(amount);
        orderItem.setTotalPrice(product.getPrice()*amount);
        orderItemService.save(orderItem);
    }
}
