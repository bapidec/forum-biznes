package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.bean.UserBean;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
@ViewScoped
@Named
public class LoginController implements Serializable {

    @Inject
    private UserBean userBean;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private FacesContext facesContext;
    @Inject @ManagedProperty("#{param.new}")
    private boolean isNew;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void onLogin() throws Exception {
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Niepoprawne dane", "Niepoprawne dane"));
                break;
            case SUCCESS:
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                String accessDir;
                switch(userBean.getUser().getAccessLevel()){
                    case 2:
                        accessDir="admin/";
                        break;
                    case 1:
                        accessDir="mod/";
                        break;
                    case 0:
                        accessDir="user/";
                        break;
                    default:
                        throw new Exception("Nie ma takiego numeru");
                }
                externalContext.redirect(externalContext.getRequestContextPath()+accessDir+"index.xhtml");
                break;
            case NOT_DONE:
        }
    }

    public void onLogout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath()+"login.xhtml");
    }

    private AuthenticationStatus continueAuthentication() {
        if(login!=null && password!=null){
            if(login.length()>4 && password.length()>4){
                return securityContext.authenticate(
                        (HttpServletRequest) externalContext.getRequest(),
                        (HttpServletResponse) externalContext.getResponse(),
                        AuthenticationParameters.withParams()
                                .newAuthentication(isNew).credential(
                                        new UsernamePasswordCredential(login, password))
                );
            }
        }
        return AuthenticationStatus.SEND_FAILURE;
    }
}