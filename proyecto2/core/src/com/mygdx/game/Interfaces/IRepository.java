package com.mygdx.game.Interfaces;

import java.util.List;
/**
 * @author Blas Machado
 * @version 1.0
 */
public interface IRepository<T> {
    void cargar();
    void guardar();
    void agregar(T obj);
    List<T> listar();
}
