
package com.thais.demotcc.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;



/**
 *
 * @author Thais
 */
public class Autenticar {
    public static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}
