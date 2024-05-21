package com.example.be.repos;

import com.example.be.models.Attachmant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachmant, Long> {

    Attachmant findAttachmantById(Long id);
}
