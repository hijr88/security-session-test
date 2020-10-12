package me.yh.springsecurity.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final SessionRegistry sessionRegistry;

    @GetMapping
    public String root(Principal principal) {
        if (principal == null) {
            return "익명 사용자입니다.";
        } else {
            System.out.println(principal.getName() + " 로그인");
            return "안녕하세요. " + principal.getName() + "님";
        }
    }

    @GetMapping("/my")
    public Principal my(Principal principal) {
        return principal;
    }

    @GetMapping("/anonymous")
    public String anonymous() {
        return "아무나";
    }

    @GetMapping("/delete")
    public String haha() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        allPrincipals.forEach(o -> {
            if(o instanceof User) {
                UserDetails userDetails = (UserDetails) o;

                if (userDetails.getUsername().equals("user2")) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(o, false);

                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            System.out.println("Expire now 삭제삭제 :" + sessionInformation.getSessionId());
                            sessionInformation.expireNow();

                            /*  아래 메소드를 실행하면 /all 에서 보이는 리스트에서는 없어지지만
                                user2의 세션이 만료되지 않아 여전히 남아있다. /my를 통해 확인

                                주석으로 해두면 만료 되어 다른 url 이동시 config 에서 설정한 expiredUrl(/expire)로 이동한다.
                                다른 url 이동 없이 가만히 있으면 /all 에서 보이는 리스트에 계속 남아있다. user1 통해 확인
                                세션 만료시간처럼 시간 지나면 없어지는거라 생각한다.
                            */
                            //  sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                        }
                    }
                }
            }
        });

        return "user2 세션 만료 하기";
    }

    @GetMapping("/all")
    public List<Object> all() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        System.out.println("현재 인증된 사용자수: " + allPrincipals.size());

        allPrincipals.forEach(o -> {
            if(o instanceof User) {
                UserDetails userDetails = (UserDetails) o;
                System.out.println("username: " + userDetails.getUsername());
            }
        });

        return allPrincipals;
    }

    @GetMapping("/expire")
    public String ex() {
        return "세션 만료!";
    }
}
