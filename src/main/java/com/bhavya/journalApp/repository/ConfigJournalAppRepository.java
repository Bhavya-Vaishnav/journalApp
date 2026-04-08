package com.bhavya.journalApp.repository;

import com.bhavya.journalApp.entity.ConfigJournalAppEntity;
import com.bhavya.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, String> {
}
