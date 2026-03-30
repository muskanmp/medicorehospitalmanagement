import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signup: any = {
    username: '',
    password: '',
    name: ''
  };

  constructor(
    private service: AuthService,
    private router: Router
  ) {}

  signupUser() {

    this.service.signup(this.signup)
      .subscribe((res: any) => {

        // Redirect to login
        this.router.navigate(['/login']);

      });

  }

  resetForm() {
    this.signup = {
      username: '',
      password: '',
      name: ''
    };
  }

}
