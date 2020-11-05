package data;


import core.model.*;
import core.service.OrderService;
import core.service.ProductService;
import core.service.RestaurantService;
import core.service.TableService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("core.config");
        final ProductService productService = context.getBean(ProductService.class);
        final RestaurantService restaurantService = context.getBean(RestaurantService.class);
        final TableService tableService = context.getBean(TableService.class);
        final OrderService orderService = context.getBean(OrderService.class);
        cleanDB(restaurantService, tableService, productService, orderService);
        final Map<String, Restaurant> restaurants = registerRestaurants(restaurantService);
        final Map<Integer, Table> tables = registerTables(restaurants, tableService);
        registerOrders(tables, orderService);
        registerProduct(restaurants, productService);
    }

    private static void cleanDB(final RestaurantService restaurantService, final TableService tableService,
                                final ProductService productService, final OrderService orderService) {
        restaurantService.deleteAll();
        tableService.deleteAll();
        productService.deleteAll();
        orderService.deleteAll();
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
        tables.put(1, createTable(1, 4, restaurants.get("demo"), tableService));
        tables.put(2, createTable(2, 6, restaurants.get("demo"), tableService));
        tables.put(3, createTable(3, 4, restaurants.get("demo"), tableService));
        tables.put(4, createTable(4, 8, restaurants.get("demo"), tableService));
        tables.put(5, createTable(5, 3, restaurants.get("demo"), tableService));
        return tables;
    }


    private static Table createTable(int number, int numberOfSeats, final Restaurant restaurant, final TableService tableService) {
        System.out.println("Creating Table : "+number + "in Restaurant : "+restaurant.getName());
        Table table = new Table();
        table.setNumber(number);
        table.setNumberOfSeats(numberOfSeats);
        table.setOccupied(false);
        table.setRestaurant(restaurant);
        tableService.save(table);
        return table;
    }

    private static Map<Integer, Order> registerOrders(Map<Integer, Table> tables, final OrderService orderService) {
        Map<Integer, Order> orders = new HashMap<Integer, Order>();
        orders.put(1, createOrder(tables.get(1), orderService));
        orders.put(2, createOrder(tables.get(3), orderService));
        return orders;
    }


    private static Order createOrder(final Table table, final OrderService orderService) {
        System.out.println("Creating Order in Table : "+table.getId());
        Order order = new Order();
        order.setActive(false);
        order.setTable(table);
        orderService.save(order);
        return order;
    }

    private static void registerProduct(Map<String, Restaurant> restaurants, final ProductService productService) {
        createProduct("Salad", 7.5, ProductType.STARTER, restaurants.get("demo"), productService);
        createProduct("Water", 3, ProductType.DRINK, restaurants.get("demo"), productService);
        createProduct("Steak", 11, ProductType.COURSE, restaurants.get("demo"), productService);
        createProduct("Fish", 10.5, ProductType.COURSE, restaurants.get("demo"), productService);
        createProduct("Ice cream", 4, ProductType.DESSERT, restaurants.get("demo"), productService);
    }

    private static void createProduct(String name, double price, ProductType type, Restaurant restaurant, final ProductService productService) {
        System.out.println("Creating Product : "+name);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setProductType(type);
        product.setRestaurant(restaurant);
        productService.save(product);
    }
}
