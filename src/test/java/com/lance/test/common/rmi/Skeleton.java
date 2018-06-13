package com.lance.test.common.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Skeleton {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        IUserHandler userHandler = new UserHandlerImpl();

//        int port = 2016;
//        UnicastRemoteObject.exportObject(userHandler, port);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("user", userHandler);
    }
}
