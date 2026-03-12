import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { catchError, finalize, Observable, tap, throwError } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class WebClientService
{

   base_url = environment.BASE_URL;
  private token: string | null = null;
  private loggedInUserId: string | null = null;
  public requestinflight: number = 0;

  constructor(private http: HttpClient)
  {
    let token = localStorage.getItem("token");
    if (token)
    {
      this.setToken(token);
    }
  }

  public setToken(token: string | null)
  {
    this.token = token;
  }

  public get busy(): boolean
  {
    // console.log("Request in flight: ", this.requestinflight);

    return this.requestinflight > 0;
  }

  public get<T>(url: string)
  {
    // console.log(environment.BASE_URL);
    // console.log(this.base_url);
    return this.http.get<T>(`${this.base_url}${url}`, {
      headers: {
        TOKEN: this.token ?? ''
      }
    });
  }
  public getOne<T>(url: string)
  {
    // console.log(environment.BASE_URL);
    // console.log(this.base_url);
    return this.http.get<T>(`${this.base_url}${url}`, {
      headers: {
        TOKEN: this.token ?? ''
      }
    });
  }
  // public delete<T>(url: string)
  // {
  //   return this.http.get<T>(`${this.base_url}${url}`,{headers:{
  //     TOKEN: this.token??''
  //   }});
  // }

  public delete<T>(url: string)
  {
    return this.http.delete<T>(`${this.base_url}${url}`, {
      headers: {
        TOKEN: this.token ?? ''
      }
    });
  }

  public post<T, U>(url: string, body: T)
  {
    this.requestinflight++;
    return this.http.post<U>(`${this.base_url}${url}`, body, {
      headers: {
        TOKEN: this.token ?? ''
      }
    }).pipe(finalize(() => { if (this.requestinflight > 0) { this.requestinflight--; } }));
  }

  public postLoc<T, U>(url: string, body: T)
  {

    return this.http.post<U>(`${this.base_url}${url}`, body, {
      headers: {
        TOKEN: this.token ?? ''
      }
    });
  }

  /*  public post<T, U>(url: string, body: T)
   {
     this.loading = true
     return this.http.post<U>(`${this.base_url}${url}`, body, {
       headers: {
         TOKEN: this.token ?? ''
       }
     }).pipe(tap(() => this.loading = false),
       catchError(this.handleError.bind(this)));
   } */

  public put<T, U>(url: string, body: T)
  {
    this.requestinflight++;
    return this.http.put<U>(`${this.base_url}${url}`, body, {
      headers: {
        TOKEN: this.token ?? ''
      }
    }).pipe(finalize(() => { if (this.requestinflight > 0) { this.requestinflight--; } }));
  }

}
