package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String SQL = "";

	public ArrayList<UserDTO> getList(int pageNumber) {
		ArrayList<UserDTO> userList = null;
		try {
			SQL = "SELECT * FROM midadmin LIMIT " + pageNumber * 15 + ", " + pageNumber * 15 + 16;
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// System.out.println(pstmt);
			rs = pstmt.executeQuery();
			userList = new ArrayList<UserDTO>();
			while (rs.next()) {
				UserDTO user = new UserDTO(rs.getString(1), // IDad
						rs.getString(2), // PASSad
						rs.getString(3), // SECad
						rs.getTimestamp(5) // Visittime
				);
				userList.add(user);
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
		return userList;
	}

	public String getName(String IDad) {
		String SQL = "SELECT SECmidad FROM midadmin WHERE IDmidad = ?";

		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, IDad);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
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
		return null; // 데이터베이스 오류
	}

	public int loginAd(String IDad, String PASSad) {
		String SQL = "SELECT PASSad FROM madmin WHERE IDad = ?";

		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, IDad);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(PASSad)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 틀림
				}
			} else {
				SQL = "SELECT IDmidad FROM midadmin WHERE IDmidad = ?";

				try {
					conn = DB_connection.getConnection();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, IDad);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						return -1; // 사용자 계정
					}
					return -2; // 존재하지 않는 아이디
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
		return -3; // 데이터베이스 오류
	}

	public int login(String IDad, String PASSad) {
		String SQL = "SELECT PASSmidad FROM midadmin WHERE IDmidad = ?";

		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, IDad);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(PASSad)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1;
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
		return -2; // 데이터베이스 오류
	}

	public int register(UserDTO user) {
		String SQL = "INSERT INTO midadmin VALUES (?, ?, ?, 'm1234', ?)";

		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getIDad());
			pstmt.setString(2, user.getPASSad());
			pstmt.setString(3, user.getSECad());
			pstmt.setTimestamp(4, user.getSigntime());
			return pstmt.executeUpdate();
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
		}
		return -1; // 등록 실패 or 데이터베이스 오류
	}

	public int modify(UserDTO user, String oldID) {
		String SQL = "UPDATE midadmin SET IDmidad = ?, PASSmidad = ?, SECmidad = ? WHERE IDmidad = ?";
		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getIDad());
			pstmt.setString(2, user.getPASSad());
			pstmt.setString(3, user.getSECad());
			pstmt.setString(4, oldID);
			return pstmt.executeUpdate();
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
		}
		return -1; // 등록 실패 or 데이터베이스 오류
	}

	public int delete(String IDad) {
		String SQL = "DELETE FROM midadmin WHERE IDmidad=?";

		try {
			conn = DB_connection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, IDad);
			return pstmt.executeUpdate();
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
		}
		return -1; // 삭제 실패 or 데이터베이스 오류
	}
}
