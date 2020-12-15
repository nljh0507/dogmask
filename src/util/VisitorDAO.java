package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VisitorDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String SQL = "";

	public ArrayList<VisitorDTO> getList(Date start, Date end, String searchType, String search, int pageNumber,
			String sortType, String sortDirection) {
		ArrayList<VisitorDTO> visitorList = null;
		try {
			if (searchType == "")
				searchType = "Dname";
			
			if (sortType == "")
				sortType = "Visittime";
			if (sortType.equals("dog_unique_number_FK")) {
				System.out.println(sortType);
				sortType = "duser.dog_unique_number_FK";
				System.out.println(sortType);
			}
			if (sortDirection == "")
				sortDirection = "DESC";

			SQL = "SELECT * FROM duser JOIN member ON member.dog_unique_number_FK = duser.dog_unique_number_FK WHERE DATE(Visittime) BETWEEN ? AND ? AND "
					+ searchType + " LIKE ? ORDER BY " + sortType + " " + sortDirection + " LIMIT " + pageNumber * 15
					+ ", " + (pageNumber * 15 + 16);
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setDate(1, start);
			pstmt.setDate(2, end);
			pstmt.setString(3, '%' + search + '%');
			// System.out.println(pstmt);
			rs = pstmt.executeQuery();

			visitorList = new ArrayList<VisitorDTO>();
			while (rs.next()) {
				VisitorDTO visitor = new VisitorDTO(rs.getString(1), // Dog_unique_number
						rs.getString(2), // IDmidad
						rs.getString(13), // name
						rs.getString(15), // phone
						rs.getTimestamp(3), // Visittime
						rs.getString(4), // Dname
						rs.getBoolean(5), // Dmuzzle
						rs.getFloat(6), // Dtemp
						rs.getBoolean(9), // Enter
						rs.getString(7), // Location
						rs.getString(10), // ImageURL
						rs.getInt(8) // Dnum
				);
				visitorList.add(visitor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return visitorList;
	}

	public void addVisitor(VisitorDTO visitor) {
		com test1 = new com(); // TODO: rename // need?

		try {
			SQL = "INSERT INTO duser (dog_unique_number_FK, IDmidad_FK, Visittime, Dname, Dmuzzle, Dtemp, Location, Check_enter, image_URL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, visitor.getDog_unique_number());
			pstmt.setString(2, visitor.getIDmidad()); // TODO: change
			pstmt.setTimestamp(3, visitor.getVisittime());
			pstmt.setString(4, visitor.getDname());
			pstmt.setBoolean(5, visitor.isDmuzzle());
			pstmt.setDouble(6, visitor.getDtemp());
			pstmt.setString(7, visitor.getLocation());
			pstmt.setBoolean(8, visitor.isEnter());
			pstmt.setString(9, visitor.getImageURL());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}