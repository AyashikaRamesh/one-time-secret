import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecretService {
  private apiUrl = 'http://localhost:8080/api/secrets';

  constructor(private http: HttpClient) {}

  async createSecret(content: string, password?: string) {
    return firstValueFrom(this.http.post<{id: string}>(this.apiUrl, { content, password }));
  }

  async checkSecret(id: string) {
    return firstValueFrom(this.http.get<{requiresPassword: boolean}>(`${this.apiUrl}/${id}/check`));
  }

  async getSecret(id: string, password?: string) {
    return firstValueFrom(this.http.post<{content: string}>(`${this.apiUrl}/${id}`, { password }));
  }
}
