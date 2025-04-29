import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SecretCreateComponent } from './secret-create/secret-create.component';
import { SecretViewComponent } from './secret-view/secret-view.component';

const routes: Routes = [
  { path: '', component: SecretCreateComponent },
  { path: 'view/:id', component: SecretViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
