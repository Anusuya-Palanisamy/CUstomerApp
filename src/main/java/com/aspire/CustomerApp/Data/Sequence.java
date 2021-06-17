package com.aspire.CustomerApp.Data;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class Sequence {
	@Autowired public  MongoOperations mongo;

    public  Long getNextCustomerSequence(String seqName)
    {
    	System.out.print("Hi Sequence");
    	CustomerSeq counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            CustomerSeq.class);
        return counter.getSeq();
    }

}
