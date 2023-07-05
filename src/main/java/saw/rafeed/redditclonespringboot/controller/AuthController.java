package saw.rafeed.redditclonespringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import saw.rafeed.redditclonespringboot.dto.AuthenticationResponse;
import saw.rafeed.redditclonespringboot.dto.LogInRequest;
import saw.rafeed.redditclonespringboot.dto.RegisterRequest;
import saw.rafeed.redditclonespringboot.service.AuthService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;

    // @GetMapping(path="/")
    // public String test(){
    //     return "Hello";
    // }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest){
        authService.signUp(registerRequest);
        return new ResponseEntity<>("New User Registered Successfully.", HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public AuthenticationResponse logIn(@RequestBody LogInRequest logInRequest){
        return authService.logIn(logInRequest);
    }

    @GetMapping(path = "/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successully.", HttpStatus.OK);
    }


}
