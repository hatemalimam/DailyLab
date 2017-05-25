/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.dailylab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.config.PrimeConfiguration;
import org.primefaces.config.StartupPrimeConfiguration;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author hatemalimam
 */
@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

	/**
	 * Creates a new instance of MainBean
	 */
	private String currentNav;

	private List<UserPojo> list;

	private String selectedTextInArea;

	private Map<String, String> map;

	private List<String> selectedList;

	private PieChartModel pieModel1;

	private ScheduleModel model;

	private List<String> batImages;

	private String pfVersion;

	private Double doubleValue;

	private UserPojo userPojo;

	private DefaultDiagramModel diagramModel;

	public MainBean() {
		currentNav = "/checkBoxesJQuery/main.xhtml";
		fillList();
		createPieModel1();
		createDiagramModel();

		doubleValue = 2d;

		model = new DefaultScheduleModel();
		model.addEvent(new DefaultScheduleEvent("Event1", new Date(), new Date()));

		batImages = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			batImages.add("bat" + i + ".jpg");
		}

		PrimeConfiguration config = new StartupPrimeConfiguration(FacesContext.getCurrentInstance());
		pfVersion = RequestContext.getCurrentInstance().getApplicationContext().getConfig().getBuildVersion();
	}

	public UserPojo getUserPojo() {
		return userPojo;
	}

	public void setUserPojo(UserPojo userPojo) {
		this.userPojo = userPojo;
	}

	public ScheduleModel getModel() {
		return model;
	}

	public void updateNav() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		currentNav = (String) map.get("currentNav");
	}

	public void setSelectedText() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		selectedTextInArea = (String) map.get("selectedText");
	}

	public String getCurrentNav() {
		return currentNav;
	}

	public void setCurrentNav(String currentNav) {
		this.currentNav = currentNav;
	}

	public List<UserPojo> getList() {
		return list;
	}

	public void setList(List<UserPojo> list) {
		this.list = list;
	}

	public void fillList() {
		list = new ArrayList<UserPojo>();
		list.add(new UserPojo("Jack", 10));
		list.add(new UserPojo("Jhon", 20));
		list.add(new UserPojo("Smack", 30));
		list.add(new UserPojo("Jimmy", 40));
		list.add(new UserPojo("Dummu", 50));
		list.add(new UserPojo("Stakr", 60));
		list.add(new UserPojo("Simi", 70));

		map = new HashMap<String, String>();
		map.put("Jack", "Jack");
		map.put("Jhon", "Jhon");
		map.put("Smack", "Smack");
		map.put("Jimmy", "Jimmy");
		map.put("Dummu", "Dummu");
		map.put("Stakr", "Stakr");
		map.put("Simi", "Simi");
	}

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public String getSelectedTextInArea() {
		return selectedTextInArea;
	}

	public void setSelectedTextInArea(String selectedTextInArea) {
		this.selectedTextInArea = selectedTextInArea;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public List<String> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<String> selectedList) {
		this.selectedList = selectedList;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public List<String> getBatImages() {
		return batImages;
	}

	public void setBatImages(List<String> batImages) {
		this.batImages = batImages;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public DefaultDiagramModel getDiagramModel() {
		return diagramModel;
	}

	public void setDiagramModel(DefaultDiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		pieModel1.set("Brand 1", 540);
		pieModel1.set("Brand 2", 325);
		pieModel1.set("Brand 3", 702);
		pieModel1.set("Brand 4", 421);

	}

	private void createDiagramModel() {
		diagramModel = new DefaultDiagramModel();
		diagramModel.setMaxConnections(-1);

		Element elementA = new Element("A", "20em", "6em");
		elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
		elementA.setId("element-a");

		Element elementB = new Element("B", "10em", "18em");
		elementB.setId("element-b");
		elementB.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));

		Element elementC = new Element("C", "40em", "18em");
		elementC.setId("element-c");
		elementC.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		

		diagramModel.addElement(elementA);
		diagramModel.addElement(elementB);
		diagramModel.addElement(elementC);

		diagramModel.connect(new Connection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));
		diagramModel.connect(new Connection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
	}

	public String getPfVersion() {
		return pfVersion;
	}

	public void setPfVersion(String pfVersion) {
		this.pfVersion = pfVersion;
	}

	public void sendLastCheckedBox() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		String submitedValue = (String) map.get("submitedValue");
		FacesMessage facesMessage = new FacesMessage(submitedValue);
		facesContext.addMessage(null, facesMessage);
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String paramName = "title[" + event.getFile().getFileName() + "]";
		String fileWithTitle = (String) map.get(paramName);
		fileWithTitle = (fileWithTitle == null) ? "not found" : "";
		RequestContext.getCurrentInstance().execute("$('#result').append('" + "<li> File Name: <span class=\"semi-bold\">" + event.getFile().getFileName() + "</span> Title: <span class=\"semi-bold\">" + fileWithTitle + "</span></li>')");
	}

	public void addMessage(String message, String to) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(message);
		facesContext.addMessage(to, facesMessage);
	}

	public void addMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(message);
		facesContext.addMessage(null, facesMessage);
	}

	public void activeDialog() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map map = facesContext.getExternalContext().getRequestParameterMap();
		String submitedValue = (String) map.get("activeDialog");
		FacesMessage facesMessage = new FacesMessage(submitedValue);
		facesContext.addMessage(null, facesMessage);
	}

	public void onDrop(DragDropEvent dragDropEvent) {
		String dargId = dragDropEvent.getDragId();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String left = params.get(dargId + "_left");
		String top = params.get(dargId + "_top");
		addMessage("Left: " + left + " Top: " + top);
	}

	public void deleteUser(UserPojo user) {
		list.remove(user);
	}
}
