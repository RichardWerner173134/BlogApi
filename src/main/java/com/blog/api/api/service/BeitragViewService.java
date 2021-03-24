package com.blog.api.api.service;

import com.blog.api.api.model.BeitragView;
import com.blog.api.api.repository.BeitragViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeitragViewService {
    @Autowired
    private BeitragViewRepository beitragViewRepository;

    public void addView(BeitragView beitragView){
        beitragViewRepository.save(beitragView);
    }

}
