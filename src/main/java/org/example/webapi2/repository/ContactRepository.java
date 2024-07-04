package org.example.webapi2.repository;

import org.example.webapi2.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Integer> {
}
