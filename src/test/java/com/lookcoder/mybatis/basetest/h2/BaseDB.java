package com.lookcoder.mybatis.basetest.h2;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class BaseDB {

  public void init() {
    try {
      DataSource ds = createDS();
      try (Connection connection = ds.getConnection()) {
        initSchema(connection);
        initData(connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected abstract Properties getCrtDSscript() throws IOException;

  protected abstract void initSchema(Connection connection) throws IOException;

  protected abstract void initData(Connection connection) throws IOException;

  protected void run(Connection connection, String script) throws IOException {
    ScriptRunner sr = new ScriptRunner(connection);
    sr.runScript(Resources.getResourceAsReader(script));
  }

  private DataSource createDS() throws IOException {
    final Properties resourceAsProperties = getCrtDSscript();
    UnpooledDataSource ud = new UnpooledDataSource();
    ud.setDriver(resourceAsProperties.getProperty("driver"));
    ud.setUrl(resourceAsProperties.getProperty("url"));
    ud.setUsername(resourceAsProperties.getProperty("username"));
    ud.setPassword(resourceAsProperties.getProperty("password"));
    return ud;
  }
}
