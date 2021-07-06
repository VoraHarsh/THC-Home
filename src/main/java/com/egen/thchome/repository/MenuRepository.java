package com.egen.thchome.repository;

import com.egen.thchome.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;




public interface MenuRepository extends JpaRepository<Menu, String> {
    Menu findByMenuId(String id);
}
