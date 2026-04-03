import { Component } from '@angular/core';
import { WebClientService } from '../web-client-service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss',
})
export class PatientListComponent {
  patients: any[] = [];

  constructor(private webClient: WebClientService, private router:Router) {}

  ngOnInit() {
    this.webClient.get(`/admin/patients`).subscribe((data: any) => {
      this.patients = data;
    });
  }

  addPatient() {
    this.router.navigateByUrl('/home/add-patient');
  }
}
