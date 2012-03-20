/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.jsf.cache;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import lombok.Getter;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.*;
import org.fam.ejb.session.*;
import org.fam.jsf.bootstrap.Bootstrap;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author mask_hot
 */
// Does not work
@Startup
@DependsOn("Bootstrap")
@Named
@Singleton
@Getter
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class CacheBean {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(CacheBean.class);
    @Inject
    private Logger LOGGER;

    private static final long serialVersionUID = 3127080842613764314L;

    private FamSeason currentSeason;
    private FamTypEvent typEventMatch;
    //
    private Locale locale = Locale.FRANCE;
    private TimeZone timeZone = TimeZone.getDefault();
    //
    private Boolean debug = Boolean.TRUE;
    // CLUBS
    @Inject
    private FamClubFacade ejbClub;
    private final List<FamClub> listClub = new ArrayList<FamClub>();

    // ORGANIZATIONS
    @Inject
    private FamOrganizationFacade ejbOrganization;
    private final List<FamOrganization> listOrganization = new ArrayList<FamOrganization>();
    // SEASONS
    @Inject
    private FamSeasonFacade ejbSeason;
    private final List<FamSeason> listSeason = new ArrayList<FamSeason>();
    // PLACES
    @Inject
    private FamPlaceFacade ejbPlace;
    private final List<FamPlace> listPlace = new ArrayList<FamPlace>();
    // TYPE PLACES
    @Inject
    private FamTypPlaceFacade ejbTypPlace;
    private final List<FamTypPlace> listTypPlace = new ArrayList<FamTypPlace>();
    // TYPE EVENT
    @Inject
    private FamTypEventFacade ejbTypEvent;
    private final List<FamTypEvent> listTypEvent = new ArrayList<FamTypEvent>();
    // EVENT STATUS
    @Inject
    private FamEventStatusFacade ejbEventStatus;
    private final List<FamEventStatus> listEventStatus = new ArrayList<FamEventStatus>();
    private FamEventStatus eventStatusScheduled;
    // TYP COMPETITION
    @Inject
    private FamTypCompetitionFacade ejbTypCompetition;
    private final List<FamTypCompetition> listTypCompetition = new ArrayList<FamTypCompetition>();
    // SCALE
    @Inject
    private FamScaleFacade ejbScale;
    private final List<FamScale> listScale = new ArrayList<FamScale>();
    // COMPETITION
    @Inject
    private FamSeasonCompetitionFacade ejbSeasonCompetition;
    private final List<FamSeasonCompetition> listSeasonCompetition = new ArrayList<FamSeasonCompetition>();


    // POSITION
    @Inject
    private FamPositionFacade ejbPosition;
    private final List<FamPosition> listPosition = new ArrayList<FamPosition>();
    // TYP MATCH
    @Inject
    private FamTypMatchFacade ejbTypMatch;
    private final List<FamTypMatch> listTypMatch = new ArrayList<FamTypMatch>();
    // TYP ANSWER
    @Inject
    private FamTypAnswerFacade ejbTypAnswer;
    private final List<FamTypAnswer> listTypAnswer = new ArrayList<FamTypAnswer>();
    // COUNTRY
    @Inject
    private FamCountryFacade ejbCountry;
    private final List<FamCountry> listCountry = new ArrayList<FamCountry>();
    // COUNTRY
    @Inject
    private FamStateFacade ejbState;
    private final List<FamState> listState = new ArrayList<FamState>();
    // COUNTRY
    @Inject
    private FamProvinceFacade ejbProvince;
    private final List<FamProvince> listProvince = new ArrayList<FamProvince>();
    // COUNTRY
    @Inject
    private FamCityFacade ejbCity;
    private final List<FamCity> listCity = new ArrayList<FamCity>();
    // TYPE EVENT
    @Inject
    private FamTypCardFacade ejbTypCard;
    private final List<FamTypCard> listTypCard = new ArrayList<FamTypCard>();
    // TYPE EVENT
    @Inject
    private FamFormationFacade ejbFormation;
    private final List<FamFormation> listFormation = new ArrayList<FamFormation>();
    //
    @Inject
    private Bootstrap bootstrap;

    private final Counter count = Metrics.newCounter(CacheBean.class, "count");


    public CacheBean() {
    }

    @PostConstruct
    void postConstruct() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("IN: CacheBean:PostConstruct");
        }
        count.inc();

//        if (!bootstrap.getInitDone()) {
//            Future<Integer> res = bootstrap.asyncCreateData();
//
//            if (res.isDone()) {
//                //Initialisation du cache
//                initCache();
//            }
//        } else {
//            //Initialisation du cache
//            initCache();
//        }
//        if (bootstrap.getInitDone()) {
//            //Initialisation du cache
//            initCache();
//        }

        try {
//            int i = ejbPosition.findAll().size();
//            LOGGER.debug(i + " positions");
            if (bootstrap.getInitDone()) {
//            if (!ejbPosition.findAll().isEmpty()) {
                initCache();
            } else {
                LOGGER.warn("Bootstrap NOT DONE!!");
            }
        } catch (Exception e) {
            String msg = "Cannot check InitDone via Table Position";
            LOGGER.error(msg, e);
            initCache();
        }
    }

