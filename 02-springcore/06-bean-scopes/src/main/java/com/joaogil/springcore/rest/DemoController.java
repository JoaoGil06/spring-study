package com.joaogil.springcore.rest;

import com.joaogil.springcore.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Definir um campo privado para a dependência
    private Coach myCoach;
    private Coach anotherCoach;
    private Coach baseballCoach;
    private Coach anotherBaseballCoach;

    // Definir um constructor para dependency injection
    // Como "trackCoach" é um singleton, tem de apontar para a mesma instância
    // Como "baseballCoach" é um prototype, são duas instâncias diferentes do mesmo tipo de objecto
    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach, @Qualifier("trackCoach") Coach theAnotherCoach, @Qualifier("baseballCoach") Coach theBaseballCoach, @Qualifier("baseballCoach") Coach theAnotherBaseballCoach) {
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;

        baseballCoach = theBaseballCoach;
        anotherBaseballCoach = theAnotherBaseballCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        // Se fosse "prototype" iria retornar false, porque é criado um objecto para cada instância
        return "Comparing beans: myCoach == anotherCoach (Porque é singleton), " + (myCoach == anotherCoach) + " | Agora comparar os beans de prototype (Baseballs - tem de ser false porque são prototype) ," + (baseballCoach == anotherBaseballCoach);
    }
}
