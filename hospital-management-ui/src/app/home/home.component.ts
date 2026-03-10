import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {


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

}