//    // CDI event to update the cache
//    public void onFamPlaceUpdated(@Observes FamPlaceUpdated updated) {
//        // TODO: Get the state from "updated" to update the cache
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onFamPlaceUpdated ");
//        }
//        loadAllPlaces();
//    }
//
//    // CDI event to update the cache
//    public void onDBUpdated(@Observes(during = TransactionPhase.AFTER_COMPLETION) DBUpdated updated) {
//        // TODO: Get the state from "updated" to update the cache
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onDBUpdated ");
//        }
//    }
//
//    public void onUpdatedClub(@Observes(during = TransactionPhase.AFTER_SUCCESS,
//            notifyObserver = Reception.IF_EXISTS) final FamClub famEntity) {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onUpdatedClub during AFTER SUCCESS");
//        }
//        loadAllClubs();
//    }
//
////    public void onUpdatedTeam(@Observes(notifyObserver = Reception.IF_EXISTS) final FamTeam famEntity) {
////        LOGGER.info("cacheBean::onUpdatedTeam ");
////        loadAllTeams();
////    }
//
//    public void onUpdatedOrganization(@Observes(notifyObserver = Reception.IF_EXISTS) final FamOrganization famEntity) {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onUpdatedOrganization ");
//        }
//        loadAllOrganizations();
//    }
//
//    public void onUpdatedSeason(@Observes(notifyObserver = Reception.IF_EXISTS) final FamSeason famEntity) {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onUpdatedSeason ");
//        }
//        loadAllSeasons();
//    }
//
//    public void onUpdatedFormation(@Observes(notifyObserver = Reception.IF_EXISTS) final FamFormation famEntity) {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onUpdatedFormation ");
//        }
//        loadAllFormations();
//    }
//
//    public void onInitDataDone(@Observes InitDataDone event) {
//        // TODO: Get the state from "updated" to update the cache
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cacheBean::onInitDataDone ");
//        }
//        initCache();
//    }
    // Update the cache every 30 seconds
