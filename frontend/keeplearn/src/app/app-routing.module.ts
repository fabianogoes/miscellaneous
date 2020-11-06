import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadChildren: './home/home.module#HomePageModule' },
  { path: 'card-list', loadChildren: './card-list/card-list.module#CardListPageModule' },
  { path: 'card-add', loadChildren: './card-add/card-add.module#CardAddPageModule' },
  { path: 'card-edit/:id', loadChildren: './card-add/card-add.module#CardAddPageModule' },
  { path: 'settings', loadChildren: './settings/settings.module#SettingsPageModule' },
  { path: 'profile', loadChildren: './profile/profile.module#ProfilePageModule' },
  { path: 'about', loadChildren: './about/about.module#AboutPageModule' },
  { path: 'practice', loadChildren: './practice/practice.module#PracticePageModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
