package org.example.common.config.web;

import com.google.inject.AbstractModule;
import io.javalin.Javalin;

public class WebModule extends AbstractModule {

  private Javalin app;

  public WebModule() {
    this.app = Javalin.create();
  }

  @Override
  protected void configure() {
    bind(Javalin.class).toInstance(app);
  }
}
