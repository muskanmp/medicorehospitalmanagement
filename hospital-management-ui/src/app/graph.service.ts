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


  getorderstatustotalcount(): Observable<any[]> {
    const token = localStorage.getItem("token");
    const url= `${environment.BASE_URL}/getAppointments&TOKEN=${token}`
    return this.http.get<any[]>(url);
  }

}
