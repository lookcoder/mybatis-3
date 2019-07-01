package com.lookcoder.mybatis.blogtest;


import org.apache.ibatis.domain.blog.Blog;

import java.util.List;

public interface BolgMapper {
  List<Blog> selectBlogDetails(Integer id);
}
