package com.example.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.data.Status;
import com.example.application.data.StatusRepository;

@Service
public class DataService {
    private StatusRepository statusRepository;

    public DataService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> fetchAllCompanies() {
        return statusRepository.findAll();
    }
}
