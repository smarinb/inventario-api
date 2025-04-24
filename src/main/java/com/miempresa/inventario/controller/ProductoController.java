package com.miempresa.inventario.controller;


import com.miempresa.inventario.model.Producto;
import com.miempresa.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private InventarioService servicio;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Producto> productos = servicio.listar();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody Producto p) {
        Producto creado = servicio.guardar(p);
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        var producto = servicio.buscar(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (servicio.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


