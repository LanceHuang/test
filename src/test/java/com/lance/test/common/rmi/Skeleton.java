package com.lance.test.common.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Skeleton {
    public static void main(String[] args) throws RemoteException {
        IUserHandler userHandler = new UserHandlerImpl();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("user", userHandler);
//        Naming.rebind();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
