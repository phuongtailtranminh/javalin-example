package org.example.common.config.web.exception;

import io.javalin.Javalin;
import java.lang.reflect.InvocationTargetException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExceptionMapping {

  private final Javalin app;

  @Inject
  public ExceptionMapping(Javalin app) {
    this.app = app;
    configure();
  }

  private void configure() {
    app.exception(
        Exception.class,
        (e, ctx) -> {
          // Because we use reflection to invoke the method, so the real exception is wrapped in
          // InvocationTargetException
          Throwable realException = ((InvocationTargetException) e).getTargetException();
          if (realException instanceof AppException) {
            ctx.json(realException);
          } else {
            ctx.status(500);
          }
        });
  }
}
