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

    public  boolean sendMail2Seller(int orderId)
    {
        if(sendMailDao.sendMail2Seller(orderId))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
