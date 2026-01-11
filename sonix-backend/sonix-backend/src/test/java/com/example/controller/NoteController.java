package com.example.sonixbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")   // 👈 ADD THIS LINE
public class NoteController {

    @Autowired
    private NoteRepository repo;

    // CREATE NOTE
    @PostMapping
    public Note create(@RequestBody Note note) {
        return repo.save(note);
    }

    // GET ALL NOTES
    @GetMapping
    public List<Note> getAll() {
        return repo.findAll();
    }

    // DELETE NOTE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "deleted";
    }
}
