package Model;

import java.util.Date;

public class Quotes {
	private int id;
	private int contractorid;
	private int clientid;
	private double price;
	private Date schedulestart;
	private Date scheduleend;
	
	private String timeWindow;
	private String note;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContractorid() {
		return contractorid;
	}

	public void setContractorid(int contractorid) {
		this.contractorid = contractorid;
	}

	public String getTimeWindow() {
		return timeWindow;
	}

	public void setTimeWindow(String timeWindow) {
		this.timeWindow = timeWindow;
	}

	public String getMote() {
		return note;
	}

	public void setMote(String mote) {
		this.note = mote;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getSchedulestart() {
		return schedulestart;
	}

	public void setSchedulestart(Date schedulestart) {
		this.schedulestart = schedulestart;
	}

	/**
	 * 
	 */
	public Quotes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param contractorid
	 * @param clientid
	 * @param price
	 * @param schedulestart
	 * @param scheduleend
	 */
	public Quotes(int id, int contractorid, int clientid, double price, Date schedulestart, Date scheduleend) {
		super();
		this.id = id;
		this.contractorid = contractorid;
		this.clientid = clientid;
		this.price = price;
		this.schedulestart = schedulestart;
		this.scheduleend = scheduleend;
	}

	public Date getScheduleend() {
		return scheduleend;
	}

	public void setScheduleend(Date scheduleend) {
		this.scheduleend = scheduleend;
	}

	// Getters and setters

}