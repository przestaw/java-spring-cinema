package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Ticeting app is running\n";
    }
    @RequestMapping("/hi")
    public String welcome() {
        return "Java_Cinema_Ticketing_App\n" +
                "Prepared for the TouK recruitment\n" +
                "by Przemysław Stawczyk\n";
    }

}