package servlet;

import org.eclipse.jetty.servlet.ServletHandler;

public class Container {
    public static ServletHandler getServletHandler(){
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MainServlet.class,"/main");
//       servletHandler.addFilterWithMapping(FilterServlet.class,"/*",EnumSet.of(DispatcherType.REQUEST));
       return servletHandler;
    }
}
