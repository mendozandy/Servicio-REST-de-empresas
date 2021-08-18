package Genericos;

import java.util.List;

public interface CRUD <T> {
    boolean insertar(T t);
    boolean modificar(T t);
    boolean eliminar(T t);
    List<T> consultarTodos();
    List<T> consultarSegunId(T t);
    String getMensaje();
           
}
