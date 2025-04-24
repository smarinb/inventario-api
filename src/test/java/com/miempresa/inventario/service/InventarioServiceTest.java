package com.miempresa.inventario.service;

import com.miempresa.inventario.model.Producto;
import com.miempresa.inventario.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventarioServiceTest {

    @Autowired
    private InventarioService servicio;

    @Autowired
    private ProductoRepository repository;

    @Test
    public void guardarProducto_nuevoProducto_loAgregaCorrectamente() {
        Producto pan = new Producto("Pan", 1.0, 10);
        Producto guardado = servicio.guardar(pan);

        assertNotNull(guardado.getId());
        assertEquals("Pan", guardado.getNombre());
    }

    @Test
    public void eliminarProducto_existente_loElimina() {
        Producto pan = new Producto("Pan", 1.0, 10);
        Producto guardado = servicio.guardar(pan);

        boolean resultado = servicio.eliminar(guardado.getId());

        assertTrue(resultado);
        assertFalse(repository.existsById(guardado.getId()));
    }

    @Test
    public void eliminarProducto_noExistente_noLoElimina() {
        boolean resultado = servicio.eliminar(9999L);
        assertFalse(resultado);
    }

    @Test
    public void buscarProducto_existente() {
        Producto pan = new Producto("Pan", 1.0, 10);
        Producto guardado = servicio.guardar(pan);

        Optional<Producto> resultado = servicio.buscar(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Pan", resultado.get().getNombre());
    }

    @Test
    public void buscarProducto_noExistente() {
        Optional<Producto> resultado = servicio.buscar(9999L);
        assertFalse(resultado.isPresent());
    }

    @Test
    public void listarProductos_devuelveLista() {
        servicio.guardar(new Producto("Pan", 1.0, 10));
        servicio.guardar(new Producto("Leche", 1.2, 20));

        assertTrue(servicio.listar().size() >= 2);
    }
}
