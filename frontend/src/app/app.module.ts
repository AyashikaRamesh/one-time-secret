import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SecretCreateComponent } from './secret-create/secret-create.component';
import { SecretViewComponent } from './secret-view/secret-view.component';

const routes = [
  { path: '', component: SecretCreateComponent },
  { path: 'view/:id', component: SecretViewComponent }
];

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
