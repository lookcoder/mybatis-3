package com.lookcoder.mybatis.basetest.h2;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class AuthorDB extends BaseDB {

  private static final String AUTHOR_H2 = "com/lookcoder/mybatis/basetest/h2/author-h2.properties";
  private static final String AUTHOR_H2_SCHEMA = "com/lookcoder/mybatis/basetest/h2/author-h2-schema.sql";
  private static final String AUTHOR_H2_DATALOAD = "com/lookcoder/mybatis/basetest/h2/author-h2-dataload.sql";

  @Override
  protected Properties getCrtDSscript() throws IOException {
    return Resources.getResourceAsProperties(AUTHOR_H2);
  }

  @Override
  protected void initSchema(Connection connection) throws IOException {
    run(connection, AUTHOR_H2_SCHEMA);
  }

  @Override
  protected void initData(Connection connection) throws IOException {
    run(connection, AUTHOR_H2_DATALOAD);
  }
}
