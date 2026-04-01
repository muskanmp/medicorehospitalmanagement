import { Component } from '@angular/core';
import { WebClientService } from '../web-client-service';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrl: './patient-add.component.scss'
})
export class PatientAddComponent {


  patient: any = {};

  genders = [
    { label: 'Male', value: 'MALE' },
    { label: 'Female', value: 'FEMALE' }
  ];

  bloodGroups = [
    'A+', 'A-', 'B+', 'B-', 'O+', 'O-', 'AB+', 'AB-'
  ];

  insurances: any[] = [];

  constructor(private service: WebClientService) {}

  ngOnInit() {
    this.loadInsurance();
  }

  loadInsurance() {
    this.service.get<[]>(`/`).subscribe(res => {
      this.insurances = res;
    });
  }

  savePatient() {
    this.service.post(`/patient`, this.patient).subscribe(() => {
      alert("Patient saved successfully");
      this.resetForm();
    });
  }

  resetForm() {
    this.patient = {};
  }

}
