package pt.bmo.list4u.api.shoppinglist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/token")
public class TokenInfoController {

    @GetMapping
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }
}
