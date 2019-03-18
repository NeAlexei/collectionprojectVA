package com.kinomo;

import com.google.gson.reflect.TypeToken;
import com.kinomo.dao.DAO;
import com.kinomo.dao.DAOFactory;
import com.kinomo.dao.DAOFactory.DAOKeys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.kinomo.dao.JsonDAO;
import com.kinomo.model.User;
import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        DAO dao = DAOFactory.get(DAOKeys.DATABASE);

        //.........     FOR  DATABASE          ..............


        dao.initialize("users");
        System.out.println("--------------");
        System.out.println(dao.getById("59006ac4218b450c1e10276d").getName());
        System.out.println("--------------");
        //System.out.println(dao.getAll());
        System.out.println("--------------");
       // System.out.println(dao.getUnique());

 //******       FOR  JSON:  ***********

       /* String user = "src/main/java/com/kinomo/users.json";
        System.out.println(dao.getById("1234").getName());
        System.out.println("*******");

        dao.getAll();
        System.out.println("*******");

        System.out.println(dao.getUnique());*/




    }
}
