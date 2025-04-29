import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  template: `
    <nav class="navbar navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" routerLink="/">One-Time Secret</a>
      </div>
    </nav>
    <router-outlet></router-outlet>
  `,
  styles: [],
  imports: [RouterModule],
  standalone: true
})
export class AppComponent {}
