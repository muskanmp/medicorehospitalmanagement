import { Injectable } from '@angular/core';
import { HttpInterceptor,HttpRequest,HttpHandler, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('token');

    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(req).pipe(

      catchError((error: HttpErrorResponse) => {

        // 🔥 HANDLE TOKEN ISSUES
        if (error.status === 401) {

          console.log("Unauthorized → Redirecting to login");

          localStorage.clear();

          this.router.navigate(['/login']);

        }

        // 🔥 HANDLE FORBIDDEN
        if (error.status === 403) {
          alert("Access Denied");
        }

        return throwError(() => error);
      })

    );
  }
}
