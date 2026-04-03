import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  user: any;
  profileItems: any;
  constructor(private router: Router, private userService: UserService) {}
  collapsed = false;

  role = 'ADMIN'; // get from JWT later

  ngOnInit(): void {
    this.user = this.userService.getUser();
    
    this.profileItems = [
      {
        label: this.user.sub,
        icon: 'pi pi-user',
      },
      {
        label: 'Logout',
        icon: 'pi pi-sign-out',
        command: () => this.onLogout(),
      },
    ];
    console.log(this.user);
  }

  toggleSidebar() {
    this.collapsed = !this.collapsed;
  }

  onLogout() {
    
    localStorage.removeItem('token');
    localStorage.clear();
    this.router.navigateByUrl('login');
  }
}
