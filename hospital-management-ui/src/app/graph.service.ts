import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { WebClientService } from './web-client-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GraphService {

  private baseUrl = `${environment.BASE_URL}`;
  constructor(private http: HttpClient, private webclient: WebClientService) {
  }


  getDashboardStatsTotalCount(): Observable<any[]> {
    const token = localStorage.getItem("token");
    const url= `${this.baseUrl}/admin/dashboard/stats`
    return this.http.get<any[]>(url);
  }

  getDoctorStats(): Observable<any[]> {
    const url= `${environment.BASE_URL}/admin/dashboard/doctor-stats`
    return this.http.get<any[]>(url);
  }

  getAppointmentsStats(): Observable<any[]> {
    const url= `${environment.BASE_URL}/admin/dashboard/monthly-appointments`
    return this.http.get<any[]>(url);
  }

}
