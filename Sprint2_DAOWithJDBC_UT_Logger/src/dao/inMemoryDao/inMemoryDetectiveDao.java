package dao.inMemoryDao;

import model.Detective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inMemoryDetectiveDao implements iInMemoryDao<Detective> {
    private Map<Long, Detective> detectiveMap = new HashMap<>();

    public Map<Long, Detective> getDetectiveMap() {
        return detectiveMap;
    }

    public void setDetectiveMap(Map<Long, Detective> detectiveMap) {
        this.detectiveMap = detectiveMap;
    }

    @Override
    public Detective insertOrUpdate(Detective detective) {
        detectiveMap.put(detective.getId(), detective);
        return detective;
    }

    @Override
    public List<Detective> findAll() {
        return new ArrayList<>(detectiveMap.values());
    }

    @Override
    public void deleteById(Long id) {
        detectiveMap.remove(id);
    }
}
