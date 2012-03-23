/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.cache;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.*;
import org.fam.ejb.session.FamPlayerFacade;
import org.fam.ejb.session.FamTeamFacade;
import org.fam.ejb.session.FamUserFacade;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mask_hot
 */
@SessionScoped
@Named
@Loggable
@Getter
@Setter
public class CachePlayer implements Serializable {


    private static final long serialVersionUID = -5455507836304240866L;
    @Inject
    private Logger LOGGER;

    @Inject
    private CacheBean cacheBean;

    private List<FamState> lstState = new ArrayList<FamState>();
    private List<FamProvince> lstProvince = new ArrayList<FamProvince>();
    private List<FamCity> lstCity = new ArrayList<FamCity>();

    @Inject
    @LoggedIn
    private FamUser currentUser;

    private FamPlayer currentPlayer;

    // TEAMS
    @Inject
    private FamTeamFacade ejbTeam;
    private List<FamTeam> listTeam = new ArrayList<FamTeam>();
    // PLAYER
    @Inject
    private FamPlayerFacade ejbPlayer;
    private List<FamPlayer> listPlayer = new ArrayList<FamPlayer>();
    // USER
    @Inject
    private FamUserFacade ejbUser;
    private List<FamUser> listUser = new ArrayList<FamUser>();

    public CachePlayer() {
    }

    @PostConstruct
    void postConstruct() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CurrentUser " + currentUser);
        }
        currentPlayer = ejbPlayer.findByFamUser(currentUser).get(0);
        // TEAMS
        loadAllTeams();
        //PLAYER
        loadAllPlayers();
        //USER
        loadAllUsers();
    }

    private void loadAllTeams() {
        listTeam.clear();

        for (FamTeam o : ejbTeam.findAll()) {
            listTeam.add(o);
        }
    }

    private void loadAllPlayers() {
        listPlayer.clear();

        for (FamPlayer o : ejbPlayer.findAll()) {
            listPlayer.add(o);
        }
    }

    private void loadAllUsers() {
        listUser.clear();

        for (FamUser o : ejbUser.findAll()) {
            listUser.add(o);
        }
    }

    public void filterState(FamCountry country) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterState");
        }
        lstState.clear();
        lstProvince.clear();
        lstCity.clear();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterState search " + country.getLibCountry());
        }
        for (FamState item : cacheBean.getListState()) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("item " + item.getFamCountry().getLibCountry());
            }
            if (item.getFamCountry().equals(country)) {
                lstState.add(item);
//                LOGGER.info("added");
            }
        }
    }

    public void filterProvince(FamState state) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterProvince");
        }
        lstProvince.clear();
        lstCity.clear();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterProvince search " + state.getLibState());
        }
        for (FamProvince item : cacheBean.getListProvince()) {
//            LOGGER.info("item " + item.getFamState().getLibState());
            if (item.getFamState().equals(state)) {
                lstProvince.add(item);
//                LOGGER.info("added");
            }
        }
    }

    public void filterCity(FamProvince province) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterCity");
        }
        lstCity.clear();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:filterCity search " + province.getLibProvince());
        }

        for (FamCity item : cacheBean.getListCity()) {
            if (item.getFamProvince().equals(province)) {
                lstCity.add(item);
//                LOGGER.info("added");
            }
        }
    }

    public List<FamCountry> completeCountry(String query) {
        List<FamCountry> suggestions = new ArrayList<FamCountry>();

        for (FamCountry p : cacheBean.getListCountry()) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamState> completeState(String query) {
        List<FamState> suggestions = new ArrayList<FamState>();

        for (FamState p : lstState) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamProvince> completeProvince(String query) {
        List<FamProvince> suggestions = new ArrayList<FamProvince>();

        for (FamProvince p : lstProvince) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public List<FamCity> completeCity(String query) {
        List<FamCity> suggestions = new ArrayList<FamCity>();

        for (FamCity p : lstCity) {
            if (p.getLibUpper().startsWith(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    public void onChangeValue(SelectEvent event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("CachePlayer:onChangeValue");
        }

        if (event.getObject().getClass().equals(FamCountry.class)) {
            filterState((FamCountry) event.getObject());
        } else {
            if (event.getObject().getClass().equals(FamState.class)) {
                filterProvince((FamState) event.getObject());
            } else {
                if (event.getObject().getClass().equals(FamProvince.class)) {
                    filterCity((FamProvince) event.getObject());
                }
            }
        }

    }

    @Produces
    @Player
    @Named(value = "currentPlayer")
    public FamPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
