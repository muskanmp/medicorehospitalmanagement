import { Component } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})

export class AppComponent {

  title = 'hospital-management-ui';
  constructor(private userService: UserService) {}

  ngOnInit() {
    const token = localStorage.getItem('token');

    if (token) {
      const decoded: any = jwtDecode(token);

      this.userService.setUser(decoded);
    }
  }
}
