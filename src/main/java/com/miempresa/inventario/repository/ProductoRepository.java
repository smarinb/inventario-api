package com.miempresa.inventario.repository;

import com.miempresa.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto,Long> {

}
