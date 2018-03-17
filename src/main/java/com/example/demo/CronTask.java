package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.BusinessDao;

@Component
public class CronTask {
	@Autowired
	BusinessDao businessDao;
	
    @Scheduled(cron="0 0 0 * * ?")
    public void updatahuobi() {
    	businessDao.updatahuobi();
    	businessDao.updataday();
    	businessDao.deletedan();
    }
    
}
