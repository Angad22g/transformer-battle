package com.aequilibrium.transformers.repository;

import com.aequilibrium.transformers.model.domain.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TransformersRepository extends CrudRepository<Transformers, Long> {

}
