package com.blog.api.api.controller;

import com.blog.api.api.model.Beitrag;
import com.blog.api.api.model.BeitragView;
import com.blog.api.api.model.User;
import com.blog.api.api.model.req.BeitragAddRequest;
import com.blog.api.api.model.dao.BeitragDAO;
import com.blog.api.api.model.req.BeitragViewIncreaseRequest;
import com.blog.api.api.service.BeitragService;
import com.blog.api.api.service.BeitragViewService;
import com.blog.api.api.service.UserService;
import com.google.gson.GsonBuilder;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@RestController
public class BeitragController {

    @Autowired
    private BeitragService beitragService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeitragViewService beitragViewService;

    @Autowired
    private DataSource ds;

    @RequestMapping(value = "/beitraege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getBeitraege() {
        List<Beitrag> allBeitraege = beitragService.getAllBeitraege();
        List<BeitragDAO> beitragDaoList = BeitragDAO.convertBeitragList(allBeitraege, beitragViewService);
        String body = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(beitragDaoList);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/addBeitrag", method = RequestMethod.POST)
    public ResponseEntity addBeitrag(@Validated @RequestBody BeitragAddRequest request) {
        try {
            request.setUserService(userService);
            Beitrag beitrag = request.convertToBeitrag();
            beitragService.addBeitrag(beitrag);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/beitraege/{beitragId}/addView", method = RequestMethod.POST)
    public ResponseEntity addView(@PathVariable int beitragId, @RequestBody BeitragViewIncreaseRequest request) throws Exception {
        BeitragView beitragView = new BeitragView();

        String reqUsername = request.getUser();
        User user = null;
        if(reqUsername == null){
            user = userService.getZeroUser();
        } else {
            Optional<User> oUser = userService.getUser(reqUsername);
            user = oUser.isPresent() ? oUser.get() : userService.getZeroUser();
        }

        Beitrag beitrag = beitragService.getAllBeitraege().stream()
                .filter(b -> b.getId() == beitragId)
                .findFirst().get();

        beitragView.setDate(request.getDate());
        beitragView.setUser(user);
        beitragView.setBeitrag(beitrag);

        beitragViewService.addView(beitragView);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/beitraege/{beitragId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeitragById(@PathVariable Long beitragId){
        Optional<Beitrag> beitrag = this.beitragService.getBeitragById(beitragId);

        if(!beitrag.isPresent()){
            return ResponseEntity.noContent().build();
        }

        BeitragDAO beitragDAO = BeitragDAO.convertBeitrag(beitrag.get(), beitragViewService);

        String body = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(beitragDAO);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
