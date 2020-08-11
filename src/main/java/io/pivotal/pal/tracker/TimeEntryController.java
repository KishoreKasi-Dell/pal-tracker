package io.pivotal.pal.tracker;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntriesRepo = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create( @RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createTimeEntry = timeEntriesRepo.create(timeEntryToCreate);
        return new ResponseEntity<>(createTimeEntry , HttpStatus.CREATED );
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry timeEntry = timeEntriesRepo.find(timeEntryId);
        if(timeEntry != null)
            return new ResponseEntity<>(timeEntry , HttpStatus.OK );
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntriesRepo.list() ,HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updateTimeEntry = timeEntriesRepo.update(timeEntryId,expected);
        if(updateTimeEntry != null)
            return new ResponseEntity<>(updateTimeEntry , HttpStatus.OK );
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        timeEntriesRepo.delete(timeEntryId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
