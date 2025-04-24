package com.miempresa.inventario.service;

import com.miempresa.inventario.model.Producto;
import com.miempresa.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository repository;


    //métodos


    public Optional<Producto> buscar(Long id) {

        return repository.findById(id);

    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> listar() {
        return repository.findAll();
    }


}
