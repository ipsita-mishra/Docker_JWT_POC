package com.learning.zoro.concepts.Repository;

import com.learning.zoro.concepts.Domain.Document;
import com.learning.zoro.concepts.Domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<Document,Long> {

}


