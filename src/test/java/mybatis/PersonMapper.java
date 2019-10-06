package mybatis;



import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/22 10:10
 * @description
 */
public interface PersonMapper {
    void addPersons(@Param("persons") List<Person> persons);
}
