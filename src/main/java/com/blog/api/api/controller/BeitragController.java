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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BeitragController {

    Logger logger = LoggerFactory.getLogger(BeitragController.class);

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
        logger.info("GET - /beitraege");

        List<Beitrag> allBeitraege = beitragService.getAllBeitraege();
        List<BeitragDAO> beitragDaoList = BeitragDAO.convertBeitragList(allBeitraege, beitragViewService);
        String body = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(beitragDaoList);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/beitraege", method = RequestMethod.POST)
    public ResponseEntity addBeitrag(@Validated @RequestBody BeitragAddRequest request) {
        logger.info(String.format("POST - /beitraege, \n\tauthor: %s,\n\ttitle: %s,\n\tcontent: %s,\n\tviews: %d", request.getAuthor(), request.getTitle(), request.getContent(), request.getViews()));

        request.setUserService(userService);
        Beitrag beitrag = request.convertToBeitrag();
        beitragService.addBeitrag(beitrag);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/beitraege/{beitragId}/addView", method = RequestMethod.POST)
    public ResponseEntity addView(@PathVariable int beitragId, @RequestBody BeitragViewIncreaseRequest request) throws Exception {
        logger.info(String.format("POST - /beitraege/%d/addView \n\tuser: %s,\n\tdate: %s", beitragId, request.getUser(), request.getDate().toString()));
        BeitragView beitragView = new BeitragView();

        String reqUsername = request.getUser();
        User user = null;
        if(reqUsername == null){
            user = userService.getZeroUser();
        } else {
            Optional<User> oUser = userService.getUser(reqUsername);
            user = oUser.orElseGet(() -> userService.getZeroUser());
        }

        Beitrag beitrag = beitragService.getAllBeitraege().stream()
                .filter(b -> b.getId() == beitragId)
                .findFirst().get();

        beitragView.setDate(Date.from(request.getDate()));
        beitragView.setUser(user);
        beitragView.setBeitrag(beitrag);

        beitragViewService.addView(beitragView);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/beitraege/{beitragId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeitragById(@PathVariable Long beitragId){
        logger.info(String.format("GET - /beitrag/%d", beitragId));

        Optional<Beitrag> beitrag = this.beitragService.getBeitragById(beitragId);

        if(beitrag.isEmpty()){
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
