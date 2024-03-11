import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular'; 
import { PerfilComponent } from './perfil.component';
import { RouterModule, Routes } from '@angular/router';
const routes: Routes = [
    { path: '', component: PerfilComponent }
  ];
  
  @NgModule({
    imports: [
      CommonModule,
      FormsModule,
      IonicModule,
      RouterModule.forChild(routes)
    ],
    declarations: [PerfilComponent]
  })
  export class PerfilModule {}
  
