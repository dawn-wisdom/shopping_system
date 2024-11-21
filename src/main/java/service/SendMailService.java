package service;

import dao.SendMailDao;
public class SendMailService {
    private SendMailDao sendMailDao=new SendMailDao();
    public boolean sendMail(String receiver){
       if(sendMailDao.sendMail2Customer(receiver))
       {
           return true;
       }
       else
       {
           return false;
       }
    }
}
