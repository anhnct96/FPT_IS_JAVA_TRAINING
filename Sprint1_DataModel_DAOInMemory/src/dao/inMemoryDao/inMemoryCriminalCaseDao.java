package dao.inMemoryDao;

import model.CriminalCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inMemoryCriminalCaseDao implements iInMemoryDao<CriminalCase> {

    private Map<Long, CriminalCase> criminalCaseMap = new HashMap<>();

    public Map<Long, CriminalCase> getCriminalCaseMap() {
        return criminalCaseMap;
    }

    public void setCriminalCaseMap(Map<Long, CriminalCase> criminalCaseMap) {
        this.criminalCaseMap = criminalCaseMap;
    }

    @Override
    public CriminalCase insertOrUpdate(CriminalCase criminalCase) {
        criminalCaseMap.put(criminalCase.getId(), criminalCase);
        return criminalCase;
    }

    @Override
    public List<CriminalCase> findAll() {
        return new ArrayList<>(criminalCaseMap.values());
    }

    @Override
    public void deleteById(Long id) {
        criminalCaseMap.remove(id);
    }
}
