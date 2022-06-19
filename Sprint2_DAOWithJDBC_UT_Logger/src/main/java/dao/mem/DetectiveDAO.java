package dao.mem;

import dao.IDetectiveDAO;
import model.Detective;

import java.util.*;

public class DetectiveDAO implements IDetectiveDAO {

    @Override
    public void save(Detective detective) {
        MemoryDataSource.DETECTIVES_LIST.add(detective);
    }

    @Override
    public Optional<Detective> get(long id) {
        return MemoryDataSource.DETECTIVES_LIST.stream()
                .filter(detective -> detective.getId() == id)
                .findFirst();
    }

    @Override
    public List<Detective> getAll() {
        return MemoryDataSource.DETECTIVES_LIST;
    }

    @Override
    public boolean update(Detective newDetective) {
        boolean isUpdated = false;
        Optional<Detective> detective = get(newDetective.getId());
        if (detective.isPresent()) {
            Detective updatedDetective = detective.get();
            updatedDetective.replaceWith(newDetective);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Detective detective) {
        return MemoryDataSource.DETECTIVES_LIST.remove(detective);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<Detective> detective = MemoryDataSource.DETECTIVES_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if (detective.isPresent()) {
            return MemoryDataSource.DETECTIVES_LIST.remove(detective);
        }
        else {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        MemoryDataSource.DETECTIVES_LIST.clear();
    }

    @Override
    public int count() {
        return MemoryDataSource.DETECTIVES_LIST.size();
    }
}
