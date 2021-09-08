package org.example;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.javalin.Javalin;
import java.lang.reflect.Method;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.database.DatabaseModule;
import org.example.common.config.web.WebModule;
import org.example.common.config.web.annotation.DeleteMapping;
import org.example.common.config.web.annotation.GetMapping;
import org.example.common.config.web.annotation.PatchMapping;
import org.example.common.config.web.annotation.PostMapping;
import org.example.common.config.web.annotation.PutMapping;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

@Slf4j
public class Application {

  public static void main(String[] args) {
    Injector injector =
        Guice.createInjector(new WebModule(), new DatabaseModule());
    Javalin app = injector.getInstance(Javalin.class).start(8080);

    registerControllerMapping(injector, app);
  }

  private static void registerControllerMapping(Injector injector, Javalin app) {
    Reflections reflections = new Reflections(Application.class, new MethodAnnotationsScanner());
    Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);
    Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);
    Set<Method> putMethods = reflections.getMethodsAnnotatedWith(PutMapping.class);
    Set<Method> patchMethods = reflections.getMethodsAnnotatedWith(PatchMapping.class);
    Set<Method> deleteMethods = reflections.getMethodsAnnotatedWith(DeleteMapping.class);

    registerGetMapping(injector, app, getMethods);
    registerPostMapping(injector, app, postMethods);
    registerPutMapping(injector, app, putMethods);
    registerPatchMapping(injector, app, patchMethods);
    registerDeleteMapping(injector, app, deleteMethods);
  }

  private static void registerDeleteMapping(Injector injector, Javalin app, Set<Method> deleteMethods) {
    deleteMethods.forEach(
        method -> {
          DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
          app.routes(
              () ->
                  path(
                      deleteMapping.path(),
                      () ->
                          get(
                              ctx ->
                                  method.invoke(
                                      injector.getInstance(method.getDeclaringClass()), ctx))));
        });
  }

  private static void registerPatchMapping(Injector injector, Javalin app, Set<Method> patchMethods) {
    patchMethods.forEach(
        method -> {
          PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
          app.routes(
              () ->
                  path(
                      patchMapping.path(),
                      () ->
                          get(
                              ctx ->
                                  method.invoke(
                                      injector.getInstance(method.getDeclaringClass()), ctx))));
        });
  }

  private static void registerPutMapping(Injector injector, Javalin app, Set<Method> putMethods) {
    putMethods.forEach(
        method -> {
          PutMapping putMapping = method.getAnnotation(PutMapping.class);
          app.routes(
              () ->
                  path(
                      putMapping.path(),
                      () ->
                          get(
                              ctx ->
                                  method.invoke(
                                      injector.getInstance(method.getDeclaringClass()), ctx))));
        });
  }

  private static void registerGetMapping(Injector injector, Javalin app, Set<Method> getMethods) {
    getMethods.forEach(
        method -> {
          GetMapping getMapping = method.getAnnotation(GetMapping.class);
          app.routes(
              () ->
                  path(
                      getMapping.path(),
                      () ->
                          get(
                              ctx ->
                                  method.invoke(
                                      injector.getInstance(method.getDeclaringClass()), ctx))));
        });
  }

  private static void registerPostMapping(
      Injector injector, Javalin app, Set<Method> postMethods) {
    postMethods.forEach(
        method -> {
          PostMapping postMapping = method.getAnnotation(PostMapping.class);
          app.routes(
              () ->
                  path(
                      postMapping.path(),
                      () ->
                          post(
                              ctx ->
                                  method.invoke(
                                      injector.getInstance(method.getDeclaringClass()), ctx))));
        });
  }
}
