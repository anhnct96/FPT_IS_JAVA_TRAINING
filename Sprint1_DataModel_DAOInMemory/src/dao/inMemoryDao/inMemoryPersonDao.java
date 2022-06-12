package dao.inMemoryDao;

import model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class inMemoryPersonDao implements iInMemoryDao<Person>{
    private Map<Long, Person> personMap = new HashMap<>();

    public Map<Long, Person> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(Map<Long, Person> personMap) {
        this.personMap = personMap;
    }

    @Override
    public Person insertOrUpdate(Person person) {
        personMap.put(person.getId(),person);
        return person;
    }

    @Override
    public List findAll() {
        return new ArrayList<>(personMap.values());
    }

    @Override
    public void deleteById(Long id) {
        personMap.remove(id);
    }
}
