import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../entities';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  user: User = {
    id: 0,
    username: '',
    password: '',
  };
  profileItems: any;
  constructor(
    private router: Router,
    private userService: UserService,
  ) {}
  collapsed = false;

  role = 'ADMIN'; // get from JWT later

  ngOnInit(): void {
    this.userService.user$.subscribe((user) => {
      if (user) {
        this.user = user;

        this.profileItems = [
          {
            label: this.user.username,
            icon: 'pi pi-user',
          },
          {
            label: 'Logout',
            icon: 'pi pi-sign-out',
            command: () => this.onLogout(),
          },
        ];
      }
    });
  }

  toggleSidebar() {
    this.collapsed = !this.collapsed;
  }

  onLogout() {
    this.userService.clearUser();
    localStorage.removeItem('token');
    localStorage.clear();
    this.router.navigateByUrl('login');
  }
}
