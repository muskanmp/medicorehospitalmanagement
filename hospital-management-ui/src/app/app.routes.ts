import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from 'angular-google-charts';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { SignupComponent } from './signup/signup.component';
import { AppointmentAddComponent } from './appointment-add/appointment-add.component';
import { AuthGuard } from './guards/authGuard';


export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
{ path:'login', component:LoginComponent },

{ path:'signup', component:SignupComponent },

{ path:'dashboard', component:DashboardComponent, canActivate:[AuthGuard] },

{ path:'patients', component:PatientListComponent },

{ path:'appointments', component:AppointmentListComponent },

{ path:'add-appointment', component:AppointmentAddComponent }

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutes
{

}
