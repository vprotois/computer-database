package app;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AnnotationsBasedApplicationInitializer 
extends AbstractContextLoaderInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
      AnnotationConfigWebApplicationContext rootContext
        = new AnnotationConfigWebApplicationContext();
      rootContext.register(AppConfig.class);
      return rootContext;
  }
}
