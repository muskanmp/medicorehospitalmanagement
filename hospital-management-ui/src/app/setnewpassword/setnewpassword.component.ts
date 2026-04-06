import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../entities';
import { WebClientService } from '../web-client-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-setnewpassword',
  templateUrl: './setnewpassword.component.html',
  styleUrl: './setnewpassword.component.scss',
})
export class SetnewpasswordComponent implements OnInit {
  user: User = {
    id: 0,
    username: '',
    password: '',
  };

  cnfmpassword: any;
  cnfpasswordError: boolean = false;

  constructor(
    private userService: UserService,
    private webclient: WebClientService,
    private router: Router,
  ) {}
  ngOnInit(): void {
    
      this.webclient.get<User>('/user').subscribe(
        (data) => {
          console.log(data);
          this.user = data;
          this.userService.setUser(data);
        }
      )
    if (!this.user) {
      alert('No user found. Please login again.');
      this.router.navigateByUrl('login');
    
    }
  }

  onPasswordCheck() {
    if (this.user.password !== this.cnfmpassword) {
      this.cnfpasswordError = true;
    } else {
      this.cnfpasswordError = false;
    }
  }

  onSubmit() {
    if (this.user.password !== this.cnfmpassword) {
      this.cnfpasswordError = true;
      return;
    }
    this.webclient.post(`/user/save-password`, this.user).subscribe((data) => {
      this.userService.clearUser();
      alert('Password reset successful!');
      this.router.navigateByUrl('home/dashboard');
    });
  }
}
