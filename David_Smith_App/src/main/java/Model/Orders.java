package Model;

import java.util.Date;

public class Orders {
	private int id;
	private int quoteid;
	private double price;
	private Date schedulestart;
	private Date scheduleend;
            private Date scheduleEnd; // Add this line

	// Constructors
	public Orders() {
	}

	public Orders(int id, int quoteid, double price, Date schedulestart, Date scheduleend) {
		this.id = id;
		this.quoteid = quoteid;
		this.price = price;
		this.schedulestart = schedulestart;
		this.scheduleend = scheduleend;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuoteid() {
		return quoteid;
	}

	public void setQuoteid(int quoteid) {
		this.quoteid = quoteid;
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

	public Date getScheduleend() {
		return scheduleend;
	}

	public void setScheduleend(Date scheduleend) {
		this.scheduleend = scheduleend;
	}
}
