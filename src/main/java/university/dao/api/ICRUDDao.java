package university.dao.api;

import java.util.List;

public interface ICRUDDao<T, P, ID> {
    P create(P item);

    List<T> read();

    T readOne(ID id);

    P update(ID id, P item);

    void delete(ID id);
}
