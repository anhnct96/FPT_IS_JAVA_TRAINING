package dao.mem;

import dao.IEvidenceDAO;
import model.Evidence;

import java.util.*;

public class EvidenceDAO implements IEvidenceDAO {


    @Override
    public void save(Evidence evidence) {
        MemoryDataSource.EVIDENCE_LIST.add(evidence);
    }

    @Override
    public Optional<Evidence> get(long id) {
        return MemoryDataSource.EVIDENCE_LIST.stream()
                .filter(evidence -> evidence.getId() == id)
                .findFirst();
    }

    @Override
    public List<Evidence> getAll() {
        return MemoryDataSource.EVIDENCE_LIST;
    }

    @Override
    public boolean update(Evidence newEvidence) {
        boolean isUpdated = false;
        Optional<Evidence> evidence = get(newEvidence.getId());
        if(evidence.isPresent()) {
            Evidence updatedEvidence = evidence.get();
            updatedEvidence.replaceWith(newEvidence);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Evidence evidence) {
        return MemoryDataSource.EVIDENCE_LIST.remove(evidence);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<Evidence> evidence = MemoryDataSource.EVIDENCE_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if (evidence.isPresent()) {
            return MemoryDataSource.EVIDENCE_LIST.remove(evidence);
        }
        else {
            return false;
        }
    }

    @Override
    public int count() {
        return MemoryDataSource.EVIDENCE_LIST.size();
    }

    public void deleteAll() {
        MemoryDataSource.EVIDENCE_LIST.clear();
    }
}
