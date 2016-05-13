package com.kyawn.test;



public class UserDaoTest {

	/*public static void main(String[] args) {

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			UserDao userDao = new UserDaoImpl();
			User tom = new User();
			tom.setName("wocao");
			tom.setPassword("nima");
			tom.setId((long)13);
			
			userDao.save(conn, tom);
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}*/
	
	/*public static void main(String[] args) {
		User user = new User();
		user.setName("admin");
		CheckUserService cs = new CheckUserService();
		System.out.println(cs.isVerified(user));
	}*/
}
