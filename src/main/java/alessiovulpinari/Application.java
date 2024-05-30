package alessiovulpinari;

import alessiovulpinari.entities.Customer;
import alessiovulpinari.entities.Order;
import alessiovulpinari.entities.Product;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {


        Supplier<Customer> customerSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            Random random = new Random();
            return new Customer(faker.name().fullName(), random.nextInt(1, 5));
        };


        List<Customer> customerList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            customerList.add(customerSupplier.get());
        }

        Random random = new Random();

        Order order1 = new Order("In consegna", LocalDate.now(), LocalDate.now().plusDays(4), getListOfProduct(5), customerList.get(random.nextInt(0, customerList.size())));
        Order order2 = new Order("Non consegnato", LocalDate.now(), LocalDate.now().plusDays(5), getListOfProduct(3), customerList.get(random.nextInt(0, customerList.size())));
        Order order3 = new Order("In preparazione", LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), getListOfProduct(2), customerList.get(random.nextInt(0, customerList.size())));
        Order order4 = new Order("In preparazione", LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), getListOfProduct(10), customerList.get(random.nextInt(0, customerList.size())));

        Order[] orderArray = {order1, order2, order3, order4};
        List<Order> orders = Arrays.stream(orderArray).toList();

        // -------------------------------------------------------------- ESERCIZIO 1 -------------------------------------------------------------------------------------------

        System.out.println("-------------------------------------------------------------- ESERCIZIO 1 -------------------------------------------------------------------------------------------");
        System.out.println();
        Map<Customer, List<Order>> customerListMap = orders.stream().collect(Collectors.groupingBy(Order::getCustomer));
        customerListMap.forEach((customer, orders1) -> System.out.println(customer + " \n\t" + orders1 + " \n"));

        // -------------------------------------------------------------- ESERCIZIO 2 -------------------------------------------------------------------------------------------

        System.out.println("-------------------------------------------------------------- ESERCIZIO 2 -------------------------------------------------------------------------------------------");
        System.out.println();

        Map<Customer, Double> customerSumListMap = orders.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order -> Order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
        customerSumListMap.forEach(((customer, aDouble) -> System.out.println("Name: " + customer.getName() + " \n" + "Prezzo totale:" + aDouble)));

        System.out.println("-------------------------------------------------------------- ESERCIZIO 3 -------------------------------------------------------------------------------------------");
        System.out.println();

        List<Product> listOfProduct = getListOfProduct(10);
        OptionalDouble maxPrice = listOfProduct.stream().mapToDouble(Product::getPrice).max();

        if (maxPrice.isPresent()) {
            System.out.println(maxPrice.getAsDouble());
        } else {
            System.out.println("Lista vuota!");
        }

        System.out.println("-------------------------------------------------------------- ESERCIZIO 4 -------------------------------------------------------------------------------------------");
        System.out.println();

        
    }


    public static List<Product> getListOfProduct(int num) {
        Supplier<Product> productSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            Random random = new Random();
            return new Product(faker.commerce().productName(), faker.commerce().department(), random.nextDouble(0, 2000));
        };

        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            productList.add((productSupplier.get()));
        }

        return productList;
    }
}

