package org.example.common.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySqlDataSource implements DataSource {

  private final HikariDataSource ds;

  public MySqlDataSource() {
    HikariConfig config = new HikariConfig("/hikari.properties");
    this.ds = new HikariDataSource(config);
  }

  @Override
  public Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
