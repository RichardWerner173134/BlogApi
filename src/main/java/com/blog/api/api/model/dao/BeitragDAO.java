package com.blog.api.api.model.dao;

import com.blog.api.api.model.Beitrag;
import com.blog.api.api.service.BeitragViewService;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeitragDAO {

    @Expose
    private Long id;
    @Expose
    private String title;
    @Expose
    private String content;
    @Expose
    private String author;
    @Expose
    private int views;
    @Expose
    private String creationTime;

    public static List<BeitragDAO> convertBeitragList(List<Beitrag> beitragList, BeitragViewService beitragViewService){
        List<BeitragDAO> list = new ArrayList<>();
        for(Beitrag beitrag : beitragList){
            if(beitrag.getUsername() != null){
                list.add(new BeitragDAO(
                        beitrag.getId(),
                        beitrag.getTitle(),
                        beitrag.getContent(),
                        beitrag.getUsername().getUsername(),
                        beitragViewService.getViewsForBeitrag(beitrag.getId()),
                        beitrag.getCreationTime().toString()
                ));
            } else{
                list.add(new BeitragDAO(
                        beitrag.getId(),
                        beitrag.getTitle(),
                        beitrag.getContent(),
                        "Unbekannt",
                        beitragViewService.getViewsForBeitrag(beitrag.getId()),
                        beitrag.getCreationTime().toString()
                ));
            }
        }
        return list;
    }

    public static BeitragDAO convertBeitrag(Beitrag beitrag, BeitragViewService beitragViewService){
        BeitragDAO beitragDao = new BeitragDAO();
        beitragDao.setId(beitrag.getId());
        beitragDao.setTitle(beitrag.getTitle());
        beitragDao.setContent(beitrag.getContent());
        beitragDao.setViews(beitragViewService.getViewsForBeitrag(beitrag.getId()));
        beitragDao.setCreationTime(beitrag.getCreationTime().toString());
        if(beitrag.getUsername() != null){
            beitragDao.setAuthor(beitrag.getUsername().getUsername());
        } else{
            beitragDao.setAuthor("Unbekannt");
        }
        return beitragDao;
    }
}
