package com.lookcoder.mybatis.blogtest;

import com.lookcoder.mybatis.basetest.AuthorTest;
import org.apache.ibatis.domain.blog.Blog;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class BlogTest {

  @Test
  public void t() {
    InputStream config = AuthorTest.class.getClassLoader().getResourceAsStream("com/lookcoder/mybatis/blogtest/mybatis-config.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(config);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      final BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
      final List<Blog> blogs = mapper.selectBlogDetails(1);
      blogs.forEach(System.out::println);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
