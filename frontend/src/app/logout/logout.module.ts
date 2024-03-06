import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { LogoutComponent } from './logout.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [LogoutComponent],
  imports: [
    CommonModule,
    IonicModule, 
    RouterModule
  ],
  exports: [LogoutComponent] 
})
export class LogoutModule {}
