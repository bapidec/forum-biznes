package com.example.forumbiznes;

import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.util.HashMap;
import java.util.Map;

@DataSourceDefinition(
    name = "java:global/JsfCrudDemoDataSource",
    className = "org.h2.jdbcx.JdbcDataSource",
        url="jdbc:h2:C:\\Users\\BURAK\\Documents\\payara6\\glassfish\\domains\\domain1/lib/databases/embedded_default",
        user="",
        password=""
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:global/JsfCrudDemoDataSource",
        callerQuery = "SELECT password FROM \"USERS\" WHERE login = ?",
        groupsQuery = "SELECT u.accessLevel FROM \"USERS\" u WHERE u.login = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
                "Pbkdf2PasswordHash.Iterations=3072",
                "Pbkdf2PasswordHash.SaltSizeBytes=64",
        }

)
@FacesConfig // konfiguruje JSF w wersji 2.3, wymagane żeby działało wstrzykiwanie ExternalContext w LoginController
@Singleton
@Startup
public class Configuration {

        @Inject
        private Pbkdf2PasswordHash pbkdf;

        @PostConstruct
        private void init() {
                Map<String,String> map = new HashMap<>();
                map.put("Pbkdf2PasswordHash.Algorithm","PBKDF2WithHmacSHA512");
                map.put("Pbkdf2PasswordHash.Iterations","3072");
                map.put("Pbkdf2PasswordHash.SaltSizeBytes","64");
                pbkdf.initialize(map);
       }
}

