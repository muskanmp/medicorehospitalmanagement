import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) {}
  collapsed = false;

  role = 'ADMIN'; // get from JWT later

  profileItems = [
    {
      label: 'Profile',
      icon: 'pi pi-user',
    },
    {
      label: 'Logout',
      icon: 'pi pi-sign-out',
      command: () => this.onLogout(),
    },
  ];

  ngOnInit(): void {}

  toggleSidebar() {
    this.collapsed = !this.collapsed;
  }

  onLogout() {
    
    localStorage.removeItem('token');
    localStorage.clear;
    this.router.navigateByUrl('login');
  }
}
