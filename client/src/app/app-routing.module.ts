import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthComponent } from './user/auth/auth.component';
import { SignupComponent } from './user/signup/signup.component';
import { EncountersComponent } from './encounters/encounters.component';

const routes: Routes = [
  //{ path: '', redirectTo: '/users/self', pathMatch: 'full' },
  { path: 'users/:id', component: DashboardComponent },
  { path: 'login', component: AuthComponent },
  { path: 'signup', component:SignupComponent},
  { path: 'encounters', component:EncountersComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
