package tv.codely.shared.infrastructure.spring;

import javax.servlet.Filter;
import java.util.HashMap;
import javax.servlet.http.ServletRequest;
import javax.servlet.http.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;


public final class BasicHttpAuthMiddleware implements Filter {

    private final CommandBus bus;

    public BasicHttpAuthMiddleware(CommandBus bus) {
        this.bus = bus;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest) request).getHeader("authorization");

        if (hasIntroducedCredentials(authorizationHeader)) {
            authenticate(authorizationHeader, chain, request, response);
        } else {
            askForCredentials(response);
        }
    }

    private boolean hasIntroducedCredentials(String authorizationHeader) {
        return authorizationHeader != null;
    }

    private void authenticate(String authorizationHeader, FilterChain chain, ServletRequest request, ServletResponse response) throws IOException, ServletException {
        String[] auth = decodeAuth(authorizationHeader);
        String user = auth[0];
        String pass = auth[1];

        if (isValid(user, pass)) {
            request.setAttribute("authentication_username", user);
            chain.doFilter(request, response);
        }
        else {
            setInvalidCredentials(response);
        }
    }

    private void setInvalidCredentials(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private boolean isValid(String user, String pass) {
        return validUsers.containsKey(user) && validUsers.get(user).equals(pass);
    }

    private String[] decodeAuth(String authString) {
        return new String(Base64.getDecoder().decode(authString.split("\\s+")[1])).split(":");
    }

    private void askForCredentials(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("WWW-Authenticate", "Basic realm=\"Restricted Area ⛔️\"");
    }
}
