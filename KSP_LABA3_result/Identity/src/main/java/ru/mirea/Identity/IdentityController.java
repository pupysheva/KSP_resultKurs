package ru.mirea.Identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;


@Controller
public class IdentityController {

    private final Logger log = Logger.getLogger(IdentityController.class.getName());

    @Autowired
    private IdentityService identityService;


    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public Token getToken(@RequestBody User user) throws Exception {
        log.info("Контроллер Аутендификации принял запрос на аутендификацию");
        Token tokenTemp = identityService.getToken(user.getLogin(), user.getPassword());
        log.info("Контроллер  Аутендификации отправляет результат запроса");
        return tokenTemp;
    }

    @RequestMapping(value = "sign/up", method = RequestMethod.POST)
    @ResponseBody
    public Token addUser(@RequestBody User user) throws Exception {
        return identityService.addUser(user.getLogin(), user.getPassword());
    }
}
