import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginData = {
    username: '',
    password: ''
  };

  errormessage:any;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login() {

    this.authService.login(this.loginData)
      .subscribe((res:any) => {

        localStorage.setItem("token", res.jwt);

        this.router.navigate(['/home/dashboard']);

      },(err)=>{

        console.log(err);
        if(err.error.statusCode==="401 UNAUTHORIZED"){

          this.errormessage= "Invalid username or password!"
        }

      }
    );

  }
}
