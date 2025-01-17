package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("authors")
public class AuthorsController {
    private ArrayList<Author> authors;

    public AuthorsController() {
        this.authors = new ArrayList<>();

        this.authors.add(new Author("JRR Tolkien", "jrr@tolkien.com"));
        this.authors.add(new Author("Jane Austen", "jane@austen.com"));
    }

    @GetMapping
    public ArrayList<Author> getAll() {
        return this.authors;
    }

    @GetMapping("/{id}")
    public Author getOne(@PathVariable(name = "id") int id) {
        if (id < this.authors.size() && id >= 0) {
            return this.authors.get(id);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author) {
        this.authors.add(author);
        return author;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Author update(@PathVariable(name = "id") int id, @RequestBody Author author) {
        if (id < this.authors.size()) {
            this.authors.get(id).setName(author.getName());
            this.authors.get(id).setEmail(author.getEmail());
            return this.authors.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Author delete(@PathVariable(name = "id") int id) {
        if (id < this.authors.size()) {
            return this.authors.remove(id);
        }
        return null;
    }
}
