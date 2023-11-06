import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OverviewComponent } from './overview/overview.component';
import { RoomOverviewComponent } from './room-overview/room-overview.component';

const routes: Routes = [
  { path: '', component: OverviewComponent },
  { path: 'room/:ref', component: RoomOverviewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
