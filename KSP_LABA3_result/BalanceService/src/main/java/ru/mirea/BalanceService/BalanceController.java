package ru.mirea.BalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.juli.logging.*;

import java.util.logging.Logger;

@Controller
public class BalanceController {

    //private final Log log = LogFactory.getLog(BalanceController.class);
    private Logger log = Logger.getLogger(BalanceController.class.getName());

    @Autowired
    private BalanceDbConnection balDbCon;

    @RequestMapping(value = "user/balance/{id}/{bal}", method = RequestMethod.PUT)
    @ResponseBody
    public String putBalance(@PathVariable int id, @PathVariable long bal){
        log.info("Контроллер Баланса принял запрос на пополнение баланса баланса");
        String temp = balDbCon.updateBalance(id, bal);
        log.info("Констроллер Баланса отправляет результат запроса");
        return temp;
    }

    @RequestMapping (value = "user/balance/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public Balance getBalance(@PathVariable int user_id){
        log.info("Контроллер Баланса принял запрос на получение баланса");
        Balance temp = balDbCon.getBalance(user_id);
        log.info("Констроллер Баланса отправляет результат запроса");
        return temp;
    }

    @RequestMapping (value = "user/balance/change_currency/{user_id}/{change_currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String changeCurrency(@PathVariable int user_id,@PathVariable String change_currency){return balDbCon.changeCurrency(user_id, change_currency);}

    @RequestMapping (value = "user/currency/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrency(@PathVariable int user_id){return balDbCon.getCurrency(user_id);}

    //Вычитание баланса
    @RequestMapping (value = "admin/update/balance/{id}/{balance}", method = RequestMethod.POST)
    @ResponseBody
    public String updateBalance2(@PathVariable int id,@PathVariable double balance){return balDbCon.updateBalance2(id,balance);}

}
