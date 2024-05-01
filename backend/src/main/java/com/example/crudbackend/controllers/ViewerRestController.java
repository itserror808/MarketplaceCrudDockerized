package com.example.crudbackend.controllers;

import com.example.crudbackend.entities.Viewer;
import com.example.crudbackend.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viewers")
public class ViewerRestController {

    @Autowired
    private ViewerService viewerService;

    @GetMapping
    public ResponseEntity<List<Viewer>> getAllViewers() {
        List<Viewer> viewers = viewerService.getAllViewers();
        return new ResponseEntity<>(viewers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viewer> getViewerById(@PathVariable("id") Long id) {
        Viewer viewer = viewerService.getViewerById(id);
        return viewer != null
                ? new ResponseEntity<>(viewer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Viewer> createViewer(@RequestBody Viewer viewer) {
        Viewer createdViewer = viewerService.createViewer(viewer);
        return new ResponseEntity<>(createdViewer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viewer> updateViewer(@PathVariable("id") Long id, @RequestBody Viewer viewer) {
        viewer.setId(id);
        Viewer updatedViewer = viewerService.updateViewer(viewer);
        return updatedViewer != null
                ? new ResponseEntity<>(updatedViewer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViewer(@PathVariable("id") Long id) {
        viewerService.deleteViewer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}