package util;

import java.sql.Timestamp;

public class UserDTO {
	private String IDad;
	private String PASSad;
	private String SECad;
	private String IDparent;
	private Timestamp Signtime;

	public String getIDad() {
		return IDad;
	}

	public void setIDad(String idad) {
		IDad = idad;
	}

	public String getPASSad() {
		return PASSad;
	}

	public void setPASSad(String passad) {
		PASSad = passad;
	}

	public String getSECad() {
		return SECad;
	}

	public void setSECad(String secad) {
		SECad = secad;
	}

	public String getIDparent() {
		return IDparent;
	}

	public void setIDparent(String idparent) {
		IDparent = idparent;
	}

	public Timestamp getSigntime() {
		return Signtime;
	}

	public void setSigntime(Timestamp signtime) {
		Signtime = signtime;
	}

	public UserDTO() {

	}

	public UserDTO(String idad, String passad, String secad) {
		super();
		IDad = idad;
		PASSad = passad;
		SECad = secad;
	}

	public UserDTO(String idad, String passad, String secad, String idparent) {
		super();
		IDad = idad;
		PASSad = passad;
		SECad = secad;
		IDparent = idparent;
	}

	public UserDTO(String idad, String passad, String secad, Timestamp signtime) {
		super();
		IDad = idad;
		PASSad = passad;
		SECad = secad;
		Signtime = signtime;
	}

	public UserDTO(String idad, String passad, String secad, String idparent, Timestamp signtime) {
		super();
		IDad = idad;
		PASSad = passad;
		SECad = secad;
		IDparent = idparent;
		Signtime = signtime;
	}
}
