package org.example.common.config.database;

import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

@Singleton
public class DslContext {

  private final DSLContext instance;

  @Inject
  public DslContext(DataSource dataSource) throws SQLException {
    this.instance = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
  }

  public DSLContext getInstance() {
    return instance;
  }

}
