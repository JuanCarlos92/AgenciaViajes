import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomePage } from './home.page';

import { HomePageRoutingModule } from './home-routing.module';
import { CardMonsterComponent } from '../components/card-monster/card-monster.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HomePageRoutingModule,
    ReactiveFormsModule

],
  declarations: [HomePage]
})
export class HomePageModule {}
