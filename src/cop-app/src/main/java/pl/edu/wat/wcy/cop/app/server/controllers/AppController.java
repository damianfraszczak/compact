package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.edu.wat.wcy.cop.app.shared.GwtEndpoints.DEV_PREFIX;


@Controller
// Handles app requests.
public class AppController {
    @RequestMapping(DEV_PREFIX + "/cop/user")
    public ResponseEntity<?> user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return WebUtil.createOkEntity(auth.getPrincipal().toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}