import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: '', redirectTo: '/users/self', pathMatch: 'full' },
  { path: 'users/:id', component: UserComponent }
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
