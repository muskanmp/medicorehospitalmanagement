import { Component } from '@angular/core';
import { WebClientService } from '../web-client-service';

@Component({
  selector: 'app-appointment-add',
  templateUrl: './appointment-add.component.html',
  styleUrl: './appointment-add.component.scss'
})
export class AppointmentAddComponent {

  appointment:any={};

patients:any[]=[];
doctors:any[]=[];
departments:any[]=[];
timeSlots:any[]=[];

statusOptions=[
{label:'Scheduled',value:'SCHEDULED'},
{label:'Completed',value:'COMPLETED'},
{label:'Cancelled',value:'CANCELLED'}
];
isediting: any;

constructor(private service:WebClientService){}

ngOnInit(){

this.loadPatients();
this.loadDoctors();
this.loadDepartments();

}

loadPatients(){
// this.service.get<[]>(`/patients`).subscribe(res=>this.patients=res);
}

loadDoctors(){
// this.service.get<[]>(`/doctors`).subscribe(res=>this.doctors=res);
}

loadDepartments(){
// this.service.get<[]>(`/departments`).subscribe(res=>this.departments=res);
}

loadDoctorAvailability(){
  this.timeSlots=[
{label:'10:00 AM',value:'10:00'},
{label:'11:00 AM',value:'11:00'},
{label:'12:00 PM',value:'12:00'}
];

}

saveAppointment(){

this.service.post('/appointment',this.appointment)
.subscribe(()=>{
alert("Appointment saved successfully");
this.resetForm();
});

}

resetForm(){
this.appointment={};
}

}
