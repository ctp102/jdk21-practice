package com.demo.jdk21practice.controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name) {
        // arrow, inline : jdk 14
        Loan loan = switch (name) {
            case "jhcho" -> new SecuredLoan();
            default -> new UnSecuredLoan(5);
        };

        // pattern matching : jdk 21
        String message = switch (loan) {
            case SecuredLoan sl -> name + "님은 무이자";
            case UnSecuredLoan usl -> name + "님은 이율이 " + usl.interest();
        };

        return message;
    }

}

// sealed 클래스/인터페이스 : jdk 17
// permits 키워드를 이용하여 상속 또는 구현할 클래스를 미리 지정한다.
// SecuredLoan, UnSecuredLoan 클래스만 Loan 인터페이스를 구현할 수 있다.
sealed interface Loan permits SecuredLoan, UnSecuredLoan {

}

// sealed 클래스/인터페이스를 상속 또는 구현하는 클래스는 final 필수!
final class SecuredLoan implements Loan {

}


record UnSecuredLoan(int interest) implements Loan {

}
