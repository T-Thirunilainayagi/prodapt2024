package layeredarchitectureexample.service;

import layeredarchitectureexample.dao.LoginDAO;
import layeredarchitectureexample.dao.LoginDAOImpl;
import layeredarchitectureexample.model.Login;

public class LoginServiceImpl implements LoginService {

	@Override
	public String verifyUserNameAndPassword(Login login) {
		LoginDAO loginDAO = new LoginDAOImpl();
		Login loginFromDB = loginDAO.getUserDetails(login.getUserName());
		if(login.getPassword().equals(loginFromDB.getPassword())) {
			return "User logged in successfully";
		}
		return "User details mismatch";
		
	}

}
