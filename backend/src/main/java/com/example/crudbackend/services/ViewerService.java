package com.example.crudbackend.services;

import com.example.crudbackend.entities.Viewer;
import com.example.crudbackend.repositories.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewerService {
    @Autowired
    private ViewerRepository viewerRepository;

    public Viewer createViewer(Viewer viewer) {
        return viewerRepository.save(viewer);
    }

    public List<Viewer> getAllViewers() {
        return viewerRepository.findAll();
    }

    public Viewer getViewerById(Long id) {
        return viewerRepository.findById(id).orElse(null);
    }

    public Viewer updateViewer(Viewer viewer) {
        return viewerRepository.save(viewer);
    }

    public void deleteViewer(Long id) {
        viewerRepository.deleteById(id);
    }

}