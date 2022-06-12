package dao.mem;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataSource {
    public static final List<Detective> DETECTIVES_LIST = new ArrayList();
    public static final List<CriminalCase> CRIMINAL_CASES_LIST = new ArrayList();
    public static final List<Evidence> EVIDENCE_LIST = new ArrayList<>();
    public static final List<Storage> STORAGE_LIST = new ArrayList<>();
    public static final List<TrackEntry> TRACK_ENTRY_LIST = new ArrayList<>();
}
