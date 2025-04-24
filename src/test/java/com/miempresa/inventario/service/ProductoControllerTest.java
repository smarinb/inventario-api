package com.miempresa.inventario.service;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarProductos_devuelve200() throws Exception {
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarProducto_devuelve201ConJson() throws Exception {
        String productoJson = """
    {
        "nombre": "Pan",
        "precio": 1.99,
        "stock": 5
    }
    """;

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Pan")));
    }

    @Test
    void agregarProductoSinNombre_devuelve400() throws Exception {
        String productoJson = """
    {
        "nombre": "",
        "precio": 1.99,
        "stock": 5
    }
    """;

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarProducto_existente_devuelve200ConContenido() throws Exception {
        String productoJson = """
    {
        "nombre": "Leche",
        "precio": 1.50,
        "stock": 10
    }
    """;

        String response = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Number idNum = JsonPath.read(response, "$.id");
        long id = idNum.longValue();

        mockMvc.perform(get("/productos/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Leche")));
    }


    @Test
    void buscarProducto_noExistente_devuelve404() throws Exception {
        mockMvc.perform(get("/productos/999999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void eliminarProducto_existente_devuelve204() throws Exception {
        String productoJson = """
    {
        "nombre": "Huevos",
        "precio": 2.99,
        "stock": 12
    }
    """;

        String response = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();




        Number idNum = JsonPath.read(response, "$.id");
        long id = idNum.longValue();


        mockMvc.perform(delete("/productos/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarProducto_noExistente_devuelve404() throws Exception {
        mockMvc.perform(delete("/productos/999999"))
                .andExpect(status().isNotFound());
    }









}
