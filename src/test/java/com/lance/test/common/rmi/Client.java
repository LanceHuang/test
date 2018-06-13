package com.lance.test.common.rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);

        Object remoteObj = registry.lookup("user");
        if (remoteObj instanceof IUserHandler) {
            IUserHandler userHandler = (IUserHandler) remoteObj;
            System.out.println(userHandler.getUserById(99));
        }
    }
}
