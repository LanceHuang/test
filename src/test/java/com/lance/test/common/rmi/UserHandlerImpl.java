package com.lance.test.common.rmi;

import java.rmi.RemoteException;

public class UserHandlerImpl implements IUserHandler {

    public UserHandlerImpl() throws RemoteException {
        super();
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        User user = new User();
        user.setName("Lance:" + id);
        user.setAge(id * 10);
        return user;
    }

    @Override
    public User getUserByName(String name) throws RemoteException {
        User user = new User();
        user.setName("Name:" + name);
        user.setAge(name.length() * 10);
        return user;
    }

    @Override
    public String info(User user) throws RemoteException {
        return user.toString();
    }
}
