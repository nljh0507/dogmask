package util;

import java.sql.Timestamp;

public class VisitorDTO {
	private String Dog_unique_number;
	private String IDmidad;
	private String name;
	private String phone;
	private Timestamp Visittime;
	private String Dname;
	private boolean Dmuzzle;
	private float Dtemp;
	private boolean Enter;
	private String Location;
	private String ImageURL;
	private int Dnum;

	public String getDog_unique_number() {
		return Dog_unique_number;
	}

	public void setDog_unique_number(String dog_unique_number) {
		Dog_unique_number = dog_unique_number;
	}

	public String getIDmidad() {
		return IDmidad;
	}

	public boolean isEnter() {
		return Enter;
	}

	public void setEnter(boolean enter) {
		Enter = enter;
	}

	public void setIDmidad(String idmidad) {
		IDmidad = idmidad;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getVisittime() {
		return Visittime;
	}

	public void setVisittime(Timestamp visittime) {
		Visittime = visittime;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public boolean isDmuzzle() {
		return Dmuzzle;
	}

	public void setDmuzzle(boolean dmuzzle) {
		Dmuzzle = dmuzzle;
	}

	public float getDtemp() {
		return Dtemp;
	}

	public void setDtemp(float dtemp) {
		Dtemp = dtemp;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public int getDnum() {
		return Dnum;
	}

	public void setDnum(int dnum) {
		Dnum = dnum;
	}

	public VisitorDTO() {

	}

	public VisitorDTO(String dog_unique_number, String idmidad, Timestamp visittime, String dname, boolean dmuzzle, float dtemp,
			boolean enter, String location, String imageURL) {
		super();
		Dog_unique_number = dog_unique_number;
		IDmidad = idmidad;
		Visittime = visittime;
		Dname = dname;
		Dmuzzle = dmuzzle;
		Dtemp = dtemp;
		Enter = enter;
		Location = location;
		ImageURL = imageURL;
	}

	public VisitorDTO(String dog_unique_number, String idmidad, String name, String phone, Timestamp visittime,
			String dname, boolean dmuzzle, float dtemp, boolean enter, String location, String imageURL, int dnum) {
		super();
		Dog_unique_number = dog_unique_number;
		IDmidad = idmidad;
		this.name = name;
		this.phone = phone;
		Visittime = visittime;
		Dname = dname;
		Dmuzzle = dmuzzle;
		Dtemp = dtemp;
		Enter = enter;
		Location = location;
		ImageURL = imageURL;
		Dnum = dnum;
	}
}
