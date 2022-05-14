package by.it.academy.homework_1.controller.web.controllers.jsp;

import by.it.academy.homework_1.services.api.IStatisticsService;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IUserService userService;
    private final IStatisticsService statisticsService;

    public AdminController(IUserService userService, IStatisticsService statisticsService) {
        this.userService = userService;
        this.statisticsService = statisticsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", this.userService.getAll());
        return "admin/users";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model) {
        model.addAttribute("stats", this.statisticsService.getStats());
        return "admin/statistics";
    }
}
