package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.exception.InvalidCustomerException;
import vn.fis.training.exception.InvalidCustomerStatusException;
import vn.fis.training.store.InMemoryCustomerStore;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService{

    private InMemoryCustomerStore customerStore = new InMemoryCustomerStore();

    public InMemoryCustomerStore getCustomerStore() {
        return customerStore;
    }

    private boolean isNullOrEmpty(String id) {
        if (id == null) {
            return false;
        }
        if (id.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public void checkDuplicate (Customer customer) {
        if (customerStore
                .findAll()
                .stream()
                .anyMatch(cust -> cust.getMobile().equals(customer.getMobile()))) {
            throw new DuplicateCustomerException(customer, "This phone number is already registed for another customer");
        }
    }

    public void validate(Customer customer) {
        if (!isNullOrEmpty(customer.getName())) {
            throw new InvalidCustomerException(customer,"Name is empty");
        }

        if (!isNullOrEmpty(customer.getBirthDay().toString())) {
            throw new InvalidCustomerException(customer, "Birthday is empty");
        }

        if (!isNullOrEmpty(customer.getMobile())) {
            throw new InvalidCustomerException(customer,"Phone number is empty");
        }

        if (!isNullOrEmpty(customer.getStatus().toString())) {
            throw new InvalidCustomerStatusException(customer,"Status is empty");
        }

        if (!customer.getName().matches("^[a-zA-Z ]{5,50}$")) {
            throw new InvalidCustomerException(customer, "Customer's name does not match the requirement! Name must have from 5 to 50 characters. Only accept a-z, A-Z and the spaces");
        }

        if (customer.getAge() < 10.0) {
            System.out.println(customer.getAge());
            throw new InvalidCustomerException(customer,"The customer age has to be over 10 years old");
        }

        if (!customer.getMobile().matches("^[0]( *[\\d] *){9}$")) {
            throw new InvalidCustomerException(customer, "Customer's phone number does not match the requirement! Phone numner must have 10 characters. Only accept 0-9, blanks, the first number must be 0");
        }

        // standardized name field
        String name = customer.getName().trim().toLowerCase();
        customer.setName(standardizeName(name));

        // standardized mobile field
        String mobile = customer.getMobile();
        String trueFormatMobile = mobile.replaceAll("\\s+","");
        customer.setMobile(trueFormatMobile);
    }

    public String standardizeName(String name) {
        String[] words = name.split("\\s+");
        String capitalizedWord = "";
        for (int i = 0 ; i < words.length ; i++) {
            if (i== words.length-1) {
                capitalizedWord += words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
            else {
                capitalizedWord += words[i].substring(0, 1).toUpperCase() + words[i].substring(1)+" ";
            }
        }
        return capitalizedWord;
    }

    public Customer findByPhone(String phone) {
        if (!isNullOrEmpty(phone)) {
            throw new IllegalArgumentException("Phone number not exist");
        }
        ArrayList<Customer> customerList = (ArrayList<Customer>) customerStore.findAll();
        Customer cus = customerList.stream()
                .filter(customer -> customer.getMobile()
                        .equals(phone))
                .findAny()
                .orElse(null);
        return cus;
    }
    @Override
    public Customer findById(String id) {
        if (!isNullOrEmpty(id)) {
            throw new IllegalArgumentException("This ID is not existed");
        }
        ArrayList<Customer> customerList = (ArrayList<Customer>) customerStore.findAll();
        Customer cus = customerList.stream()
                .filter(customer -> customer.getId()
                        .equals(id))
                .findAny()
                .orElse(null);
        return cus;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        validate(customer);
        if (customerStore.getMapCustomer() == null) {
            customerStore.insertOrUpdate(customer);
        }
        else {
            checkDuplicate(customer);
            customerStore.insertOrUpdate(customer);
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        validate(customer);
        Customer customerInDB = findByPhone(customer.getMobile());
        if (customerInDB == null) {
            createCustomer(customer);
        }
        else {
            customerInDB.setName(customer.getName());
            customerInDB.setBirthDay(customer.getBirthDay());
            customerInDB.setCreateDateTime(customer.getCreateDateTime());
        }

        return customer;
    }

    @Override
    public void deleteCustomerById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        if (!isNullOrEmpty(id)) {
            throw new IllegalArgumentException("Can not delete because of null id");
        }
        Customer cus = findById(id);
        if (cus.getStatus() == CustomerStatus.ACTIVE) {
            throw new InvalidCustomerStatusException(cus, "Can not delete ACTIVE customer");
        }
        else {
            customerStore.deleteById(id);
        }
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        ArrayList<Customer> customerListByNameAsc = (ArrayList<Customer>) customerStore
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .collect(Collectors.toList());
        return customerListByNameAsc;
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        ArrayList<Customer> customerListByNameLimit = (ArrayList<Customer>) customerStore
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .limit(limit)
                .collect(Collectors.toList());
        return customerListByNameLimit;
    }

    @FunctionalInterface
    public interface x {
        List<Customer> findAllCustomerByNameLike(String customerName);
    }

    public List<Customer> findAllCustomerByNameLike(String customerName) {
        String standardizeName = standardizeName(customerName);
        int lengthOfName = standardizeName.length();
        List<Customer> tmp = new ArrayList<>();
        for (Customer customer: customerStore.findAll()) {
            if (customer.getName().contains(standardizeName)) {
                tmp.add(customer);
            }
        }
        return tmp;
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return findAllCustomerByNameLike(custName)
                .stream()
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .limit(Integer.parseInt(limit))
                .collect(Collectors.toList());
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return customerStore
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(Customer::getAge, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    SummaryCustomerByAgeDTO s = new SummaryCustomerByAgeDTO();
                    s.setAge(e.getKey());
                    s.setCount(e.getValue());
                    return s;
                }).collect(Collectors.toList());
    }

    public void printCustomer(List<Customer> list) {
        for (Customer customer: list) {
            System.out.println(customer.toString());
        }
    }

    public void printDTO(List<SummaryCustomerByAgeDTO> list) {
        for (SummaryCustomerByAgeDTO s : list ) {
            System.out.println(s.toString());
        }
    }
}
