package org.example.common.config.database;

import com.google.inject.AbstractModule;

public class DatabaseModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DataSource.class).toInstance(new MySqlDataSource());
  }

}
