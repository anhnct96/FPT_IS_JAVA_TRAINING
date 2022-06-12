package vn.fis.training;


import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;
import vn.fis.training.service.SimpleCustomerService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class CustomerManagementApplication
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        SimpleCustomerService simpleCustomerService = new SimpleCustomerService();
        int choosen = 0;
        do {
            System.out.println("-----MENU-----");
            System.out.println(
                    "1. Create customer\n"+
                            "2. Update customer\n"+
                            "3. Delete customer by id\n"+
                            "4. Find customer by id\n"+
                            "5. Find all customer order by name\n"+
                            "6. Find all customer order by name limit\n"+
                            "7. Find all customer by name like order by name asc\n"+
                            "8. Summary customer by age order by age desc\n"+
                            "9. Show all customer\n"+
                            "0. Exit"
            );
            choosen = sc.nextInt();
            sc.nextLine();
            if (choosen == 1) {
                System.out.println("Name: ");
                String name = sc.nextLine();
                System.out.println("Birthday: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthday = LocalDate.parse(sc.nextLine(), formatter);
                System.out.println("Mobile: ");
                String mobile = sc.nextLine();

                Customer customer = new Customer(name,birthday,mobile, CustomerStatus.ACTIVE);
                simpleCustomerService.createCustomer(customer);
            } else if (choosen == 2) {
                System.out.println("Name: ");
                String name = sc.nextLine();
                System.out.println("Birthday: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthday = LocalDate.parse(sc.nextLine(), formatter);
                System.out.println("Mobile: ");
                String mobile = sc.nextLine();

                Customer customer = new Customer(name,birthday,mobile, CustomerStatus.ACTIVE);
                simpleCustomerService.updateCustomer(customer);
            } else if (choosen == 3) {
                System.out.println("id: ");
                String id = sc.nextLine();
                simpleCustomerService.deleteCustomerById(id);
            } else if (choosen == 4) {
                System.out.println("id:");
                String id = sc.nextLine();
                System.out.println(simpleCustomerService.findById(id).toString());
            } else if (choosen == 5) {
                simpleCustomerService.printCustomer(simpleCustomerService.findAllOrderByNameAsc());
            } else if (choosen == 6) {
                System.out.println("limit: ");
                int limit = sc.nextInt();
                simpleCustomerService.printCustomer(simpleCustomerService.findAllOrderByNameLimit(limit));
            } else if (choosen == 7) {
                System.out.println("Customer name: ");
                String custName = sc.nextLine();
                System.out.println("Limit: ");
                String limit = sc.nextLine();
                simpleCustomerService.printCustomer(simpleCustomerService.findAllCustomerByNameLikeOrderByNameAsc(custName, limit));

            } else if (choosen == 8) {
                simpleCustomerService.printDTO(simpleCustomerService.summaryCustomerByAgeOrderByAgeDesc());

            } else if (choosen == 9) {
                simpleCustomerService.printCustomer(simpleCustomerService.getCustomerStore().findAll());
            }
        } while (choosen != 0);
    }
    // TODO: Implement method to test all CustomerService
}
