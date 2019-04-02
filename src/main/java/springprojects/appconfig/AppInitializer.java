package springprojects.appconfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
public class AppInitializer implements WebApplicationInitializer {

        public void onStartup(ServletContext servletContext)  {
            AnnotationConfigWebApplicationContext context
                    = new AnnotationConfigWebApplicationContext();
            context.register(AppConfig.class);

            servletContext.addListener(new ContextLoaderListener(context));

            ServletRegistration.Dynamic dispatcher = servletContext
                    .addServlet("dispatcher", new DispatcherServlet(context));

            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
        }
}
