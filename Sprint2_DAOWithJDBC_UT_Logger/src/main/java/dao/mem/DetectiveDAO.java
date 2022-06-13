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
        Detective detective = MemoryDataSource.DETECTIVES_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .get();
        return MemoryDataSource.DETECTIVES_LIST.remove(detective);
    }
}
