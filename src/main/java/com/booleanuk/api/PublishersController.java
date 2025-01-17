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
@RequestMapping("publishers")
public class PublishersController {
    private ArrayList<Publisher> publishers;

    public PublishersController() {
        this.publishers = new ArrayList<>();

        this.publishers.add(new Publisher("Penguin", "London"));
        this.publishers.add(new Publisher("Other publisher", "New York"));
    }

    @GetMapping
    public ArrayList<Publisher> getAll() {
        return this.publishers;
    }

    @GetMapping("/{id}")
    public Publisher getOne(@PathVariable(name = "id") int id) {
        if (id < this.publishers.size() && id >= 0) {
            return this.publishers.get(id);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher create(@RequestBody Publisher publisher) {
        this.publishers.add(publisher);
        return publisher;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher update(@PathVariable(name = "id") int id, @RequestBody Publisher publisher) {
        if (id < this.publishers.size()) {
            this.publishers.get(id).setName(publisher.getName());
            this.publishers.get(id).setCity(publisher.getCity());
            return this.publishers.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Publisher delete(@PathVariable(name = "id") int id) {
        if (id < this.publishers.size()) {
            return this.publishers.remove(id);
        }
        return null;
    }
}
