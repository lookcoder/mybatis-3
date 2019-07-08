package com.lookcoder.mybatis.basetest;

import com.lookcoder.mybatis.basetest.h2.AuthorDB;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class AuthorTest {

  @Before
  public void initDB() {
    new AuthorDB().init();
  }

  @Test
  public void authorTest() {
    InputStream config = AuthorTest.class.getClassLoader().getResourceAsStream("com/lookcoder/mybatis/basetest/mybatis-config.xml");

    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(config);

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      final AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);

      System.out.println("==============================================");
      final AuthorExample authorExample = new AuthorExample();
      authorExample.createCriteria().andUsernameEqualTo("author1");
      final List<Author> authors = mapper.selectByExample(authorExample);
      authors.forEach(System.out::println);
      System.out.println("==============================================");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
