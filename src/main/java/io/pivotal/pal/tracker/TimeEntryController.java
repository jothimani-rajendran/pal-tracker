package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    @ResponseBody
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        timeEntryRepository.create(timeEntry);

        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (null != timeEntry) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/time-entries")
    @ResponseBody
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();

        if (null != timeEntries) {
            return new ResponseEntity<List<TimeEntry>>(timeEntries, HttpStatus.OK);
        }
        return new ResponseEntity<List<TimeEntry>>(timeEntries, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {

        TimeEntry updateTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (null != updateTimeEntry) {
            return new ResponseEntity<TimeEntry>(updateTimeEntry, HttpStatus.OK);
        }

        return new ResponseEntity<TimeEntry>(updateTimeEntry, HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        if(null == timeEntryRepository.find(id)) {
            return new ResponseEntity<TimeEntry>(new TimeEntry(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<TimeEntry>(new TimeEntry(), HttpStatus.FOUND);
    }

}
