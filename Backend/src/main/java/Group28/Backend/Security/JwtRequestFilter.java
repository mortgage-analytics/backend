package Group28.Backend.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

  private JwtUtil jwtUtil;

  public JwtRequestFilter(JwtUtil jwtUtil)
  {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws ServletException, IOException {

    String jwt = null;
    if(request.getCookies() != null)
    {
      System.out.println("cookies");

      for(Cookie cookie : request.getCookies())
      {
        if(cookie.getName().equals("AuthToken"))
        {
          jwt = cookie.getValue();
        }
      }
    } else
    {
      System.out.println("no cookies");
    }

//    final String authHeader = request.getHeader("Cookie");
//
//    String jwt = null;
//    if (authHeader != null && authHeader.startsWith("AuthToken=")) {
//      jwt = authHeader.substring(10);
//    }

    if (jwt != null && jwtUtil.validateToken(jwt, "username")) {
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken("username", null, null);
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    chain.doFilter(request, response);
  }
}
