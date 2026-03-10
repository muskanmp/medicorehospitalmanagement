import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { SignupComponent } from './signup/signup.component';
import { AppointmentAddComponent } from './appointment-add/appointment-add.component';
import { AuthGuard } from './guards/authGuard';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';


export const routes: Routes = [

{
path:'home',
component:HomeComponent,
children:[

{ path:'dashboard', component:DashboardComponent },

{ path:'patients', component:PatientListComponent },

{ path:'appointments', component:AppointmentListComponent },

{ path:'add-appointment', component:AppointmentAddComponent }

]
},

{ path:'login', component:LoginComponent },

{ path:'signup', component:SignupComponent },

{ path:'', redirectTo:'dashboard', pathMatch:'full'}

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule
{

}
