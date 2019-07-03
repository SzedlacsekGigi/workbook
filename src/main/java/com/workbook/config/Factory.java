package com.workbook.config;

public class Factory {

    public com.workbook.dao.OopDao oopDaoData;
    public ConnectToDao connectToDao = ConnectToDao.getInstance();


    public Factory() {
            oopDaoData = com.workbook.dao.implementation.OopDaoSQL.getInstance();
        }
    }
