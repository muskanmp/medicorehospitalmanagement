import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { environment } from '../../environments/environment.development';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  loginData = {
    username: '',
    password: '',
  };

  errormessage: any;

  private baseUrl = environment.BASE_URL;

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.userService.clearUser();
    localStorage.removeItem('token');
  }

  login() {
    this.authService.login(this.loginData).subscribe(
      (res: any) => {
        localStorage.setItem('token', res.jwt);

        this.router.navigate(['/home/dashboard']);
      },
      (err) => {
        console.log(err);
        if (err.error.statusCode === '401 UNAUTHORIZED') {
          this.errormessage = 'Invalid username or password!';
        }
      },
    );
  }

  googlelogin() {
    window.location.href = `${this.baseUrl}/oauth2/authorization/google`;
  }

  githublogin() {
    window.location.href = `${this.baseUrl}/oauth2/authorization/github`;
  }
}
