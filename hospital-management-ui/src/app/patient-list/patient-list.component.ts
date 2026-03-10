import { Component } from '@angular/core';
import { WebClientService } from '../web-client-service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss'
})
export class PatientListComponent
{

  patients: any[] = [];

  constructor(
    private webClient: WebClientService
  ) { }

  ngOnInit()
  {

    this.webClient.get(`/getallpatients`)
      .subscribe((data: any) =>
      {
        this.patients = data;
      });

  }

}
