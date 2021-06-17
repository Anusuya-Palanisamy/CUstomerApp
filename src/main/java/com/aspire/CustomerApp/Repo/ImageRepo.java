package com.aspire.CustomerApp.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspire.CustomerApp.Data.Image;

public interface ImageRepo extends MongoRepository<Image, String> { }


