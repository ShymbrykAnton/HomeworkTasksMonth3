package servlet;

import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Container {
    public static ServletHandler getServletHandler(){
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MainServlet.class,"/Login");
        servletHandler.isInitialized();
//        servletHandler.addFilterWithMapping(FilterServlet.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        return servletHandler;
    }
}
