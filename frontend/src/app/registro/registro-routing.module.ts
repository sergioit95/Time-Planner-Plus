import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistroPage } from './registro.page';

const routes: Routes = [
  {
    path: 'registro',
    component: RegistroPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RegistroPageRoutingModule {}
