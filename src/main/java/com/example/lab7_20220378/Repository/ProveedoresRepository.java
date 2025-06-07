package com.example.lab7_20220378.Repository;

import com.example.lab7_20220378.Entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores,Integer> {
}
