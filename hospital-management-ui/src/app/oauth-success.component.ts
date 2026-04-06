import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-oauth-success',
  template: ''
})
export class OauthSuccessComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {

    const token = this.route.snapshot.queryParamMap.get('token');

    if (token) {
      localStorage.setItem('token', token);
      this.router.navigate(['/setnewpassword']);
    } else {
      this.router.navigate(['/login']);
    }

  }
}