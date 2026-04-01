import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
})
export class SignupComponent {
  signup: any = {
    username: '',
    password: '',
    name: '',
    gender: '',
    birthdate: '',
    bloodgroup: '',
    email: '',
  };

  bloodGroups = [
    { label: 'A+', value: 'A_POSITIVE' },
    { label: 'A-', value: 'A_NEGATIVE' },
    { label: 'B+', value: 'B_POSITIVE' },
    { label: 'B-', value: 'B_NEGATIVE' },
    { label: 'AB+', value: 'AB_POSITIVE' },
    { label: 'AB-', value: 'AB_NEGATIVE' },
    { label: 'O+', value: 'O_POSITIVE' },
    { label: 'O-', value: 'O_NEGATIVE' },
  ];

  genders = [
    { label: 'Male', value: 'MALE' },
    { label: 'Female', value: 'FEMALE' },
  ];
cnfmpassword: any;
  constructor(
    private service: AuthService,
    private router: Router,
  ) {}

  signupUser() {
    this.service.signup(this.signup).subscribe((res: any) => {
      // Redirect to login
      this.router.navigate(['/login']);
    });
  }

  resetForm() {
    this.signup = {
      username: '',
      password: '',
      name: '',
      gender: '',
      birthdate: '',
      bloodgroup: '',
      email: '',
    };
  }
}
