package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(
            ProductoRepository productoRepository) {

        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {

        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {

        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {

        return productoRepository.save(producto);
    }

    public Producto actualizar(
            Long id,
            Producto productoActualizado) {

        return productoRepository
                .findById(id)
                .map(producto -> {

                    producto.setNombre(
                            productoActualizado.getNombre());

                    producto.setCategoria(
                            productoActualizado.getCategoria());

                    producto.setPrecio(
                            productoActualizado.getPrecio());

                    producto.setStock(
                            productoActualizado.getStock());

                    return productoRepository.save(producto);

                }).orElse(null);
    }

    public boolean eliminar(Long id) {

        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);

        return true;
    }
}
