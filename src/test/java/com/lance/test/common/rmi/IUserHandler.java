package com.lance.test.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserHandler extends Remote {

    User getUserById(int id) throws RemoteException;

    User getUserByName(String name) throws RemoteException;

    String info(User user) throws RemoteException;
}
