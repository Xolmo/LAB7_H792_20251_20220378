package com.example.lab7_20220378.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProveedoresController {

    @GetMapping(value = "/proveedores", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> obtenerProveedores() {
        
    }
}
