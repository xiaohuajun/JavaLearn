import mybatis.Person;
import mybatis.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/14 12:11
 * @description
 */
public class LogTest {


    public static SqlSessionFactory sqlSessionFactory = null;

    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.trace("===trace===");
        logger.debug("====debug==");
        logger.info("=====info=====");
        logger.warn("=====warn=====");
        logger.error("=====error=====");

        processMybatisBatch();
    }


    public SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            try {
                Reader reader = Resources.getResourceAsReader(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    public void processMybatisBatch() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Person person = new Person("jerry" + i, "email@" + i, "f");
            persons.add(person);
        }
        personMapper.addPersons(persons);
        sqlSession.commit();

    }
}
