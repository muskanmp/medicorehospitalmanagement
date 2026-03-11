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

  setLoggedInUserId(userId: string): void
  {
    this.loggedInUserId = userId;
    localStorage.setItem('loggedInUserId', userId); // Store in local storage
  }

  getLoggedInUserId(): string | null
  {
    if (!this.loggedInUserId)
    {
      // Retrieve from local storage if not already loaded
      this.loggedInUserId = localStorage.getItem('id');
      // this.loggedInUserId = localStorage.getItem('loggedInUserId');
    }
    return this.loggedInUserId;
  }

  clearLoggedInUserId(): void
  {
    this.loggedInUserId = null;
    localStorage.removeItem('id'); // Remove from local storage
    // localStorage.removeItem('loggedInUserId'); // Remove from local storage
  }
  // Error handling
  // private handleError(error: any): Observable<never>
  // {
  //   this.loading = false;
  //   return throwError(error);
  // }

}

//After Logout
// @Injectable({
//   providedIn: 'root'
// })
// export class AuthGuard implements CanActivate {

//   constructor(private router: Router) {}

//   canActivate(): boolean {
//     const token = localStorage.getItem('token');
//     if (token) {
//       return true; // Allow access
//     } else {
//       this.router.navigate(['/login']); // Redirect to login
//       return false; // Deny access
//     }
//   }
// }


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate
{

  constructor(private router: Router)
  {
    window.addEventListener('storage', (event) =>
    {
      if (event.key === 'token' && !event.newValue)
      {
        this.router.navigate(['/login']); // Redirect to login
      }
    });
  }

  canActivate(): boolean
  {
    const token = localStorage.getItem('token');
    if (token)
    {
      return true; // Allow access
    } else
    {
      this.router.navigate(['/login']); // Redirect to login
      return false; // Deny access
    }
  }


}

