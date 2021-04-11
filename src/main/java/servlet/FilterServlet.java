//package servlet;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class FilterServlet implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain)
//            throws IOException, ServletException {
//        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
//        HttpServletResponse resp = (HttpServletResponse) response;
//
//        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
