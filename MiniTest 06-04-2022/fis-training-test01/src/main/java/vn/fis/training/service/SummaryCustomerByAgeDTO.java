package vn.fis.training.service;

public class SummaryCustomerByAgeDTO {
    private long age;
    private long count;
    //TODO: Setters, Getter, Constructors


    public SummaryCustomerByAgeDTO(long age, long count) {
        this.age = age;
        this.count = count;
    }

    public SummaryCustomerByAgeDTO() {
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SummaryCustomerByAgeDTO{" +
                "age=" + age +
                ", count=" + count +
                '}';
    }


}
