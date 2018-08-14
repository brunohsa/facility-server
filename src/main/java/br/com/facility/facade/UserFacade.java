package br.com.facility.facade;

import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.IncomeService;
import br.com.facility.service.UserService;
import br.com.facility.webservice.model.request.UserRequest;
import br.com.facility.webservice.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade implements IUserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = new User(
                userRequest.getName(),
                userRequest.getLastName(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getEmail()
        );
        return new UserResponse(userService.save(user));
    }

    @Override
    public User findByUserName(String username) {
        return userService.findByUserName(username);
    }

    @Override
    public void delete() {
        incomeService.deleteAll();
        expenseService.deleteAll();
        userService.delete();
    }
}
