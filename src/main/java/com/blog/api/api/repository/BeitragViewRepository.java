package com.blog.api.api.repository;

import com.blog.api.api.model.BeitragView;
import com.blog.api.api.model.BeitragViewId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeitragViewRepository extends CrudRepository<BeitragView, BeitragViewId> {
}
