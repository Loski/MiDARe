import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/users/self', pathMatch: 'full' },
  { path: 'users/:id', component: UserComponent },
  { path: 'users/:id', component: DashboardComponent }

];


@NgModule({
  imports: [
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
