package com.ve.ve.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ve.ve.Model.MiserEnchere;

@Repository
public interface MiserEnchereRepository extends MongoRepository<MiserEnchere, String> {
}

