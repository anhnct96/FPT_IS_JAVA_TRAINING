package dao.mem;

import dao.ITrackEntryDAO;
import model.CriminalCase;
import model.TrackEntry;

import java.util.*;

public class TrackEntryDAO implements ITrackEntryDAO {

    @Override
    public void save(TrackEntry trackEntry) {
        MemoryDataSource.TRACK_ENTRY_LIST.add(trackEntry);
    }

    @Override
    public Optional<TrackEntry> get(long id) {
        return MemoryDataSource.TRACK_ENTRY_LIST.stream()
                .filter(trackEntry -> trackEntry.getId() == id)
                .findFirst();
    }

    @Override
    public List<TrackEntry> getAll() {
        return MemoryDataSource.TRACK_ENTRY_LIST;
    }

    @Override
    public boolean update(TrackEntry newTrackEntry) {
        boolean isUpdated = false;
        Optional<TrackEntry> trackEntry = get(newTrackEntry.getId());
        if (trackEntry.isPresent()) {
            TrackEntry updatedTrackEntry = trackEntry.get();
            updatedTrackEntry.replaceWith(newTrackEntry);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(TrackEntry trackEntry) {
        return MemoryDataSource.TRACK_ENTRY_LIST.remove(trackEntry);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<TrackEntry> trackEntry = MemoryDataSource.TRACK_ENTRY_LIST.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if (trackEntry.isPresent()) {
            return MemoryDataSource.TRACK_ENTRY_LIST.remove(trackEntry.get());
        }
        else {
            return false;
        }
    }

    @Override
    public int count() {
        return MemoryDataSource.TRACK_ENTRY_LIST.size();
    }

    public void deleteAll() {
        MemoryDataSource.TRACK_ENTRY_LIST.clear();
    }
}
