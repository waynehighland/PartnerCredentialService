package com.bt.marketplace.partnercredentials.repository;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredentialRepository extends MongoRepository<PartnerCredentialDocument, String> {
}
