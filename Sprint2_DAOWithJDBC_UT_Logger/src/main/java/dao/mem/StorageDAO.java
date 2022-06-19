package dao.mem;

import dao.IStorageDAO;
import model.CriminalCase;
import model.Storage;

import java.util.*;

public class StorageDAO implements IStorageDAO {

    @Override
    public void save(Storage storage) {
        MemoryDataSource.STORAGE_LIST.add(storage);
    }

    @Override
    public Optional<Storage> get(long id) {
        return MemoryDataSource.STORAGE_LIST.stream()
                .filter(storage -> storage.getId() == id)
                .findFirst();
    }

    @Override
    public List<Storage> getAll() {
        return MemoryDataSource.STORAGE_LIST;
    }

    @Override
    public boolean update(Storage newStorage) {
        boolean isUpdated = false;
        Optional<Storage> storage = get(newStorage.getId());
        if(storage.isPresent()) {
            Storage updatedStorage = storage.get();
            updatedStorage.replaceWith(newStorage);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Storage storage) {
        return MemoryDataSource.STORAGE_LIST.remove(storage);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<Storage> storage = MemoryDataSource.STORAGE_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if (storage.isPresent()) {
            return MemoryDataSource.STORAGE_LIST.remove(storage.get());
        }
        else {
            return false;
        }
    }

    @Override
    public int count() {
        return MemoryDataSource.STORAGE_LIST.size();
    }

    public void deleteAll() {
        MemoryDataSource.STORAGE_LIST.clear();
    }
}
