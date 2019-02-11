
package com.thais.demotcc;

import com.thais.demotcc.config.Autenticar;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author Thais
 */
public class FiltroFuncionario extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest sr1, ServletResponse sr2, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest httpServRequest = (HttpServletRequest) sr1;
        String header = httpServRequest.getHeader("Autorization");
        
        if (header == null || !header.startsWith("Bearer ")){
            throw new ServletException("Não foi possível validar o token.");
        }
        
        String token = header.substring(7);
        
        try {
            Jwts.parser()
                    .setSigningKey(Autenticar.KEY)
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new ServletException(e);
        }

        fc.doFilter(sr1, sr2);
    }
    
}
