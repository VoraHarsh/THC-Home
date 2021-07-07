package com.egen.thchome.repository;

import com.egen.thchome.entity.Interceptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface InterceptorRepository extends JpaRepository<Interceptor, String> {

    List<Interceptor> findByMethodName(String methodName);

    @Query("SELECT interceptor FROM Interceptor interceptor WHERE interceptor.startTime BETWEEN :startTime AND :endTime")
    List<Interceptor> findAllInterceptorsWithInInterval(Timestamp startTime, Timestamp endTime);

}
