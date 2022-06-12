package dao.inMemoryDao;

import model.TrackEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inTrackEntryDao implements iInMemoryDao<TrackEntry> {

    private Map<Long, TrackEntry> trackEntryMap = new HashMap<>();

    public Map<Long, TrackEntry> getTrackEntryMap() {
        return trackEntryMap;
    }

    public void setTrackEntryMap(Map<Long, TrackEntry> trackEntryMap) {
        this.trackEntryMap = trackEntryMap;
    }

    @Override
    public TrackEntry insertOrUpdate(TrackEntry trackEntry) {
        trackEntryMap.put(trackEntry.getId(), trackEntry);
        return trackEntry;
    }

    @Override
    public List<TrackEntry> findAll() {
        return new ArrayList<>(trackEntryMap.values());
    }

    @Override
    public void deleteById(Long id) {
        trackEntryMap.remove(id);
    }
}
