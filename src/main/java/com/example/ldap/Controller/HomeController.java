package com.example.ldap.Controller;

import com.example.ldap.Data.Goods;
import com.example.ldap.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HomeController {
    @RequestMapping("/user")
    public String index() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //get username
        String username = auth.getName();

        System.out.println("Username: " + username);

        return "index";
    }

    @Autowired
    private GoodsService goodsService;
    @PostMapping("/doUpdateGoods")
    public String doUpdateGoods(Goods entity){
        goodsService.updateGoods(entity);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public String doFinfById(@PathVariable Integer id, Model model){
        Goods goods = goodsService.findById(id);
        model.addAttribute("g", goods);
        return "goods-update";
    }
    @RequestMapping("/doSaveGoods")
    public String doSaveGoods (Goods entity) {// receiving client parameters with POJO object
        goodsService.saveGoods(entity);
        return "redirect:/";
    }
    @RequestMapping("/new")
    public String doGoodsAddUI(){
        return "goods-adds";
    }

    @RequestMapping("/delete/{id}") // rest style URL
    public String doDeleteById (@PathVariable Integer id) {// @ pathvariable describes a parameter, which means that the value of the parameter comes from the URL
        goodsService.deleteById(id);
        return "redirect:/";// Redirect indicates redirection (the client sends a request to the server again)
    }//    @RequestMapping("doDeleteById")//    public String doDeleteById(Integer id){//        goodsService.deleteById(id);//        return "redirect:/goods/doGoodsUI";// Redirct indicates redirection (the client sends a request to the server again)//    }
    @RequestMapping("/")
    public String doGoodsUI(Model model){
        List<Goods> goodsList=goodsService.findGoods();
        model.addAttribute("goodsList", goodsList);
        return "index";//Viewname
        //The return value is given to dispatcher serv
        //Let
        //Dispatcherservlet will call viewresolver for view resolution (view + model)
        //Finally, the dispatcher servlet responds the parsing result to the page
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }
}
