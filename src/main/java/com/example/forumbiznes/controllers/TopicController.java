package com.example.forumbiznes.controllers;

import com.example.forumbiznes.services.TopicServiceImpl;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TopicController {
    @EJB
    TopicServiceImpl topicService;
}
