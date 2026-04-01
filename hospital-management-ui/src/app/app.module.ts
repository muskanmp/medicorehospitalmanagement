import { LOCALE_ID, NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { DatePipe } from "@angular/common";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { GoogleChartsModule } from "angular-google-charts";
import { AppRoutingModule } from './app-routing.module';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { AppointmentAddComponent } from './appointment-add/appointment-add.component';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { MenuModule } from 'primeng/menu';
import { SidebarModule } from 'primeng/sidebar';
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from './home/home.component';
import { JwtInterceptor } from "./jwt.interceptor";
import { DropdownModule } from "primeng/dropdown";
import { CalendarModule } from "primeng/calendar";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PatientAddComponent } from "./patient-add/patient-add.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    PatientListComponent,
    AppointmentListComponent,
    AppointmentAddComponent,
    PatientAddComponent,
    HomeComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    GoogleChartsModule,
    AppRoutingModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    CardModule,
    MenuModule,
    SidebarModule,
    TableModule,
    DropdownModule,
    CalendarModule,
    BrowserAnimationsModule
  ],

  providers: [
    DatePipe,
    { provide: LOCALE_ID, useValue: 'en-IN' },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule
{
}
