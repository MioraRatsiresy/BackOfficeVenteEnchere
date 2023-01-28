package com.ve.ve.Repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ve.ve.Model.PhotoEnchere;

@Repository
public interface PhotoEnchereRepository extends MongoRepository<PhotoEnchere, String> {
    List<PhotoEnchere> findByIdEnchere(int idEnchere);
}
