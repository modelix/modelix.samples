import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OverviewComponent } from './overview/overview.component';
import { RoomOverviewComponent } from './room-overview/room-overview.component';
import { LectureTableComponent } from './lecture-table/lecture-table.component';

@NgModule({
  declarations: [
    AppComponent,
    OverviewComponent,
    RoomOverviewComponent,
    LectureTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
