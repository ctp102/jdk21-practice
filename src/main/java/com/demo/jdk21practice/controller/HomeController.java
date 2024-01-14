package com.demo.jdk21practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/name")
    public String name(@RequestParam String name) {
        // arrow, inline : jdk 14
        Loan loan = switch (name) {
            case "jhcho" -> new SecuredLoan();
            default -> new UnSecuredLoan(5);
        };

        // pattern matching 이전 기존 코드
        String beforeMessage = null;

        if (loan instanceof SecuredLoan) {
            SecuredLoan sl = (SecuredLoan) loan;
            beforeMessage = name + "님은 무이자";
        } else if (loan instanceof UnSecuredLoan) {
            UnSecuredLoan usl = (UnSecuredLoan) loan;
            beforeMessage = name + "님은 이율이 " + usl.interest();
        }

        // pattern matching : jdk 21
        // + record pattern
        String message = switch (loan) {
            case SecuredLoan sl -> name + "님은 무이자";
            case UnSecuredLoan(int interest) -> name + "님은 이율이 " + interest;
        };

        return message;
    }

    @GetMapping("/score")
    public String score(@RequestParam Integer score) {
        // when
        String message = switch (score) {
            case 100 -> "Prefect!";
            case Integer i when i >= 90 -> "Very Good!";
            case Integer i when i >= 80 -> "Good!";
            default -> "Bad";
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
