package com.example.forumbiznes.Model;

import com.example.forumbiznes.Dao.TopicDao;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@ManagedBean
@ViewScoped
public class TopicManagedBean extends AbstractModel implements Serializable {
    private Optional<Topic> topicBean;

    @PostConstruct
    public void init() {
        // Pobierz wartość parametru ID z adresu URL
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idParam = params.get("id");
        if (idParam != null) {
            setId(Long.valueOf(idParam));
            // Pobierz dane z bazy danych na podstawie ID
            TopicDao dao = new TopicDao();
            topicBean = dao.findById(this.getId());
        }
    }
}
