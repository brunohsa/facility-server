package br.com.facility.facade;

import br.com.facility.model.User;
import br.com.facility.webservice.model.request.UserRequest;
import br.com.facility.webservice.model.response.UserResponse;

public interface IUserFacade {

    UserResponse save(UserRequest userRequest);

    User findByUserName(String username);

    void delete();
}
