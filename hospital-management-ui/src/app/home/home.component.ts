import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  constructor(private router:Router){

  }
collapsed=false;

role='ADMIN';   // get from JWT later

profileItems=[
{
label:'Profile',
icon:'pi pi-user'
},
{
label:'Logout',
icon:'pi pi-sign-out',
command:()=>this.logout()
}
];

toggleSidebar(){
this.collapsed=!this.collapsed;
}

logout(){
localStorage.removeItem("token");
window.location.href="/login";
}

onLogout() {

  this.router.navigateByUrl("login")
throw new Error('Method not implemented.');
}

}
