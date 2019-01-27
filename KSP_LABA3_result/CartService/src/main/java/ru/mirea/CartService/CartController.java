package ru.mirea.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class CartController {

    private final Logger log = Logger.getLogger(CartController.class.getName());

    @Autowired
    private ServiceForCart cs;

    @RequestMapping(value = "user/cart/{user_id}/item/{pet_or_stuff}/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String cart_putItem(@PathVariable String pet_or_stuff,@PathVariable int user_id,@PathVariable int id) throws JsonProcessingException {
        log.info("Контроллер Корзины принял запрос на добавление в корзину");
        String temp = cs.putItem_inCart(pet_or_stuff,user_id, id);
        log.info("Контроллер Корзины отправляет результат запроса на добавление в корзину: "+ temp);
        return temp;
    }


    @RequestMapping(value = "user/delete_cart/{user_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String cart_deleteItem(@PathVariable int user_id) {
        return cs.deleteCart(user_id);
    }

    //нельзя обращаться пользователям
    @RequestMapping(value = "admin/cart/{user_id}/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOneItem(@PathVariable int user_id,@PathVariable int id) {
        return cs.deleteOneItem(user_id,id);
    }


    @RequestMapping(value = "user/get_cart/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Cart> cart_getCart(@PathVariable int user_id) {
         return cs.getCart(user_id);
    }


    @RequestMapping (value = "user/pay_cart/{user_id}", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@PathVariable int user_id) {
        log.info("Контроллер Корзины принял запрос на оплату корзины");
        String temp = cs.payCart(user_id);
        log.info("Контроллер Корзины отправляет результат запроса покупки: "+ temp);
        return temp;
    }

    @RequestMapping (value = "user/get_cart_cost/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public String get_cart_cost(@PathVariable int user_id) {
        return  cs.get_cart_cost(user_id);

    }

}

