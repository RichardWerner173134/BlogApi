package com.blog.api.api.service;

import com.blog.api.api.model.Beitrag;
import com.blog.api.api.repository.BeitragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeitragService {

    @Autowired
    private BeitragRepository beitragRepository;

    public List<Beitrag> getAllBeitraege(){
        return beitragRepository.findAll();
    }

    public Optional<Beitrag> getBeitragById(Long id){
        return beitragRepository.findById(id);
    }

    public void addBeitrag(Beitrag beitrag) {
        beitragRepository.save(beitrag);
    }
}
