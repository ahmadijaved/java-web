package be.isl.orders.ui;

import be.isl.orders.business.OrdersFacade;
import be.isl.orders.entity.Orders;
import be.isl.orders.ui.viewmodel.OrdersSearch;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@Named("planningController")
@SessionScoped
public class PlanningController
        extends AbstractController<Orders, OrdersFacade, OrdersSearch>
        implements Serializable {

    @Inject
    private OrdersFacade ejbFacade;

    private List<Orders> items = null;
    private Orders selected;

    private Date scheduleInitialDate;

    private ScheduleModel eventModel;
    private PlanningScheduleEvent event = new PlanningScheduleEvent();

    public PlanningController() {
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();

        items = ejbFacade.findAll();
        items.forEach((o) -> {
            eventModel.addEvent(new PlanningScheduleEvent(o));
        });
    }

    @Override
    public OrdersFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public Orders create() {
        return new Orders();
    }

    public Date getScheduleInitialDate() {
        return scheduleInitialDate;
    }

    public void setScheduleInitialDate(Date scheduleInitialDate) {
        this.scheduleInitialDate = scheduleInitialDate;
    }

    // <editor-fold defaultstate="collapsed" desc="Schedule related methods"> 
    // 
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public PlanningScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(PlanningScheduleEvent event) {
        this.event = event;
    }

    public void addEvent() {
        System.out.println("addEvent");
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            event.setStartDate(event.convertToLocalDateTime(((Orders) event.getData()).getOrderDate()));
            event.setEndDate(event.getStartDate().plusHours(12));
            eventModel.updateEvent(event);
            ejbFacade.edit((Orders) event.getData());
        }
        event = new PlanningScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (PlanningScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new PlanningScheduleEvent("",
                (Date) selectEvent.getObject(),
                (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        Orders o = (Orders) event.getScheduleEvent().getData();
        Calendar cal = Calendar.getInstance();
        
       
        cal.setTime(o.getOrderDate());
        cal.add(Calendar.DAY_OF_YEAR, event.getDayDelta());
        o.setOrderDate(cal.getTime());
        ejbFacade.edit(o);
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Event resized", "Day delta:" + event.getDayDeltaStart() + 
                        ", Minute delta:" + event.getMinuteDeltaStart());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // </editor-fold>
    @Override
    public Orders getSelected() {
        return selected;
    }

    @Override
    public void setSelected(Orders selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
}

class PlanningScheduleEvent extends DefaultScheduleEvent {

    private Orders o;

    public PlanningScheduleEvent(Orders o) {
        this.o = o;
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(o.getOrderDate());

        super.setStartDate(convertToLocalDateTime(calDate.getTime()));
        calDate.add(Calendar.HOUR, 12);

        super.setEndDate(convertToLocalDateTime(calDate.getTime()));
        super.setTitle(o.toString());

        super.setData(o);
    }

    PlanningScheduleEvent() {
        o = new Orders();
        super.setData(o);
    }

    PlanningScheduleEvent(String string, Date startDate, Date endDate) {
        o = new Orders();
        o.setOrderDate(startDate);
        super.setStartDate(convertToLocalDateTime(startDate));
        super.setEndDate(convertToLocalDateTime(endDate));

        super.setData(o);
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Orders getOrders() {
        return o;
    }

    public void setOrders(Orders o) {
        this.o = o;
    }
}