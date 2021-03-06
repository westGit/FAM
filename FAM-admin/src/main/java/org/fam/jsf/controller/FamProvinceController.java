package org.fam.jsf.controller;

import org.fam.ejb.model.FamProvince;
import org.fam.ejb.session.FamProvinceFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famProvinceController")
@ViewScoped
public class FamProvinceController extends AbstractController<FamProvince>
        implements Serializable {

    @EJB
    private FamProvinceFacade ejbFacade;

    public FamProvinceController() {
    }

    @PostConstruct
    private void postConstruct() {

//        findAll();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamProvince getSelected() {
        if (current == null) {
            current = new FamProvince();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamProvinceFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdProvince();
        return "pretty:editProvince";
    }

    @Override
    public String prepareView() {
        id = current.getIdProvince();
        return "pretty:viewProvince";
    }

    @Override
    public String prepareList() {
        return "pretty:listProvince";
    }

    @Override
    public String prepareCreate() {
        current = new FamProvince();
        selectedItemIndex = -1;
        return "pretty:createProvince";
    }
}