//    @Schedule(hour = "*", minute = "*", second = "*/30")
//    @LoggingInterceptor

    public void initCache() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("IN: updateCache");
        }
        // CLUBS
        loadAllClubs();

        // ORGANIZATIONS
        loadAllOrganizations();
        // SEASONS
        loadAllSeasons();
        // PLACES
        loadAllPlaces();
        // TYP PLACES
        loadAllTypPlaces();
        //TYPCARD
        loadAllTypCards();
        // TYP EVENT
        loadAllTypEvents();
        //EVENT STATUS
        loadAllEventStatuses();
        //TYP COMPETITIONS
        loadAllTypCompetitions();
        //SCALES
        loadAllScales();
        //COMPETITIONS
        loadAllCompetitions();


        //POSITION
        loadAllPositions();
        //TYP MATCH
        loadAllTypMatchs();
        //TYP ANSWER
        loadAllTypAnswers();
        //COUNTRY
        loadAllCountries();
        //STATES
        loadAllStates();
        //PROVINCE
        loadAllProvinces();
        //CITY
        loadAllCities();
        //FORMATION
        loadAllFormations();

    }

    private void loadAllClubs() {
        listClub.clear();

        for (FamClub o : ejbClub.findAll()) {
            listClub.add(o);
        }
    }


    private void loadAllOrganizations() {
        listOrganization.clear();

        for (FamOrganization o : ejbOrganization.findAll()) {
            listOrganization.add(o);
        }
    }

    private void loadAllSeasons() {
        listSeason.clear();

        for (FamSeason o : ejbSeason.findAll()) {
            listSeason.add(o);
        }
        currentSeason = ejbSeason.getCurrentSeason();
    }

    private void loadAllPlaces() {
        listPlace.clear();

        for (FamPlace o : ejbPlace.findAll()) {
            listPlace.add(o);
        }
    }

    private void loadAllTypPlaces() {
        listTypPlace.clear();

        for (FamTypPlace o : ejbTypPlace.findAll()) {
            listTypPlace.add(o);
        }
    }

    private void loadAllTypEvents() {
        listTypEvent.clear();

        for (FamTypEvent o : ejbTypEvent.findAll()) {
            listTypEvent.add(o);
        }
        typEventMatch = ejbTypEvent.getMatch();
    }

    private void loadAllEventStatuses() {
        listEventStatus.clear();

        for (FamEventStatus o : ejbEventStatus.findAll()) {
            listEventStatus.add(o);
        }
        eventStatusScheduled = ejbEventStatus.getScheduled();
    }

    private void loadAllTypCompetitions() {
        listTypCompetition.clear();

        for (FamTypCompetition o : ejbTypCompetition.findAll()) {
            listTypCompetition.add(o);
        }
    }

    private void loadAllScales() {
        listScale.clear();

        for (FamScale o : ejbScale.findAll()) {
            listScale.add(o);
        }
    }

    private void loadAllCompetitions() {
        listSeasonCompetition.clear();

        for (FamSeasonCompetition o : ejbSeasonCompetition.findAll()) {
            listSeasonCompetition.add(o);
        }
    }


    private void loadAllPositions() {
        listPosition.clear();

        for (FamPosition o : ejbPosition.findAll()) {
            listPosition.add(o);
        }
    }

    private void loadAllTypMatchs() {
        listTypMatch.clear();

        for (FamTypMatch o : ejbTypMatch.findAll()) {
            listTypMatch.add(o);
        }
    }

    private void loadAllTypAnswers() {
        listTypAnswer.clear();

        for (FamTypAnswer o : ejbTypAnswer.findAll()) {
            listTypAnswer.add(o);
        }
    }

    private void loadAllCountries() {
        listCountry.clear();

        for (FamCountry o : ejbCountry.findAll()) {
            listCountry.add(o);
        }
    }

    private void loadAllProvinces() {
        listProvince.clear();

        for (FamProvince o : ejbProvince.findAll()) {
            listProvince.add(o);
        }
    }

    private void loadAllStates() {
        listState.clear();

        for (FamState o : ejbState.findAll()) {
            listState.add(o);
        }
    }

    private void loadAllCities() {
        listCity.clear();

        for (FamCity o : ejbCity.findAll()) {
            listCity.add(o);
        }
    }

    private void loadAllTypCards() {
        listTypCard.clear();

        for (FamTypCard o : ejbTypCard.findAll()) {
            listTypCard.add(o);
        }
    }

    private void loadAllFormations() {
        listFormation.clear();

        for (FamFormation o : ejbFormation.findAll()) {
            listFormation.add(o);
        }
    }


    @Produces
    @Named(value = "currentSeason")
    public FamSeason getCurrentSeason() {
        return currentSeason;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    public void setEjbClub(FamClubFacade ejbClub) {
        this.ejbClub = ejbClub;
    }

    public void setEjbOrganization(FamOrganizationFacade ejbOrganization) {
        this.ejbOrganization = ejbOrganization;
    }

    public void setEjbSeason(FamSeasonFacade ejbSeason) {
        this.ejbSeason = ejbSeason;
    }

    public void setEjbPlace(FamPlaceFacade ejbPlace) {
        this.ejbPlace = ejbPlace;
    }

    public void setEjbTypPlace(FamTypPlaceFacade ejbTypPlace) {
        this.ejbTypPlace = ejbTypPlace;
    }

    public void setEjbTypEvent(FamTypEventFacade ejbTypEvent) {
        this.ejbTypEvent = ejbTypEvent;
    }

    public void setEjbEventStatus(FamEventStatusFacade ejbEventStatus) {
        this.ejbEventStatus = ejbEventStatus;
    }

    public void setEventStatusScheduled(FamEventStatus eventStatusScheduled) {
        this.eventStatusScheduled = eventStatusScheduled;
    }

    public void setEjbTypCompetition(FamTypCompetitionFacade ejbTypCompetition) {
        this.ejbTypCompetition = ejbTypCompetition;
    }

    public void setEjbScale(FamScaleFacade ejbScale) {
        this.ejbScale = ejbScale;
    }

    public void setEjbSeasonCompetition(FamSeasonCompetitionFacade ejbSeasonCompetition) {
        this.ejbSeasonCompetition = ejbSeasonCompetition;
    }

    public void setEjbPosition(FamPositionFacade ejbPosition) {
        this.ejbPosition = ejbPosition;
    }

    public void setEjbTypMatch(FamTypMatchFacade ejbTypMatch) {
        this.ejbTypMatch = ejbTypMatch;
    }

    public void setEjbTypAnswer(FamTypAnswerFacade ejbTypAnswer) {
        this.ejbTypAnswer = ejbTypAnswer;
    }

    public void setEjbCountry(FamCountryFacade ejbCountry) {
        this.ejbCountry = ejbCountry;
    }

    public void setEjbState(FamStateFacade ejbState) {
        this.ejbState = ejbState;
    }

    public void setEjbProvince(FamProvinceFacade ejbProvince) {
        this.ejbProvince = ejbProvince;
    }

    public void setEjbCity(FamCityFacade ejbCity) {
        this.ejbCity = ejbCity;
    }

    public void setEjbTypCard(FamTypCardFacade ejbTypCard) {
        this.ejbTypCard = ejbTypCard;
    }

    public void setEjbFormation(FamFormationFacade ejbFormation) {
        this.ejbFormation = ejbFormation;
    }
}