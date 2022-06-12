package dao.mem;

import dao.ICriminalCaseDAO;
import model.CriminalCase;

import java.util.*;

public class CriminalCaseDAO implements ICriminalCaseDAO {

    @Override
    public void delete(long id) {
        for(CriminalCase criminalCase : MemoryDataSource.CRIMINAL_CASES_LIST) {
            if (criminalCase.getId() == id){
                MemoryDataSource.CRIMINAL_CASES_LIST.remove(criminalCase);
                return;
            }
        }
    }

    @Override
    public void save(CriminalCase criminalCase) {
        MemoryDataSource.CRIMINAL_CASES_LIST.add(criminalCase);
    }

    @Override
    public Optional<CriminalCase> get(long id) {
        return MemoryDataSource.CRIMINAL_CASES_LIST.stream()
                .filter(criminalCase -> criminalCase.getId() == id)
                .findFirst();
        //return Optional.ofNullable(MemoryDataSource.CRIMINAL_CASES_LIST.get((int)id));
    }

    @Override
    public List<CriminalCase> getAll() {
        return MemoryDataSource.CRIMINAL_CASES_LIST;
    }

    @Override
    public boolean update(CriminalCase newCriminalCase) {
        Optional<CriminalCase> criminalCase = get(newCriminalCase.getId());
        boolean isUpdated = false;
        if(criminalCase.isPresent()){
            CriminalCase updatedCriminalCase = criminalCase.get();
            updatedCriminalCase.replaceWith(newCriminalCase);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(CriminalCase criminalCase) {
        return MemoryDataSource.CRIMINAL_CASES_LIST.remove(criminalCase);
    }

    @Override
    public boolean deleteById(long id) {
        CriminalCase criminalCase = MemoryDataSource.CRIMINAL_CASES_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .get();
        return MemoryDataSource.CRIMINAL_CASES_LIST.remove(criminalCase);
    }
}
