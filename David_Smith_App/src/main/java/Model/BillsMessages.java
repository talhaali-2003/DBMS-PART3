package Model;

import java.util.Date;

public class BillsMessages {
	private int id;
	private int userid;
	private int billid;
	private Date msgtime;
	private double price;
	private Date schedulestart;
	private Date scheduleend;
	private String note;

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public Date getMsgtime() {
		return msgtime;
	}

	public void setMsgtime(Date msgtime) {
		this.msgtime = msgtime;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @param id
	 * @param userid
	 * @param billid
	 * @param msgtime
	 * @param price
	 * @param schedulestart
	 * @param scheduleend
	 * @param note
	 */
	public BillsMessages(int id, int userid, int billid, Date msgtime, double price, Date schedulestart,
			Date scheduleend, String note) {
		super();
		this.id = id;
		this.userid = userid;
		this.billid = billid;
		this.msgtime = msgtime;
		this.price = price;
		this.schedulestart = schedulestart;
		this.scheduleend = scheduleend;
		this.note = note;
	}

	/**
	 * 
	 */
	public BillsMessages() {
		super();
		// TODO Auto-generated constructor stub
	}

}