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
  cnfpasswordError: boolean = false;
  constructor(
    private service: AuthService,
    private router: Router,
  ) {}

  onPasswordCheck() {
    if (this.signup.password !== this.cnfmpassword) {
      this.cnfpasswordError = true;
    }
      else {
        this.cnfpasswordError = false;
      }
  }
  
  signupUser() {
    if (this.signup.password !== this.cnfmpassword) {
      this.cnfpasswordError = true;
      return;
    }
    if (!this.signup.username || !this.signup.password || !this.signup.name || !this.signup.gender 
      || !this.signup.birthdate || !this.signup.bloodgroup) {
      alert("Please fill all the fields");
      return;
    }

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
