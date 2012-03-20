package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamScale;
import org.fam.ejb.session.FamScaleFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famScaleController")
@ViewScoped
public class FamScaleController extends AbstractController<FamScale> implements Serializable {

    @EJB
    private FamScaleFacade ejbFacade;

    public FamScaleController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamScale getSelected() {
        if (current == null) {
            current = new FamScale();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamScaleFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdScale();
        return "pretty:editScale";
    }

    @Override
    public String prepareView() {
        id = current.getIdScale();
        return "pretty:viewScale";
    }

    @Override
    public String prepareList() {
        return "pretty:listScale";
    }

    @Override
    public String prepareCreate() {
        current = new FamScale();
        selectedItemIndex = -1;
        return "pretty:createScale";
    }
}