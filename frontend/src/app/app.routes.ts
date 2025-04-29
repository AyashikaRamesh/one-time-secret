import { Routes } from '@angular/router';
import { SecretCreateComponent } from './secret-create/secret-create.component';
import { SecretViewComponent } from './secret-view/secret-view.component';

export const routes: Routes = [
    { path: '', component: SecretCreateComponent },
    { path: 'view/:id', component: SecretViewComponent }
];
