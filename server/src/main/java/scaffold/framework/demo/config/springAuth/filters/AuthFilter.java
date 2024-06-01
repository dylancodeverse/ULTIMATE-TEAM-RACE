package scaffold.framework.demo.config.springAuth.filters;

import java.io.IOException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scaffold.framework.demo.config.springAuth.annotations.Auth;

@Component
public class AuthFilter implements Filter {

    @Autowired
    private ApplicationContext context;

    private RequestMappingHandlerMapping handlerMapping;

    @PostConstruct
    public void init() {
        // Spécifiez le nom du bean que vous souhaitez utiliser
        this.handlerMapping = (RequestMappingHandlerMapping) context.getBean("requestMappingHandlerMapping");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest theRequest = (HttpServletRequest) request;
        HttpServletResponse response2 = (HttpServletResponse) response;

        // Obtenir le HandlerMethod qui correspond à l'URI
        HandlerMethod handlerMethod = null;
        try {
            HandlerExecutionChain varr = handlerMapping.getHandler(theRequest);
            if (varr != null) {
                System.out.println("-------filtre commence-------");

                handlerMethod = (HandlerMethod) varr.getHandler();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si on a trouvé un HandlerMethod, obtenir les détails de la classe et de la
        // méthode
        if (handlerMethod != null) {
            Method controllerMethod = handlerMethod.getMethod();
            // verifier si l'annotation @Auth est presente sur la methode
            if (controllerMethod.isAnnotationPresent(Auth.class)) {
                Auth authAnnotation = controllerMethod.getAnnotation(Auth.class);
                String rule = authAnnotation.rule();
                Class<?> classSource = authAnnotation.classSource();
                try {
                    Object obj = classSource.getConstructor().newInstance();
                    classSource.getMethod(rule, HttpServletRequest.class, HttpServletResponse.class).invoke(obj,
                            theRequest, response2);
                    // si tsisy olana sinon tsy ato mi regle azy fa a partir anle rule
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            // Continuer avec le reste de la chaîne de filtres
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre
    }

    @Override
    public void destroy() {
        // Nettoyage du filtre
    }

}
