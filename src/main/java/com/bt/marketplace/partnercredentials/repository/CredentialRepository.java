package com.bt.marketplace.partnercredentials.repository;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredentialRepository extends MongoRepository<PartnerCredentialDocument, String> {
}
