package com.example.crudbackend.controllers;

import com.example.crudbackend.entities.Viewer;
import com.example.crudbackend.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/viewers")
public class ViewerController {
    @Autowired
    private ViewerService viewerService;

    @GetMapping
    public String getViewersPage(Model model) {
        model.addAttribute("viewers", viewerService.getAllViewers());
        return "viewers";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("viewer", new Viewer());
        return "create-viewer";
    }

    @PostMapping
    public String createViewer(@ModelAttribute("viewer") Viewer viewer) {
        viewerService.createViewer(viewer);
        return "redirect:/viewers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Viewer viewer = viewerService.getViewerById(id);
        model.addAttribute("viewer", viewer);
        return "edit-viewer";
    }

    @PostMapping("/update/{id}")
    public String updateViewer(@PathVariable("id") Long id, @ModelAttribute("viewer") Viewer viewer) {
        viewer.setId(id);
        viewerService.updateViewer(viewer);
        return "redirect:/viewers";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteViewer(@PathVariable("id") Long id) {
        viewerService.deleteViewer(id);
        return "redirect:/viewers";
    }




}