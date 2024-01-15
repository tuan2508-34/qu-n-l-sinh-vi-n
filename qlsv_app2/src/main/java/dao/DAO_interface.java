package dao;

import java.util.List;

public interface DAO_interface<T> {
      public int insert (T t);
      public int update (T t);
      public int delete (T t);
      public List<T> selectAll();
      public T selectById (T t);
      public List<T> selectByCondition(String condition);
}
