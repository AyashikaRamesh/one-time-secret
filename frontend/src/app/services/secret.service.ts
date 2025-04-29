import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SecretService {
  private apiUrl = 'http://localhost:8081/api/secrets';

  constructor(private http: HttpClient) { }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // Client-side or network error
      return throwError(() => new Error('Network error. Please check your connection.'));
    }
    return throwError(() => error);
  }

  createSecret(content: string, password?: string): Observable<{id: string}> {
    return this.http.post<{id: string}>(this.apiUrl, { content, password })
      .pipe(catchError(this.handleError));
  }

  checkSecret(id: string): Observable<{requiresPassword: boolean}> {
    return this.http.get<{requiresPassword: boolean}>(`${this.apiUrl}/${id}/check`)
      .pipe(catchError(this.handleError));
  }

  viewSecret(id: string, password?: string): Observable<{content: string}> {
    return this.http.post<{content: string}>(`${this.apiUrl}/${id}`, { password })
      .pipe(catchError(this.handleError));
  }
}
