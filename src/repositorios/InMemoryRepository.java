package repositorios;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T> {
    protected Map<Long, T> data = new HashMap<>();
    protected AtomicLong idGenerator = new AtomicLong();

    //Este metodo guarda una entidad nueva.
    public T save(T entity) {
        long id = idGenerator.incrementAndGet();
        // Suponiendo que las entidades tienen un metodo setId
        try {
            String clase;
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
            clase = entity.getClass().getName();
            System.out.println(clase + "   id :" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        data.put(id, entity);
        return entity;
    }

    //Este metodo busca una entidad por su ID.
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }


    //Recupera todas las entidades guardadas.
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    //Actualiza una entidad existente.
    public Optional<T> genericUpdate(Long id, T updatedEntity) {
        if (!data.containsKey(id)) {
            return Optional.empty();
        }

        try {
            // Establecer el mismo ID en la entidad actualizada para mantener la coherencia
            Method setIdMethod = updatedEntity.getClass().getMethod("setId", Long.class);
            setIdMethod.invoke(updatedEntity, id);

            data.put(id, updatedEntity);
            return Optional.of(updatedEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //Elimina una entidad por su ID.
    public Optional<T> genericDelete(Long id) {
        if (!data.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.ofNullable(data.remove(id));
    }

    //Busca entidades basándose en el valor de un campo específico.
    public List<T> genericFindByField(String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        try {
            for (T entity : data.values()) {
                Method getFieldMethod = entity.getClass().getMethod("get" + capitalize(fieldName));
                Object fieldValue = getFieldMethod.invoke(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    results.add(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    //Metodo auxiliar que simplemente convierte la primera letra de una cadena en mayúscula
    //para construir los nombres de los métodos getter correctamente.
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
