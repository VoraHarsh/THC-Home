package com.egen.thchome.repository;

import com.egen.thchome.entity.Interceptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterceptorRepository extends JpaRepository<Interceptor, String> {

    @Query("SELECT interceptor FROM Interceptor interceptor WHERE interceptor.methodName LIKE %:methodName")
    List<Interceptor> findByMethodNameEndsWith(String methodName);
}
