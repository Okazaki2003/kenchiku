package com.seproject.buildmanager.repository;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.seproject.buildmanager.entity.MstAuth;

public interface MstAuthRepository extends JpaRepository<MstAuth, Long> {

}
