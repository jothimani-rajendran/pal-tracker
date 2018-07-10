package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntryMap = new HashMap<Long, TimeEntry>();
    private long timeEntryId =1l;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(timeEntryId);
        this.timeEntryMap.put(timeEntryId, timeEntry);
        timeEntryId++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return this.timeEntryMap.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
            timeEntry.setId(id);
            this.timeEntryMap.put(id, timeEntry);
            return timeEntry;
    }

    @Override
    public void delete(long id) {
        this.timeEntryMap.remove(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<TimeEntry>();
        this.timeEntryMap.entrySet().stream().forEach(e -> timeEntries.add(e.getValue()));
        return timeEntries;
    }
}
