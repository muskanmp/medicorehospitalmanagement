import { Component } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { UserService } from './user.service';
import { WebClientService } from './web-client-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'hospital-management-ui';
  constructor(
    private userService: UserService,
    private webClient: WebClientService,
    private router: Router,
  ) {}

  ngOnInit() {
    const token = localStorage.getItem('token');

    if (token) {
      // console.log("app user", this.userService.getUser());

      if (this.userService.getUser()!=null) return;

      this.webClient.get('/user').subscribe(
        (data) => {
          console.log(data);
          this.userService.setUser(data);
        },
        (error) => {
          console.error('Error fetching user data:', error);
          this.userService.clearUser();
          localStorage.removeItem('token');
          this.router.navigateByUrl('login');
        },
      );
    }
  }
}
