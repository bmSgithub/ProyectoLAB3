package com.mygdx.game.Interfaces;

import java.util.List;

public interface IRepository<T> {
    void cargar();
    void guardar();
    void agregar(T obj);
    List<T> listar();
}
