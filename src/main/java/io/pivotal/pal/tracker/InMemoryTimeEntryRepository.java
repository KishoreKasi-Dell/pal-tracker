package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap<Long, TimeEntry>  timeEntriesRepo = new HashMap<>();
    private Long counter =1L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = counter++;
        timeEntry.setId(id);
        timeEntriesRepo.put(id, timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntriesRepo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntriesRepo.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        if (timeEntriesRepo.get(id) != null) {
            timeEntry.setId(id);
            timeEntriesRepo.replace(id, timeEntry);
        }else {
            timeEntry = null;
        }
        return timeEntry;

    }

    @Override
    public void delete(Long id) {
        timeEntriesRepo.remove(id);

    }
}
